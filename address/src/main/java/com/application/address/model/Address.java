package com.application.address.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table( name = "address" )
@Data
public class Address {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @Column( name = "id" )
    private Long id;

    @Column(name="country")
    private String country;

    @Column(name="state")
    private String state;

    @Column( name = "city" )
    private String city;

    @Column( name = "street" )
    private String street;

    @Column( name = "zip_code" )
    private String zipCode;

    @Column(name = "user_id")
    private Long userId;

}