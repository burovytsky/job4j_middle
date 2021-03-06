package ru.job4j.multithreading.threads;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;

public class Limiter implements Runnable {
    private final String url;
    private final int speedLimit;

    public Limiter(String url, int speedLimit) {
        this.url = url;
        this.speedLimit = speedLimit * 1000;
    }

    @Override
    public void run() {
        try {
            try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
                 FileOutputStream fileOutputStream = new FileOutputStream("file.zip")) {
                byte[] dataBuffer = new byte[1024];
                int bytesRead;
                long byteCount = 0;
                long byteCountInfo = 0;
                long startTime = System.currentTimeMillis();
                long startTimeInfo = System.currentTimeMillis();
                System.out.println("max speed " + speedLimit / 1024 + " kb/s");
                while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    byteCount += bytesRead;
                    byteCountInfo += bytesRead;
                    if (byteCount >= speedLimit) {
                        long endTime = System.currentTimeMillis();
                        if (endTime - startTime <= 1000) {
                            Thread.sleep(1000 - (endTime - startTime));
                            byteCount = 0;
                        }
                    }
                    System.out.print("\rloading... " + byteCountInfo / 1024 + " kb");
                    startTime = System.currentTimeMillis();
                }
                System.out.println();
                System.out.println("loading time: " + (System.currentTimeMillis() - startTimeInfo) / 1000 + " seconds");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
