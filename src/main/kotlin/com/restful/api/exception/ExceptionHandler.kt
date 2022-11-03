package com.restful.api.exception

import com.restful.api.dto.ErrorResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

/**
 * 例外ハンドラー
 *
 * @author yanaokahiroki
 */
@RestControllerAdvice
class ExceptionHandler : ResponseEntityExceptionHandler() {

  @ExceptionHandler(NotFoundException::class)
  fun handleNotFoundException(exception: NotFoundException, request: WebRequest): ResponseEntity<Any> {
    val error = ErrorResponse(HttpStatus.NOT_FOUND.value(), exception.message)
    return super.handleExceptionInternal(exception, error, HttpHeaders(), HttpStatus.NOT_FOUND, request)
  }

  @ExceptionHandler(BadRequestException::class)
  fun handleBadRequestException(exception: BadRequestException, request: WebRequest): ResponseEntity<Any> {
    val error = ErrorResponse(HttpStatus.BAD_REQUEST.value(), exception.message)
    return super.handleExceptionInternal(exception, error, HttpHeaders(), HttpStatus.BAD_REQUEST, request)
  }

  @ExceptionHandler(Exception::class)
  fun handleAllException(exception: Exception, request: WebRequest): ResponseEntity<Any> {
    val error = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.message)
    return super.handleExceptionInternal(exception, error, HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request)
  }
}
