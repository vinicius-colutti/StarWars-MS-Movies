package com.colutti.starwars.movies.repository

import com.colutti.starwars.movies.model.Movie
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface MovieRepository: PagingAndSortingRepository<Movie, Long> {
}