package com.javarush.shakhurov.service;

import com.javarush.shakhurov.model.User;
import com.javarush.shakhurov.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService {

    public static List<User> getAll() {
        try {
            return UserRepository.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<User> findById(Long id) {
        try {
            return UserRepository.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Optional<User> findByEmail(String email) {
        try {
            return UserRepository.findByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean existByEmail(String email) {
        try {
            return UserRepository.existByEmail(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Long create(User user) {
        try {
            return UserRepository.save(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(Long id) {
        try {
            UserRepository.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
