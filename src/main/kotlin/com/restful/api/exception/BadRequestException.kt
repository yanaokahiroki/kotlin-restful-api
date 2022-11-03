package com.restful.api.exception

/**
 * BadRequest例外
 *
 * @param message エラーメッセージ
 *
 * @author yanaokahiroki
 */
class BadRequestException(message: String) : BaseException(message)
