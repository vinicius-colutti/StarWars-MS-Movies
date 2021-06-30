package com.colutti.starwars.movies.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*
import javax.persistence.*

@Entity @Table(name="movie")
data class Movie (
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     var id: Long = 0,

     var name: String = "",

     @JsonFormat(pattern="yyyy-MM-dd")
     @Temporal(TemporalType.TIMESTAMP)
     var release_date: Date = Date(),

     var image_url: String = "",

     @JsonProperty("characters")
     @OneToMany(mappedBy = "movie", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
     var characters: List<CharactersRelationship> = emptyList()

)

