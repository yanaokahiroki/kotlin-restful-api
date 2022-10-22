package com.restful.api.repository

import com.restful.api.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository : JpaRepository<Product, Int> {
  @Query("SELECT * FROM Products WHERE title = ?1", nativeQuery = true)
  fun findByTitle(title: String): Product?
}
