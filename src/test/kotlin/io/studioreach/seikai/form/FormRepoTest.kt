package io.studioreach.seikai.form

import io.studioreach.seikai.DatabaseTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import strikt.api.expectThat
import strikt.assertions.isTrue

class FormRepoTest : DatabaseTest() {

    @Autowired
    lateinit var db: MongoOperations

    @Test
    fun `can add a new form to the database`() {
        db.insert(Form(
            name = "Form",
            fields = listOf(Field(name = "Field 1", type = "input"))
        ))
        expectThat(true).isTrue()
    }
}
