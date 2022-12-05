package com.order.ecommerce.mapper

import com.order.ecommerce.dto.AddressDto
import com.order.ecommerce.enum.PaymentStatus
import com.order.ecommerce.model.Address
import com.order.ecommerce.model.Payment
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.UUID

@Component
class OrderDetailsMapper {

    fun buildPayment(amount: Double, paymentMode: String): Payment = Payment(
        UUID.randomUUID().toString(),
        amount,
        paymentMode,
        UUID.randomUUID().toString(),
        PaymentStatus.PROCESSING.name,
        LocalDate.now(),
        null
    )

    fun buildAddress(addressDto: AddressDto): Address = with(addressDto) {
        Address(
            addressId = UUID.randomUUID().toString(),
            address1 = address1,
            address2 = address2,
            city = city,
            state = state,
            zip = zip,
            email = email,
            phone = phone,
            createdAt = LocalDate.now(),
            order = null
        )
    }
}
