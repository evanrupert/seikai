package io.studioreach.seikai

import io.studioreach.seikai.form.FormRepo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
open class DatabaseTest {

    @Autowired
    protected lateinit var formRepo: FormRepo

    @BeforeEach
    @AfterEach
    fun purge() {
        formRepo.deleteAll()
    }
}
