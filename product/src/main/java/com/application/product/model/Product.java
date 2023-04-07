package com.application.product.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table( name = "products" )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "name", unique = true, nullable = false, length = 100 )
    private String name;

    @Column( name = "price", nullable = false )
    private BigDecimal price;

    @Column( name = "amount", nullable = false )
    private Long amount;

    @Column( name = "user_id", nullable = false)
    private Long userId;

    public Product(String name, BigDecimal price, Long amount, Long userId) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.userId = userId;
    }
}
