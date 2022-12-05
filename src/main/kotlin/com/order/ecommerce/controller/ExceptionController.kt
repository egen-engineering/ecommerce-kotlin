package com.order.ecommerce.controller

import java.util.NoSuchElementException
import org.slf4j.LoggerFactory
import org.springdoc.api.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionController {

    private val logger = LoggerFactory.getLogger(ExceptionController::class.java)

    @ExceptionHandler(NoSuchElementException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun elasticExceptionHandler(e: NoSuchElementException): ErrorMessage {
        logger.warn("Entity not found", e)
        return ErrorMessage("Entity not found, ${e.message}")
    }
}
