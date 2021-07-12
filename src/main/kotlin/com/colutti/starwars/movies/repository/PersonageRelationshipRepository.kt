package com.colutti.starwars.movies.repository

import com.colutti.starwars.movies.model.PersonageRelationship
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import javax.transaction.Transactional

interface PersonageRelationshipRepository: PagingAndSortingRepository<PersonageRelationship, Long> {

    @Modifying
    @Transactional
    @Query(value="DELETE FROM personage_relationship WHERE movie_id = ?1", nativeQuery = true)
    fun deleteMovies(movie_id: Long)

    @Query(value="SELECT * FROM personage_relationship p WHERE p.movie_id = ?1", nativeQuery = true)
    fun findByMovieId(movie_id: Long): Iterable<PersonageRelationship>

}