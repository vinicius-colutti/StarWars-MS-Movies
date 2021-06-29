package com.colutti.starwars.movies.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity @Table(name="movie")
data class Movie (
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long = 0,
     var name: String = "",
     var release_date: Date = Date(),
     var image_url: String = "",

     @JsonProperty("characters")
     @OneToMany(mappedBy = "movie", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
     val characters: List<CharactersRelationship> = emptyList()

)

