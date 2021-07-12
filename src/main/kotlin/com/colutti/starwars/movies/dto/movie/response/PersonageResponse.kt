package com.colutti.starwars.movies.dto.movie.response

data class PersonageResponse(
        var id: Long,
        var name: String,
        var birth: String,
        var death: String,
        var species: String,
        var url_image: String,
        var description: String
)