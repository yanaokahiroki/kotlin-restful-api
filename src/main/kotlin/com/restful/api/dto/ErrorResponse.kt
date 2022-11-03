package com.restful.api.dto

/**
 * エラーレスポンス
 *
 * @property status HTTPステータス
 * @property message エラーメッセージ
 * @property errorDetailList エラー詳細リスト
 */
data class ErrorResponse(
  var status: Int,
  var message: String? = null
)
