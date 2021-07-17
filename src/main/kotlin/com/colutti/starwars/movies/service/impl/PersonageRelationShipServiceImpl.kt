package com.colutti.starwars.movies.service.impl

import com.colutti.starwars.movies.model.PersonageRelationship
import com.colutti.starwars.movies.repository.PersonageRelationshipRepository
import com.colutti.starwars.movies.service.PersonageRelationShipService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class PersonageRelationShipServiceImpl: PersonageRelationShipService {

    @Autowired
    lateinit var personageRelationshipRepository: PersonageRelationshipRepository

    override fun getByMovieId(id: Long): List<PersonageRelationship> {
        return personageRelationshipRepository.findByMovieId(id).toList()
    }

    @CacheEvict("movies", allEntries = true)
    override fun save(personageRelationship: PersonageRelationship) {
        personageRelationshipRepository.save(personageRelationship)
    }

    @CacheEvict("movies", allEntries = true)
    override fun deleteMovies(id: Long) {
        personageRelationshipRepository.deleteMovies(id)
    }

}