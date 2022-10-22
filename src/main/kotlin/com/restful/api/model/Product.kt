package com.restful.api.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * 商品テーブル
 *
 * @property id ID
 * @property title 商品名
 * @property body 商品詳細
 * @property price 商品価格
 * @property createdAt 作成日時
 * @property updatedAt 更新日時
 *
 * @author yanaokahiroki
 */
@Entity
@Table(name = "products")
class Product() {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  var id: Int = 0

  @Column(nullable = false, unique = true)
  var title: String = ""

  @Column(nullable = false)
  var body: String = ""

  @Column(nullable = false)
  var price: Int = 0

  @CreationTimestamp
  @Column(nullable = false, updatable = true)
  var createdAt: LocalDateTime = LocalDateTime.now()

  @UpdateTimestamp
  @Column(nullable = false, updatable = true)
  var updatedAt: LocalDateTime = LocalDateTime.now()

  constructor(title: String, body: String, price: Int) : this() {
    this.title = title
    this.body = body
    this.price = price
  }
}
