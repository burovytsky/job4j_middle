package ru.job4j.multithreading.wait_notify_notifyAll;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public synchronized void offer(T value) {
        while (queue.size() >= limit){
            try {
                System.out.println("max elements in queue");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
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
        this.notifyAll();
        return queue.poll();
    }

    public synchronized int getSize(){
        return queue.size();
    }

    public synchronized int getMaxSize(){
        return this.limit;
    }
}
