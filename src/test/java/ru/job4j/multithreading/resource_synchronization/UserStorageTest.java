package ru.job4j.multithreading.resource_synchronization;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class UserStorageTest {

    @Test
    public void whenTransferMoney() {
        UserStorage userStorage = new UserStorage();
        User user1 = new User(1, 1000);
        User user2 = new User(2, 0);
        User user3 = new User(3, 0);
        userStorage.add(user1);
        userStorage.add(user2);
        userStorage.add(user3);
        Thread thread1 = new Thread(() -> userStorage.transfer(1, 2, 10));
        Thread thread2 = new Thread(() -> userStorage.transfer(1, 3, 10));
        Thread thread3 = new Thread(() -> userStorage.transfer(1, 3, 15));
        Thread thread4 = new Thread(() -> userStorage.transfer(1, 2, 5));
        Thread thread5 = new Thread(() -> userStorage.transfer(1, 2, 15));
        Thread thread6 = new Thread(() -> userStorage.transfer(1, 2, 15));
        Thread thread7 = new Thread(() -> userStorage.transfer(1, 2, 15));
        Thread thread8 = new Thread(() -> userStorage.transfer(1, 2, 15));
        Thread thread9 = new Thread(() -> userStorage.transfer(1, 3, 15));
        Thread thread10 = new Thread(() -> userStorage.transfer(1, 3, 15));
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
        thread7.start();
        thread8.start();
        thread9.start();
        thread10.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(userStorage.findById(1).getBalance(), is(870));
        assertThat(userStorage.findById(2).getBalance(), is(75));
        assertThat(userStorage.findById(3).getBalance(), is(55));
    }

    @Test
    public void whenAddShouldReturnTrue(){
        UserStorage userStorage = new UserStorage();
        User user1 = new User(1, 1000);
        assertTrue(userStorage.add(user1));
    }

    @Test
    public void whenDeleteUser(){
        UserStorage userStorage = new UserStorage();
        User user1 = new User(1, 1000);
        User user2 = new User(2, 1000);
        userStorage.add(user1);
        userStorage.add(user2);
        userStorage.delete(user1);
        assertThat(userStorage.findAll().size(), is(1));
    }
}