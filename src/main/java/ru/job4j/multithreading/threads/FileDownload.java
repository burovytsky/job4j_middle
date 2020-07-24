package ru.job4j.multithreading.threads;


public class FileDownload {
    public static void main(String[] args) {
        String url = args[0];
        int speedLimit = Integer.parseInt(args[1]);
        Limiter limiter = new Limiter(url, speedLimit);
        Thread thread = new Thread(limiter);
        thread.start();
    }
}
