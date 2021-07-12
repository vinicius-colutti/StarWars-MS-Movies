package com.colutti.starwars.movies.dto.movie.converts

import com.colutti.starwars.movies.dto.movie.request.MovieRequest
import com.colutti.starwars.movies.dto.movie.response.PersonageResponse
import com.colutti.starwars.movies.dto.movie.response.MovieResponse
import com.colutti.starwars.movies.model.PersonageRelationship
import com.colutti.starwars.movies.model.Movie
import org.springframework.stereotype.Component

@Component
class MovieConverter {

    fun requestToMovie(movieRequest: MovieRequest): Movie{
        var newList: List<PersonageRelationship> = movieRequest.personages.map { PersonageRelationship(0, it.personage_id, null) };
        var movie = Movie(0, movieRequest.name, movieRequest.release_date, movieRequest.image_url, newList)
        return movie
    }

    fun movieToListResponse(movie: List<Movie>): List<MovieResponse>{
        var movieReponseList: List<MovieResponse> = movie.map { mapMovie ->
            MovieResponse(mapMovie.id, mapMovie.name, mapMovie.release_date, mapMovie.image_url,
                    mapMovie.personages.map { char -> PersonageResponse(char.personage_id, "", "", "", "", "", "") })
        };
        return movieReponseList
    }

    fun movieToResponse(movie: Movie, personageResponse: List<PersonageResponse>): MovieResponse{
        return MovieResponse(movie.id, movie.name, movie.release_date, movie.image_url, personageResponse)
    }

}
