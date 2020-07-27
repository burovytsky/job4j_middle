package ru.job4j.multithreading.wait_notify_notifyAll;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) {
        queue.add(value);
        this.notifyAll();
        System.out.println("all threads woke up");
    }

    public synchronized T poll() {
        while (queue.size() < 1) {
            try {
                System.out.println("queue is empty");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("queue is not empty. return element");
        return queue.peek();
    }
}
