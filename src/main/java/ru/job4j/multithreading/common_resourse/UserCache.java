package ru.job4j.multithreading.common_resourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        List<User> rsl = new ArrayList<>();
        for (Map.Entry<Integer, User> entry : users.entrySet()) {
            User user = User.of(entry.getValue().getName());
            user.setId(entry.getKey());
            rsl.add(user);
        }
        return rsl;
    }
}
