package com.colutti.starwars.movies.controller

import com.colutti.starwars.movies.dto.ResponseDefault
import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.response.MovieResponse
import com.colutti.starwars.movies.model.Movie
import com.colutti.starwars.movies.service.MovieService
import com.colutti.tour.model.exceptions.ErrorMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
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

    @GetMapping()
    fun getAll(): ResponseEntity<List<MovieResponse>>{
        val list = service.getAll()
        val status = if(list.size == 0) HttpStatus.NOT_FOUND else HttpStatus.OK
        return ResponseEntity(list,status)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody movieRequest: MovieRequest): ResponseEntity<ResponseDefault> {
        var status = HttpStatus.NOT_FOUND
        var respostaJson = ResponseDefault("Error", Date())
        if(service.getById(id) != null){
            status = HttpStatus.OK
            service.update(id, movieRequest)
            respostaJson = ResponseDefault("OK", Date())
        }
        return ResponseEntity(respostaJson, status)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id:Long) =
        ResponseEntity(service.getById(id),HttpStatus.OK)



}