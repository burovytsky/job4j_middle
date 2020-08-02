package ru.job4j.multithreading.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Burovytsky Constantine
 * @version 1.0
 * @since 02.08.2020
 */
public class EmailNotification {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Method generates email for user.
     *
     * @param user the user
     */
    public void emailTo(User user) {
        String subject = String.format("Notification %s to email %s", user.getName(), user.getEmail());
        String body = String.format("Add a new event to %s", user.getName());
        pool.submit(() -> send(subject, body, user.getEmail()));
    }

    /**
     * Method sends email to user.
     *
     * @param subject the subject
     * @param body    the body
     * @param email   user email
     */
    public void send(String subject, String body, String email) {
        System.out.println(subject + ". " + body + ". " + email);
    }

    /**
     * Close the pool and wait for all tasks to complete.
     */
    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
