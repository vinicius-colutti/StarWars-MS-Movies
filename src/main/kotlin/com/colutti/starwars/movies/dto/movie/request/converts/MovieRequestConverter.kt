package com.colutti.starwars.movies.dto.movie.request.converts

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.model.CharactersRelationship
import com.colutti.starwars.movies.model.Movie

class MovieRequestConverter {

    fun convertToMovie(movieRequest: MovieRequest): Movie{
        var newList: List<CharactersRelationship> = movieRequest.characters.map { CharactersRelationship(0, it.character_id, null) };
        var movie = Movie(0, movieRequest.name, movieRequest.release_date, movieRequest.image_url, newList)
        return movie
    }

}
