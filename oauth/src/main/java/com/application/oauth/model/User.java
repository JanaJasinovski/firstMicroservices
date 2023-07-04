package com.application.oauth.model;

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
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "users" )
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Column( name = "email", unique = true, nullable = false, length = 100 )
    @Email
    @NotEmpty
    private String email;

    @Column( name = "first_name", nullable = false, length = 100 )
    @NotEmpty
    private String firstName;
    @Column( name = "last_name", nullable = false, length = 100 )
    @NotEmpty
    private String lastName;
    @Column( name = "password", nullable = false )
    @NotEmpty
    private String password;

    @ManyToMany( fetch = FetchType.EAGER, cascade = CascadeType.PERSIST )
    @JoinTable( name = "user_roles",
            joinColumns = {@JoinColumn( name = "user_id", referencedColumnName = "id" )},
            inverseJoinColumns = {@JoinColumn( name = "role_id", referencedColumnName = "id" )} )
    private List<Role> roles = new ArrayList<>();

}