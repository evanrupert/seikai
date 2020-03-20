package io.studioreach.seikai

import org.jetbrains.exposed.sql.Database
import org.springframework.stereotype.Component

@Component
class SeikaiDatabase {
    val connect by lazy {
        println("Connecting to database...")

        Database.connect(
            "jdbc:postgresql://localhost:5432/seikai",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "password"
        )
    }
}
