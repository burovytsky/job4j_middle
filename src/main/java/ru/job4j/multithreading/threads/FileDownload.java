package ru.job4j.multithreading.threads;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        String url = args[0];
        int speedLimit = Integer.parseInt(args[1]);
        downloadWithLimit(url, speedLimit);
    }

    public static void downloadWithLimit(String url, int speedLimit) throws Exception {
        speedLimit *= 1000;
        String fileName = "file.zip";
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long byteCount = 0;
            long byteCountInfo = 0;
            long startTime = System.currentTimeMillis();
            long startTime2 = System.currentTimeMillis();
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
            System.out.println("loading time: " + (System.currentTimeMillis() - startTime2) / 1000 + " seconds");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
