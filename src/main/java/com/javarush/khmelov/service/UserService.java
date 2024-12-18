package com.javarush.khmelov.service;

import com.javarush.khmelov.storage.repository.UserRepository;
import com.javarush.khmelov.entity.User;

import java.util.Collection;
import java.util.Optional;

public class UserService {



    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void create(User user) {
        userRepository.create(user);
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long id) {
        return userRepository.get(id);
    }

    public boolean isCorrectLoginPassword(String login, String password) {
        Collection<User> users = getAll();
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isExistLogin(String login){
        Collection<User> users = getAll();
        for (User user : users) {
            if (user.getLogin().equals(login)){
                return true;
            }
        }
        return false;
    }

}
