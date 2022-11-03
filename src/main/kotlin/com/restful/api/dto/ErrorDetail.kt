package com.restful.api.dto

/**
 * エラー詳細
 *
 * @property key エラーとなったプロパティ
 * @property detail エラー詳細
 */
class ErrorDetail(private var key: String, private var detail: String)
