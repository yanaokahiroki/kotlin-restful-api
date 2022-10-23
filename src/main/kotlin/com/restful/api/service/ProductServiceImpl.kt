package com.restful.api.service

import com.restful.api.dto.ProductDto
import com.restful.api.form.ProductForm
import com.restful.api.model.Product
import com.restful.api.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.Optional

/**
 * 商品サービス
 *
 * @author yanaokahiroki
 */
@Service
class ProductServiceImpl(private val productRepository: ProductRepository) : ProductService {

  @Override
  override fun registerProduct(productRequestBody: ProductForm): ProductDto {
    productRepository.findByTitle(productRequestBody.title)?.let { throw Exception("すでに登録済みのタイトルです") }
    val product = Product(productRequestBody.title, productRequestBody.body, productRequestBody.price)
    return ProductDto(productRepository.save(product))
  }

  @Override
  override fun findAll(): List<ProductDto> {
    val productList: List<Product> = productRepository.findAll()
    return productList.stream().map { product -> ProductDto(product) }.toList()
  }

  @Override
  override fun find(id: Int): ProductDto {
    val product: Product = findById(id)
    return ProductDto(product)
  }

  @Override
  override fun updateProduct(id: Int, productForm: ProductForm): ProductDto {
    val targetProduct: Product = findById(id)
    targetProduct.title = productForm.title
    targetProduct.body = productForm.body
    targetProduct.price = productForm.price
    return ProductDto(productRepository.save(targetProduct))
  }

  @Override
  override fun deleteProduct(id: Int): Unit = productRepository.deleteById(id)

  /**
   * 商品IDで商品を取得する
   *
   * @param id 商品ID
   * @return 商品情報
   */
  private fun findById(id: Int): Product {
    val product: Optional<Product> = productRepository.findById(id)
    if (product.isEmpty) {
      throw Exception("IDの商品はありません。")
    }
    return product.get()
  }
}
