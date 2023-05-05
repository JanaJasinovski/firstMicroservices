package com.application.order.model;

import com.application.order.dto.CartItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Document( collection = "order" )
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    private String id;

    private Long totalAmount;

    private BigDecimal totalPrice;

    private String orderStatus;

    @CreationTimestamp
    private Date dateCreated;

    @UpdateTimestamp
    private Date deliveredDate;

    private Long userId;

    private List<CartItemDto> cartItems;
}
