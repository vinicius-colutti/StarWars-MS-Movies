package com.colutti.starwars.movies.service.impl

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.converts.MovieConverter
import com.colutti.starwars.movies.dto.movie.converts.PersonageConverter
import com.colutti.starwars.movies.dto.movie.response.MovieResponse
import com.colutti.starwars.movies.model.Movie
import com.colutti.starwars.movies.model.PersonageRelationship
import com.colutti.starwars.movies.repository.MovieRepository
import com.colutti.starwars.movies.service.MovieService
import com.colutti.starwars.movies.service.PersonageRelationShipService
import com.colutti.tour.exception.MovieNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class MovieServiceImpl: MovieService {

    @Autowired
    lateinit var movieRepository: MovieRepository
    @Autowired
    lateinit var movieConverter: MovieConverter
    @Autowired
    lateinit var personageConverter: PersonageConverter
    @Autowired
    lateinit var personageRelationShipService: PersonageRelationShipService

    @CacheEvict("movies", allEntries = true)
    override fun create(movieRequest: MovieRequest) {
        var movieToSave = this.movieConverter.requestToMovie(movieRequest)
        var savedMovie = movieRepository.save(movieToSave)
        movieToSave.personages.map { char ->
            char.movie = savedMovie
            personageRelationShipService.save(char)
        }
    }

    @Cacheable("movies")
    override fun getAll(): List<MovieResponse> {
        val movieList: List<Movie> = movieRepository.findAll().toList()
        return movieList.map { getMovie(it.id) }
    }

    @Cacheable("movies")
    override fun getById(id: Long): MovieResponse{
        val movie: Movie = movieRepository.findById(id).orElseGet { throw MovieNotFoundException("Movie ${id} not found") }
        val personagesList: List<PersonageRelationship> = personageRelationShipService.getByMovieId(id)
        return movieConverter.movieToResponse(movie, personageConverter.personageListToDto(personagesList))
    }

    @CacheEvict("movies", allEntries = true)
    override fun update(idRequest: Long, movieRequest: MovieRequest) {
        personageRelationShipService.deleteMovies(idRequest)
        var movieToUpdate = this.movieConverter.requestToMovie(movieRequest)
        movieToUpdate.id = idRequest
        println(movieToUpdate.personages)
        movieToUpdate.personages = movieToUpdate.personages.map { char ->
            PersonageRelationship(0, char.personage_id , movieToUpdate)
        }
        movieRepository.save(movieToUpdate)
    }

    override fun getMovie(id: Long): MovieResponse {
        val movie: Movie = movieRepository.findById(id).orElseGet { throw MovieNotFoundException("Movie ${id} not found") }
        val personagesList: List<PersonageRelationship> = personageRelationShipService.getByMovieId(id)
        return movieConverter.movieToResponse(movie, personageConverter.personageListToDto(personagesList))
    }

}

