package com.restful.api.exception

/**
 * NotFound例外
 *
 * @param message エラーメッセージ
 *
 * @author yanaokahiroki
 */
class NotFoundException(message: String) : BaseException(message)
