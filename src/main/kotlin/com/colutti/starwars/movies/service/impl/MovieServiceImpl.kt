package com.colutti.starwars.movies.service.impl

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.converts.MovieConverter
import com.colutti.starwars.movies.dto.movie.response.MovieResponse
import com.colutti.starwars.movies.repository.CharactersRelationshipRepository
import com.colutti.starwars.movies.repository.MovieRepository
import com.colutti.starwars.movies.service.MovieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl: MovieService {

    @Autowired
    lateinit var movieRepository: MovieRepository
    @Autowired
    lateinit var charactersRelationshipRepository: CharactersRelationshipRepository
    @Autowired
    lateinit var movieConverter: MovieConverter

    override fun create(movieRequest: MovieRequest) {
        var movieToSave = this.movieConverter.requestToMovie(movieRequest)
        var savedMovie = movieRepository.save(movieToSave)
        movieToSave.characters.map { char ->
            char.movie = savedMovie
            charactersRelationshipRepository.save(char)
        }
    }

    override fun getAll(): List<MovieResponse> {
       var movieList =  this.movieConverter.movieToResponse(movieRepository.findAll().toList())
       println(movieList)
       return movieList
    }

}