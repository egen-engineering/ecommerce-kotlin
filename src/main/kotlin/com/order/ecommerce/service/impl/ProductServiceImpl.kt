package com.order.ecommerce.service.impl

import com.order.ecommerce.dto.ProductCreateResponse
import com.order.ecommerce.dto.ProductDto
import com.order.ecommerce.mapper.EntityMapper
import com.order.ecommerce.model.Product
import com.order.ecommerce.repository.ProductRepository
import com.order.ecommerce.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.UUID

@Service
class ProductServiceImpl(
    private val productRepository: ProductRepository,
    private val mapper: EntityMapper
) : ProductService {

    private val log = LoggerFactory.getLogger(this::class.java)

    override fun createProduct(productDto: ProductDto): ProductCreateResponse {
        val product = productRepository.save(productDto.toProductEntity())
        log.info("Creating Product with productId {}", product.productId)
        return ProductCreateResponse(product.productId)
    }

    override fun getProduct(productId: String): ProductDto {
        log.info("Get Product by productId {}", productId)
        val product = productRepository.findById(productId).orElseThrow()
        return mapper.toProductDto(product)
    }

    private fun ProductDto.toProductEntity() = Product(
        productId = UUID.randomUUID().toString(),
        sku = sku,
        title = title,
        description = description,
        price = price,
        createdAt = LocalDate.now(),
        orderItems = null
    )
}
