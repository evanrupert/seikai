package io.studioreach.seikai.form

import io.studioreach.seikai.ApiTest
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class FormControllerTest : ApiTest() {
    @Test
    fun `get returns the form with the given id`() {
        val form = formRepo.insert(Form(
            name = "Bank Loan Application",
            fields = listOf(
                Field(name = "Name", type = "input"),
                Field(name = "Age", type = "numeric")
            )
        ))

        assertGet("/api/forms/${form.id}") { resp: Form ->
            expectThat(resp).isEqualTo(form)
        }
    }
}
