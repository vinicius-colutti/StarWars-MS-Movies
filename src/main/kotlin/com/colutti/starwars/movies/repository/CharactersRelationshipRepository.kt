package com.colutti.starwars.movies.repository

import com.colutti.starwars.movies.model.CharactersRelationship
import org.springframework.data.repository.PagingAndSortingRepository

interface CharactersRelationshipRepository: PagingAndSortingRepository<CharactersRelationship, Long> {
}