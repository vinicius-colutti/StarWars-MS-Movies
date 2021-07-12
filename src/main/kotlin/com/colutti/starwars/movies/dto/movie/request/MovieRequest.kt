package com.colutti.starwars.movies.dto.movie.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class MovieRequest (
    var name: String,
    var release_date: Date,
    var image_url: String,

    @JsonProperty("personages")
    var personages: List<PersonageRequest>
)