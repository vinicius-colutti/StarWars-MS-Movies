package com.colutti.starwars.movies.service

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.response.MovieResponse
import com.colutti.starwars.movies.model.Movie


interface MovieService {
    fun create(movieRequest: MovieRequest)
    fun getAll(): List<MovieResponse>
    fun getById(id: Long): MovieResponse
    fun update(id: Long, movieRequest: MovieRequest)
    fun getMovie(id: Long): MovieResponse
}