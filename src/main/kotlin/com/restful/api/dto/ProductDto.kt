package com.restful.api.dto

import com.restful.api.model.Product
import java.time.LocalDateTime

class ProductDto(product: Product) {
  val id: Int = product.id
  val title: String = product.title
  val body: String = product.body
  val price: Int = product.price
  val createdAt: LocalDateTime = product.createdAt
  val updatedAt: LocalDateTime = product.updatedAt
}
