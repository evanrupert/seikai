package io.studioreach.seikai.form

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "forms")
data class Form(
    val name: String,
    val fields: List<Field>,
    @Id val id: String? = null
)

data class Field(
    val name: String,
    val type: String
)
