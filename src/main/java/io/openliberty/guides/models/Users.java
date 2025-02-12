package io.openliberty.guides.models;

import jakarta.nosql.Column;
import jakarta.nosql.Entity;
import jakarta.nosql.Id;

@Entity
public class Users {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String provider;

    @Column
    private String role;

    // Getters y setters
}
