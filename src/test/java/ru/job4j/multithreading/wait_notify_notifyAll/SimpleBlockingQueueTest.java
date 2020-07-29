package ru.job4j.multithreading.wait_notify_notifyAll;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void poll() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread consumer = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            for (int i = 0; i < 4; i++) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer");
        Thread producer = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");

            for (int i = 0; i < 4; i++) {
                try {
                    queue.offer(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Producer");
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}