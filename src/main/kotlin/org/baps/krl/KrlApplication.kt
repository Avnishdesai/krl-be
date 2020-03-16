package org.baps.krl

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KrlApplication

fun main(args: Array<String>) {
	runApplication<KrlApplication>(*args)
}
