package com.colutti.starwars.movies.service

import com.colutti.starwars.movies.model.PersonageRelationship

interface PersonageRelationShipService {

    fun getByMovieId(id: Long): List<PersonageRelationship>
    fun save(personageRelationship: PersonageRelationship)
    fun deleteMovies(id: Long)

}