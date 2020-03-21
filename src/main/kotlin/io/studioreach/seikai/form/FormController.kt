package io.studioreach.seikai.form

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("forms")
class FormController {

    @Autowired
    lateinit var formRepo: FormRepo

    @GetMapping
    fun all(): List<Form> {
        return formRepo.all()
    }
}
