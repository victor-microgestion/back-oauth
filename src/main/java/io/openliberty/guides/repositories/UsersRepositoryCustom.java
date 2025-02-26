package io.openliberty.guides.repositories;

public interface UsersRepositoryCustom {
    boolean updateByEmail(String email, String name, String emailNuevo, String provider, String role);
}