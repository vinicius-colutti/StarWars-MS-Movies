package com.colutti.starwars.movies.dto.movie.response

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class MovieResponse (
    var id: Long,
    var name: String,
    var release_date: Date,
    var image_url: String,

    @JsonProperty("personages")
    var personageResponse: List<PersonageResponse>
)