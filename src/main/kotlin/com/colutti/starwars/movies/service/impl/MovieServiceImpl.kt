package com.colutti.starwars.movies.service.impl

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.request.converts.MovieRequestConverter
import com.colutti.starwars.movies.model.Movie
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

    override fun create(movieRequest: MovieRequest) {
        var movieRequestConverter = MovieRequestConverter()
        var movieToSave = movieRequestConverter.convertToMovie(movieRequest)
        println("MOVIE TO SAVE")
        println(movieToSave)
        var savedMovie = movieRepository.save(movieToSave)
        movieToSave.characters.map { char ->
            println("TESTE")
            char.movie = savedMovie
            charactersRelationshipRepository.save(char)
        }
    }

}