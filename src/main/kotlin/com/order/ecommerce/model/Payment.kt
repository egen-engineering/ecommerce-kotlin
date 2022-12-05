package com.order.ecommerce.model

import com.order.ecommerce.enum.PaymentStatus
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "ecommerce_payment")
class Payment(

    @Id
    @Column(name = "payment_id", nullable = false, unique = true)
    private var paymentId: String,

    @Column(name = "amount", nullable = false)
    private var amount: Double,

    @Column(name = "payment_mode", nullable = false)
    private var paymentMode: String,

    @Column(name = "confirmation_number", nullable = false)
    private var confirmationNumber: String,

    @Column(name = "payment_status", nullable = false)
    private var paymentStatus: String,

    @Column(name = "createdAt", nullable = false)
    private var createdAt: LocalDate,

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "payment")
    private var order: Order?

) : Serializable {

    constructor(amount: Double, paymentMode: String) : this(
        UUID.randomUUID().toString(),
        amount,
        paymentMode,
        UUID.randomUUID().toString(),
        PaymentStatus.PROCESSING.name,
        LocalDate.now(),
        null
    )
}
