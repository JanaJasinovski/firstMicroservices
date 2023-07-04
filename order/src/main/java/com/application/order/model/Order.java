package com.application.order.model;

import com.application.order.dto.AddressDto;
import com.application.order.dto.CartItemDto;
import com.application.order.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@ToString
@Builder
public class Order {
    @Id
    private String id;

    private Long totalAmount;

    private BigDecimal totalPrice;

    private String orderStatus;

    @CreationTimestamp
    private String dateCreated;

    @UpdateTimestamp
    private String deliveredDate;

    private Long userId;

    private List<CartItemDto> cartItems;
}
