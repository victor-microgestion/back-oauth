package io.openliberty.guides.repositories;

import io.openliberty.guides.models.Users;
import jakarta.data.repository.DataRepository;
import jakarta.data.repository.Repository;

@Repository
public interface UsersRepository extends DataRepository<Users, String> {
}