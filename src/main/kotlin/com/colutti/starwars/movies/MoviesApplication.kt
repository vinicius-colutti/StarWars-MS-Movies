package com.colutti.starwars.movies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication

@SpringBootApplication
class MoviesApplication

fun main(args: Array<String>) {
	runApplication<MoviesApplication>(*args)
}
