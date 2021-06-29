package com.colutti.starwars.movies.service

import com.colutti.starwars.movies.dto.movie.request.MovieRequest


interface MovieService {
    fun create(movieRequest: MovieRequest)
}