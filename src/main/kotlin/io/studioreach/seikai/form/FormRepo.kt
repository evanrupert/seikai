package io.studioreach.seikai.form

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoOperations
import org.springframework.data.mongodb.core.findAll
import org.springframework.stereotype.Repository

@Repository
class FormRepo {

    @Autowired
    lateinit var db: MongoOperations

    fun all(): List<Form> {
        return db.findAll()
    }

    fun findById(id: String): Form? {
        return db.findById(id, Form::class.java)
    }

    fun insert(form: Form): Form {
        return db.insert(form)
    }

    fun deleteAll() {
        db.dropCollection("forms")
    }
}
