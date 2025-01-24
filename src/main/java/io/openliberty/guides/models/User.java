package io.openliberty.guides.models;

import org.bson.types.ObjectId;

public class User {
    private ObjectId id; // El identificador único de MongoDB
    private String name;
    private String email;
    private String provider; // Ejemplo: "google" o "github"
    private String role; // Rol del usuario (admin, ejecutivo, etc.)

    // Constructor vacío para serialización/deserialización
    public User() {}

    // Constructor con parámetros
    public User(String name, String email, String provider, String role) {
        this.name = name;
        this.email = email;
        this.provider = provider;
        this.role = role;
    }

    // Getters y setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", provider='" + provider + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
