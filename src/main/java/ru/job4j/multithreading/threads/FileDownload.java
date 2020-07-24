package ru.job4j.multithreading.threads;


public class FileDownload {
    public static void main(String[] args) throws Exception {
        String url = args[0];
        int speedLimit = Integer.parseInt(args[1]);
        Limiter limiter = new Limiter(url, speedLimit);
        limiter.download();
    }
}
