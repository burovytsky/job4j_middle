package ru.job4j.multithreading.wait_notify_notifyAll;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            System.out.println("max elements in queue");
            wait();
        }
        queue.offer(value);
        System.out.println("added  " + value);
        notify();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.size() == 0) {
            System.out.println("queue is empty");
            this.wait();
        }
        T rs = queue.poll();
        System.out.println("remove " + rs);
        notify();
        return rs;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}
