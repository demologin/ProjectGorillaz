package com.javarush.khmelov.storage.repository;

import com.javarush.khmelov.entity.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository implements Repository<User> {

    public static final AtomicLong id = new AtomicLong(System.currentTimeMillis());
    private final Map<Long, User> map = new HashMap<>();

    public UserRepository() {
        map.put(1L, new User(1L, "Ivanov", "qwerty", 10, 1, 9));
        map.put(2L, new User(2L, "JakVelnev", "", 4, 2, 2));
        map.put(3L, new User(3L, "Buynov", "admin", 7, 3, 4));
        map.put(4L, new User(4L, "Khmelov", "admin", 777, 777, 0));
    }

    @Override
    public Collection<User> getAll() {
        return map.values();
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(map.get(id));
    }

    @Override
    public void create(User entity) {
        entity.setId(id.incrementAndGet());
        entity.setGamesCount(0);
        entity.setWinsCount(0);
        entity.setLossCount(0);
        update(entity);
    }

    @Override
    public void update(User entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(User entity) {
        map.remove(entity.getId());
    }
}
