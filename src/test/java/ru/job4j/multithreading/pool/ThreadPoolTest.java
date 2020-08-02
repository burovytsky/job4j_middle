package ru.job4j.multithreading.pool;

import org.junit.Test;

public class ThreadPoolTest {

    @Test
    public void work() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool(5);
        for (int i = 0; i < 5; i++) {
            threadPool.work(new Task(i));
        }
    }
}