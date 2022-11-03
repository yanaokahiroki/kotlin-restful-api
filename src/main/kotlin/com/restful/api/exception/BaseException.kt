package com.restful.api.exception

/**
 * 基底例外
 *
 * 独自例外は必ずこのクラスを継承する
 *
 * @param message エラーメッセージ
 *
 * @author yanaokahiroki
 */
abstract class BaseException(message: String) : RuntimeException(message)
