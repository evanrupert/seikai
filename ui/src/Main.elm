import Browser
import List
import Html exposing (Html, text, div, h1, form, input, label)
import Http
import Json.Decode as Json

type alias Model = Form

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

-- TODO: Replace this with Browser.application to pull form id from url
main : Program () Model Msg
main =
    Browser.document
        { init = init
        , view = view
        , update = update
        , subscriptions = subscriptions
        }

init : () -> ( Model, Cmd Msg )
init _ =
    ({ id = ""
    , name = ""
    , fields = []
    }, fetchFormData)

subscriptions : Model -> Sub Msg
subscriptions _ =
    Sub.none

view : Model -> Browser.Document Msg
view model =
    { title = "Seikai"
    , body = [body model]
    }

body : Model -> Html Msg
body model =
    div []
        [ h1 [] [ text model.name ]
        , formView model.fields
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

update : Msg -> Model -> ( Model, Cmd Msg )
update msg model =
    case msg of
        FetchedFormData result ->
            case result of
                Ok formData ->
                    (formData, Cmd.none)
                Err _ ->
                    Debug.log (Debug.toString e)
                    (model, Cmd.none)

-- HTTP

fetchFormData : Cmd Msg
fetchFormData =
    Http.get
        { url = "/api/forms/5e7584da2abeb222d91eca5d"
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
