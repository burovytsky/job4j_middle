package ru.job4j.multithreading.wait_notify_notifyAll;

import org.junit.Test;

public class SimpleBlockingQueueTest {

    @Test
    public void poll() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread consumer = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            queue.poll();
        }, "Consumer");
        Thread producer = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " started");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            queue.offer(10);
        }, "Producer");
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
    }
}