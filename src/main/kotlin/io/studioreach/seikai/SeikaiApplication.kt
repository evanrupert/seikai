package io.studioreach.seikai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SeikaiApplication

fun main(args: Array<String>) {
	runApplication<SeikaiApplication>(*args)
}
