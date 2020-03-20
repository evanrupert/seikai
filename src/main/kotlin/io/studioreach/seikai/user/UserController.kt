package io.studioreach.seikai.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/users")
class UserController {

    @Autowired
    lateinit var userRepo: UserRepo

    @GetMapping
    fun all(): List<User> = userRepo.all()
}
