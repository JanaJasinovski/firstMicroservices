package com.application.oauth.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table( name = "roles" )
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Role {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Enumerated( EnumType.STRING )
    @Column( name = "name" )
    private RoleEnum name;

    public Role(RoleEnum name) {
        this.name = name;
    }

    public String getRoleName() {
        return name.toString();
    }
}
