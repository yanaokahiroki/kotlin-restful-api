package com.restful.api.form

/**
 * 商品登録リクエストボディ
 *
 * @property title 商品名
 * @property body 商品詳細
 * @property price 商品価格
 *
 * @author yanaokahiroki
 */
data class ProductForm(
  val title: String,
  val body: String,
  val price: Int
)
