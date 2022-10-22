package com.restful.api.service

import com.restful.api.dto.ProductDto
import com.restful.api.form.ProductForm

/**
 * 商品サービスインターフェイス
 *
 * @author yanaokahiroki
 */
interface ProductService {

  /**
   * 商品情報を登録する
   *
   * @param productRequestBody 商品
   * @return 登録した商品情報
   */
  fun registerProduct(productRequestBody: ProductForm): ProductDto

  /**
   * 商品情報を全件取得する
   *
   * @return 商品情報リスト
   */
  fun findAll(): List<ProductDto>

  /**
   * IDに一致する商品情報を取得する
   *
   * @param id 商品ID
   * @return 商品情報
   */
  fun find(id: Int): ProductDto

  /**
   * 商品情報を更新する
   *
   * @param id 商品ID
   * @param productForm 商品フォーム
   * @return 更新後の商品情報
   */
  fun updateProduct(id: Int, productForm: ProductForm): ProductDto

  /**
   * 商品情報を削除する
   *
   * @param id 商品ID
   */
  fun deleteProduct(id: Int)
}
