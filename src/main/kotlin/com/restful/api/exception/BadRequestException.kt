package com.restful.api.exception

import org.springframework.http.HttpStatus

class BadRequestException : BaseException {
  constructor(
    message: String,
  ) : super(
    status = HttpStatus.BAD_REQUEST,
    message = message
  )

  constructor(
    message: String,
    cause: Exception
  ) : super(
    status = HttpStatus.BAD_REQUEST,
    message = message,
    cause = cause
  )
}
