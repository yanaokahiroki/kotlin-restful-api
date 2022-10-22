package com.restful.api.controller

import com.restful.api.dto.ProductDto
import com.restful.api.form.ProductForm
import com.restful.api.service.ProductServiceImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/products")
class ProductController(private val productServiceImpl: ProductServiceImpl) {

  @PostMapping
  fun register(@RequestBody productRequestBody: ProductForm): ResponseEntity<ProductDto> =
    ResponseEntity(productServiceImpl.registerProduct(productRequestBody), HttpStatus.CREATED)

  @GetMapping
  fun findAll(): ResponseEntity<List<ProductDto>> = ResponseEntity(productServiceImpl.findAll(), HttpStatus.OK)

  @GetMapping("{id}")
  fun find(@PathVariable id: Int): ResponseEntity<ProductDto> {
    return ResponseEntity(productServiceImpl.find(id), HttpStatus.OK)
  }

  @PostMapping("{id}")
  fun update(@PathVariable id: Int, @RequestBody productRequestBody: ProductForm): ResponseEntity<ProductDto> =
    ResponseEntity(productServiceImpl.updateProduct(id, productRequestBody), HttpStatus.OK)


  @DeleteMapping("{id}")
  fun delete(@PathVariable id: Int): ResponseEntity<Unit> =
    ResponseEntity(productServiceImpl.deleteProduct(id), HttpStatus.NO_CONTENT)
}
