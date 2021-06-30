package com.colutti.starwars.movies.dto.movie.converts

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.response.CharacterResponse
import com.colutti.starwars.movies.dto.movie.response.MovieResponse
import com.colutti.starwars.movies.model.CharactersRelationship
import com.colutti.starwars.movies.model.Movie
import org.springframework.stereotype.Component

@Component
class MovieConverter {

    fun requestToMovie(movieRequest: MovieRequest): Movie{
        var newList: List<CharactersRelationship> = movieRequest.characters.map { CharactersRelationship(0, it.character_id, null) };
        var movie = Movie(0, movieRequest.name, movieRequest.release_date, movieRequest.image_url, newList)
        return movie
    }

    fun movieToListResponse(movie: List<Movie>): List<MovieResponse>{
        var movieReponseList: List<MovieResponse> = movie.map { mapMovie ->
            MovieResponse(mapMovie.id, mapMovie.name, mapMovie.release_date, mapMovie.image_url,
                    mapMovie.characters.map { char -> CharacterResponse(char.character_id, "") })
        };
        return movieReponseList
    }

    fun movieToResponse(movie: Movie): MovieResponse{
        var movieReponse = MovieResponse(movie.id, movie.name, movie.release_date, movie.image_url,
        movie.characters.map { char -> CharacterResponse(char.character_id, "") })
        return movieReponse
    }

}
