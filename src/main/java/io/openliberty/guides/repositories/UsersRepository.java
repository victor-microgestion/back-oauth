package io.openliberty.guides.repositories;

import java.util.List;

import org.bson.types.ObjectId;

import io.openliberty.guides.models.Users;
import jakarta.data.repository.BasicRepository;
import jakarta.data.repository.Repository;

@Repository
public interface UsersRepository extends BasicRepository<Users, String> {
    Users findByEmail(String email);
}