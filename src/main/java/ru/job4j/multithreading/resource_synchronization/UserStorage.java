package ru.job4j.multithreading.resource_synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;

import java.util.List;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<User> users = new ArrayList<>();

    public synchronized boolean add(User user) {
        if (findById(user.getId()) == null) {
            users.add(new User(user.getId(), user.getBalance()));
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user) {
        return delete(user) && add(user);
    }

    public synchronized boolean delete(User user) {
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

    public synchronized User findById(int id) {
        User rsl = null;
        for (User user : users) {
            if (user.getId() == id) {
                rsl = new User(user.getId(), user.getBalance());
                break;
            }
        }
        return rsl;
    }

    public synchronized List<User> findAll() {
        List<User> rsl = new ArrayList<>();
        for (User user : users) {
            rsl.add(new User(user.getId(), user.getBalance()));
        }
        return rsl;
    }

//    @GuardedBy("this")
//    private List<User> users = new ArrayList<>();
//
//    public synchronized boolean add(User user) {
//        if (findById(user.getId()) == null) {
//            users.add(user);
//            return true;
//        }
//        return false;
//    }
//
//    public synchronized boolean update(User user) {
//        return delete(user) && add(user);
//    }
//
//    public synchronized boolean delete(User user) {
//        return users.remove(findById(user.getId()));
//    }
//
//    public synchronized boolean transfer(int fromId, int toId, int amount) {
//        User userFrom = findById(fromId);
//        User userTo = findById(toId);
//        if (userFrom.withdraw(amount) && userTo != null) {
//            userTo.deposit(amount);
//            update(userFrom);
//            update(userTo);
//            return true;
//        }
//        return false;
//    }
//
//    public synchronized User findById(int id) {
//        User rsl = null;
//        for (User user : users) {
//            if (user.getId() == id) {
//                return user;
//            }
//        }
//        return rsl;
//    }
//
//    public synchronized List<User> findAll() {
//        List<User> rsl = new ArrayList<>();
//        for (User user : users) {
//            rsl.add(new User(user.getId(), user.getBalance()));
//        }
//        return rsl;
//    }
}
