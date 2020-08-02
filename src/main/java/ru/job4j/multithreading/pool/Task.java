package ru.job4j.multithreading.pool;

public class Task implements Runnable {
    private final int number;

    public Task(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        System.out.println("Start task number :" + number);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End task number :" + number);
    }
}
