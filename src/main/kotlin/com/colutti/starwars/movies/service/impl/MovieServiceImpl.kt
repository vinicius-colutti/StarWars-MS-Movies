package com.colutti.starwars.movies.service.impl

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.converts.MovieConverter
import com.colutti.starwars.movies.dto.movie.response.MovieResponse
import com.colutti.starwars.movies.model.CharactersRelationship
import com.colutti.starwars.movies.repository.CharactersRelationshipRepository
import com.colutti.starwars.movies.repository.MovieRepository
import com.colutti.starwars.movies.service.MovieService
import com.colutti.tour.exception.MovieNotFoundException
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
       var movieList =  this.movieConverter.movieToListResponse(movieRepository.findAll().toList())
       println(movieList)
       return movieList
    }

    override fun getById(id: Long): MovieResponse =
        movieConverter.movieToResponse(movieRepository.findById(id).orElseGet { throw MovieNotFoundException("Movie ${id} not found") })

    override fun update(idRequest: Long, movieRequest: MovieRequest) {
        charactersRelationshipRepository.deleteMovies(idRequest)
        var movieToUpdate = this.movieConverter.requestToMovie(movieRequest)
        movieToUpdate.id = idRequest
        println(movieToUpdate.characters)
        movieToUpdate.characters = movieToUpdate.characters.map { char ->
            CharactersRelationship(0, char.character_id , movieToUpdate)
        }
        movieRepository.save(movieToUpdate)
    }


}

