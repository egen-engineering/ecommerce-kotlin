package com.order.ecommerce.integrationtest

import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@AutoConfigureEmbeddedDatabase
@AutoConfigureMockMvc
@Sql("/product/insert_prerequisite_records.sql")
internal class ProductIntegrationTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun testGetProduct() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/products/106")
        )
            .andExpect(status().`is`(200))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(
                content().json(
                    """
                        {
                          "productId": "106",
                          "sku": "1006",
                          "title": "SoftDrink",
                          "description": "Coke",
                          "price": 5.99,
                          "createdAt": "2022-10-17"
                        }
                    """.trimIndent()
                )
            )
            .andReturn()
    }

}
