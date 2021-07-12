package com.colutti.starwars.movies.model

import javax.persistence.*

@Entity
@Table(name="personage_relationship")
data class PersonageRelationship(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,

        var personage_id: Long = 1,

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "movie_id")
        var movie: Movie? = null,
)