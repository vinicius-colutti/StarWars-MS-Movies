package com.colutti.starwars.movies.repository

import com.colutti.starwars.movies.model.CharactersRelationship
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.data.repository.query.Param
import javax.transaction.Transactional

interface CharactersRelationshipRepository: PagingAndSortingRepository<CharactersRelationship, Long> {

    @Modifying
    @Transactional
    @Query(value="DELETE FROM characters_relationship WHERE movie_id = ?1", nativeQuery = true)
    fun deleteMovies(movie_id: Long)

}