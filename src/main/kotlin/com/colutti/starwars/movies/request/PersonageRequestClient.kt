package com.colutti.starwars.movies.request

import com.colutti.starwars.movies.dto.movie.response.PersonageResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class PersonageRequestClient {

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Value("\${request.personage.url}")
    val personageUrl: String? = null

    fun getPersonage(personage_id: Long): PersonageResponse {
        val httpHeaders = HttpHeaders()
        httpHeaders.contentType = MediaType.APPLICATION_JSON
        return restTemplate.getForObject(personageUrl+"/"+personage_id, PersonageResponse::class.java)!!
    }
}