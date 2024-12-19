package com.javarush.khmelov.repository;

import com.javarush.khmelov.entity.Role;
import com.javarush.khmelov.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserRepositoryTest {

    private final UserRepository userRepository = new UserRepository();
    private User admin;

    @BeforeEach
    void createAdmin() {
        admin = User.builder()
                .id(1L)
                .login("testAdmin")
                .password("testPassword")
                .role(Role.ADMIN)
                .build();
        userRepository.create(admin);
    }

    @Test
    void get() {
        User user = userRepository.get(1L);
        Assertions.assertEquals(admin, user);
    }


    @Test
    void find() {
        User pattern = User.builder().login("testAdmin").build();
        var userStream = userRepository.find(pattern);
        Assertions.assertEquals(admin, userStream.findFirst().orElseThrow());
    }

    @Test
    void update(){
        admin.setLogin("newLogin");
        userRepository.update(admin);
        User user = userRepository.get(1L);
        Assertions.assertEquals(admin, user);
    }

    @Test
    void delete(){
        userRepository.delete(admin);
        Assertions.assertEquals(0, userRepository.getAll().size());
    }
}