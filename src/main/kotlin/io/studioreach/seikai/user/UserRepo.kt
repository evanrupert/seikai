package io.studioreach.seikai.user

import io.studioreach.seikai.Repo
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.selectAll
import org.springframework.stereotype.Repository


@Repository
class UserRepo : Repo() {
    object Users : Table("users") {
        val id = uuid("id")
        val name = varchar("name", 255)
        val age = integer("age")

        override val primaryKey = PrimaryKey(id, name = "PK_User_Id")

        fun from(row: ResultRow): User =
            User(
                id = row[id],
                name = row[name],
                age = row[age]
            )
    }

    fun all(): List<User> = query {
        Users.selectAll().map(Users::from)
    }
}
