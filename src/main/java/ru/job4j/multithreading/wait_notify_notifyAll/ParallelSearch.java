package ru.job4j.multithreading.wait_notify_notifyAll;

public class ParallelSearch {
    public static void main(String[] args){
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(1);
        final Thread consumer = new Thread(
                () -> {
                    while (!Thread.currentThread().isInterrupted()) {
                        try {
                            queue.poll();
                        } catch (Exception e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt(); } } }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 10; index++) {
                        try {
                            queue.offer(index);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                    consumer.interrupt();
                }

        ).start();
    }
}
