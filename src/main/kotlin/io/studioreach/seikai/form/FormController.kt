package io.studioreach.seikai.form

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/forms")
class FormController {

    @Autowired
    lateinit var formRepo: FormRepo

    @GetMapping
    fun all(): List<Form> {
        return formRepo.all()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: String): Form? {
        return formRepo.findById(id)
    }
}
