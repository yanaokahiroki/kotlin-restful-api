package com.restful.api.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
  val status: HttpStatus,
  message: String,
  cause: Exception? = null
) : RuntimeException(message, cause)
