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
        println("In form repo")
        return db.findAll()
    }

    fun deleteAll() {
        db.dropCollection("forms")
    }
}
