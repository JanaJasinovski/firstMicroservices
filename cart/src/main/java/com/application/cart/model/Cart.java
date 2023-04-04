package com.application.cart.model;

import com.application.cart.dto.ProductDto;
import com.application.cart.dto.UserDto;
import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document( collection = "cart" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    @Id
    private String  id;
    private String username;
    private String productName;
    private Integer amount;
    private BigDecimal totalPrice;

    public Cart(String username, String productName, Integer amount, BigDecimal totalPrice) {
        this.username = username;
        this.productName = productName;
        this.amount = amount;
        this.totalPrice = totalPrice;
    }
}
