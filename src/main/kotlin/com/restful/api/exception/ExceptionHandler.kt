package com.restful.api.exception

import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

class ExceptionHandler : ResponseEntityExceptionHandler() {

  @ExceptionHandler(BadRequestException::class)
  fun handleBadRequestException(exception: BadRequestException) {

  }
}
