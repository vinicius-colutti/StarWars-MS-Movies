package com.colutti.starwars.movies.advice

import com.colutti.tour.exception.MovieNotFoundException
import com.colutti.tour.model.exceptions.ErrorMessage
import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.lang.Exception
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(JsonParseException::class)
    fun JsonFormatExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Error", exception.message ?: "error"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MovieNotFoundException::class)
    fun MovieNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception):
            ResponseEntity<ErrorMessage>{
        return ResponseEntity(ErrorMessage("Not Found", exception.message ?: "error"), HttpStatus.NOT_FOUND)
    }

}