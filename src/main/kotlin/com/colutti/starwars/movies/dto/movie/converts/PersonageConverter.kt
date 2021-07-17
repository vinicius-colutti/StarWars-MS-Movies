package com.colutti.starwars.movies.dto.movie.converts

import com.colutti.starwars.movies.dto.movie.response.PersonageResponse
import com.colutti.starwars.movies.model.PersonageRelationship
import org.springframework.stereotype.Component

@Component
class PersonageConverter {

    fun personageListToDto(personageRelationship: List<PersonageRelationship>): List<PersonageResponse> {
        return personageRelationship.map { PersonageResponse(it.personage_id) }
    }

}