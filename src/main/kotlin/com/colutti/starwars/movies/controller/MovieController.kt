package com.colutti.starwars.movies.controller

import com.colutti.starwars.movies.dto.ResponseDefault
import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.model.Movie
import com.colutti.starwars.movies.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping(value = ["/starwars/movies"])
class MovieController {

    @Autowired
    lateinit var service: MovieService

    @PostMapping()
    fun create(@RequestBody movieRequest: MovieRequest): ResponseEntity<ResponseDefault> {
        service.create(movieRequest)
        val respostaJson = ResponseDefault("Created movie!", Date())
        return ResponseEntity(respostaJson, HttpStatus.CREATED)
    }

}