package ru.job4j.multithreading.pool;

import ru.job4j.multithreading.wait_notify_notifyAll.SimpleBlockingQueue;

public class TaskExecutor implements Runnable{
    private final SimpleBlockingQueue<Runnable> queue;

    public TaskExecutor(SimpleBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true){
                String name = Thread.currentThread().getName();
                Runnable task = queue.poll();
                System.out.println("Task started by thread "+ name);
                task.run();
                System.out.println("Task finished by thread "+ name);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
