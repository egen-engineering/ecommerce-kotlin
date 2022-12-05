package com.order.ecommerce.service

import com.order.ecommerce.dto.ProductCreateResponse
import com.order.ecommerce.dto.ProductDto

interface ProductService {
    fun createProduct(productDto: ProductDto): ProductCreateResponse
    fun getProduct(productId: String): ProductDto
}
