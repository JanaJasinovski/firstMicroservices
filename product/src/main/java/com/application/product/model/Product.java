package com.application.product.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "products" )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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

    @Column( name = "username", nullable = true )
    private String username;

    public Product(String name, BigDecimal price, Long amount, String username) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.username = username;
    }
}
