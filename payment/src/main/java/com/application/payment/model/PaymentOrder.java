package com.application.payment.model;

import com.application.payment.dto.AddressDto;
import com.application.payment.dto.CartItemDto;
import com.application.payment.dto.OrderDto;
import com.application.payment.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "payment_order")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class PaymentOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="order_tracking_number")
    private String orderTrackingNumber;

    @Column(name="customer")
    private UserDto customer;

    @Column(name="shipping_address")
    private AddressDto shippingAddress;

    @Column(name="order")
    private OrderDto order;

    @Column(name="order_items")
    private List<CartItemDto> orderItems;

    @Column(name="payment_attempts")
    private List<PaymentAttempt> paymentAttempts;
}
