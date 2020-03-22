import Browser
import List
import Html exposing (Html, text, div, h1, form, input, label)
import Html.Attributes exposing (class)
import Http
import Url
import Url.Parser as Parser
import Browser.Navigation as Nav
import Json.Decode as Json

type Page
    = NotFoundPage
    | FormPage

type alias Model =
    { key : Nav.Key
    , page : Page
    , form : Form
    }

type alias Form =
    { id : String
    , name : String
    , fields : List Field
    }

type alias Field =
    { name : String
    , t : String
    }

type Msg
    = FetchedFormData (Result Http.Error Form)
    | LinkClicked Browser.UrlRequest
    | UrlChanged Url.Url

main : Program () Model Msg
main =
    Browser.application
        { init = init
        , view = view
        , update = update
        , subscriptions = subscriptions
        , onUrlRequest = LinkClicked
        , onUrlChange = UrlChanged
        }

init : () -> Url.Url -> Nav.Key -> ( Model, Cmd Msg )
init _ url key =
    case parseUrl url of
        Just formId ->
            ({ key = key
            , page = FormPage
            , form = emptyFormData
            }, fetchFormData formId)
        Nothing ->
            ({ key = key
            , page = NotFoundPage
            , form = emptyFormData
            }, Cmd.none)

emptyFormData : Form
emptyFormData =
    { id = ""
    , name = ""
    , fields = []
    }

subscriptions : Model -> Sub Msg
subscriptions _ =
    Sub.none

view : Model -> Browser.Document Msg
view model =
    case model.page of
        FormPage ->
            { title = model.form.name ++ " | Seikai"
            , body = [formPage model.form]
            }
        NotFoundPage ->
            { title = "Form Not Found | Seikai"
            , body = [notFoundPage]
            }

formPage : Form -> Html Msg
formPage form =
    div []
        [ h1 [] [ text form.name ]
        , formView form.fields
        ]

formView : List Field -> Html Msg
formView fields =
    form []
        (List.map formField fields)

formField : Field -> Html Msg
formField field =
    div []
        [ label [] [ text field.name ]
        , input [] []
        ]

notFoundPage : Html Msg
notFoundPage =
    div [ class "not-found-container" ]
        [ h1 [] [ text "We can't seem to find that form" ]
        ]


update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        FetchedFormData result ->
            case result of
                Ok formData ->
                    ({ model | form = formData }, Cmd.none)
                Err _ ->
                    Debug.log (Debug.toString e)
                    ({ model | page = NotFoundPage }, Cmd.none)
        LinkClicked urlRequest ->
            case urlRequest of
                Browser.Internal url ->
                    ( model
                    , Nav.pushUrl model.key (Url.toString url)
                    )
                Browser.External href ->
                    ( model
                    , Nav.load href
                    )
        UrlChanged _ ->
            (model, Cmd.none)

-- URL

parseUrl : Url.Url -> Maybe String
parseUrl = Parser.parse Parser.string

-- HTTP

fetchFormData : String -> Cmd Msg
fetchFormData formId =
    Http.get
        { url = "/api/forms/" ++ formId
        , expect = Http.expectJson FetchedFormData formDataDecoder
        }

formDataDecoder : Json.Decoder Form
formDataDecoder =
    Json.map3
        Form
        (Json.field "id" Json.string)
        (Json.field "name" Json.string)
        (Json.field "fields" <| Json.list fieldDataDecoder)

fieldDataDecoder : Json.Decoder Field
fieldDataDecoder =
    Json.map2
        Field
        (Json.field "name" Json.string)
        (Json.field "type" Json.string)
