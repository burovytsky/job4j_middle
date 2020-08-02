package ru.job4j.multithreading.pool;

import ru.job4j.multithreading.wait_notify_notifyAll.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks;
    private final int size = Runtime.getRuntime().availableProcessors();

    public ThreadPool(int queueSize) {
        this.tasks = new SimpleBlockingQueue<>(queueSize);
        TaskExecutor taskExecutor = new TaskExecutor(tasks);
        initThreads(taskExecutor);
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    private void initThreads(TaskExecutor executor){
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(executor);
            thread.start();
        }
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public SimpleBlockingQueue<Runnable> getTasks(){
        return tasks;
    }

    public List<Thread> getThreads() {
        return threads;
    }

}
