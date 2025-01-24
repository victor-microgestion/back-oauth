package io.openliberty.guides.services;

import io.openliberty.guides.models.User;
import io.openliberty.guides.repositories.UserRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    // CREATE
    public void saveUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
            System.out.println("Usuario guardado: " + user.getEmail());
        } else {
            System.out.println("El usuario ya existe: " + user.getEmail());
        }
    }

    // READ ALL
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // READ BY EMAIL
    public User userExists(String email) {
        return userRepository.findByEmail(email);
    }

    // READ BY ID
    public User getUserById(String id) {
        User user = userRepository.findById(id);
        if (user != null) {
            System.out.println("Usuario encontrado: " + id);
        } else {
            System.out.println("Usuario no encontrado: " + id);
        }
        return user;
    }

    // UPDATE
    public boolean updateUser(String email, User updatedUser) {
        boolean isUpdated = userRepository.update(email, updatedUser);
        if (isUpdated) {
            System.out.println("Usuario actualizado: " + email);
        } else {
            System.out.println("No se encontró el usuario para actualizar: " + email);
        }
        return isUpdated;
    }

    // DELETE BY EMAIL
    public boolean deleteUserByEmail(String email) {
        boolean isDeleted = userRepository.deleteByEmail(email);
        if (isDeleted) {
            System.out.println("Usuario eliminado: " + email);
        } else {
            System.out.println("No se encontró el usuario para eliminar: " + email);
        }
        return isDeleted;
    }

    // DELETE BY ID
    public boolean deleteUserById(String id) {
        boolean isDeleted = userRepository.deleteById(id);
        if (isDeleted) {
            System.out.println("Usuario eliminado por ID: " + id);
        } else {
            System.out.println("No se encontró el usuario para eliminar por ID: " + id);
        }
        return isDeleted;
    }
}
