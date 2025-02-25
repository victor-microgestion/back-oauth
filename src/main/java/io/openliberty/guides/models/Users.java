package io.openliberty.guides.models;

import org.bson.types.ObjectId;
import org.eclipse.jnosql.databases.mongodb.mapping.ObjectIdConverter;

import jakarta.json.bind.annotation.JsonbTypeAdapter;
import jakarta.nosql.Column;
import jakarta.nosql.Convert;
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
    public ObjectId getId() {
        return new ObjectId(id);
    }

    public void setId(ObjectId id) {
        this.id = id.toHexString();
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
}
