package ru.job4j.multithreading.wait_notify_notifyAll;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class CountBarrierTest {
    @Test
    public void count() throws InterruptedException {
        CountBarrier countBarrier = new CountBarrier(2);
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            countBarrier.await();
        }, "First Thread");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start");
            countBarrier.count();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            countBarrier.count();
        }, "Second Thread");
        thread1.start();
        thread2.start();
        thread1.join();
        thread1.join();
    }
}