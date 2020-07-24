package ru.job4j.multithreading.resource_synchronization;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

import java.util.List;

@ThreadSafe
public class UserStorage {
    private volatile List<User> users = new ArrayList<>();

    public boolean add(User user) {
        return users.add(new User(user.getId(), user.getBalance()));
    }

    public boolean update(User user) {
        return delete(user) && add(user);
    }

    public boolean delete(User user) {
        return users.remove(findById(user.getId()));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = findById(fromId);
        User userTo = findById(toId);
        if (userFrom.withdraw(amount)) {
            userTo.deposit(amount);
            update(userFrom);
            update(userTo);
            return true;
        }
        return false;
    }

    public User findById(int id) {
        User rsl = null;
        for (User user : users) {
            if (user.getId() == id) {
                rsl = new User(user.getId(), user.getBalance());
                break;
            }
        }
        return rsl;
    }

    public List<User> findAll() {
        List<User> rsl = new ArrayList<>();
        for (User user : users) {
            rsl.add(new User(user.getId(), user.getBalance()));
        }
        return rsl;
    }
}
