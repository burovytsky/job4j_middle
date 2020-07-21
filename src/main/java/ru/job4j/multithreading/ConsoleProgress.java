package ru.job4j.multithreading;

public class ConsoleProgress implements Runnable {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(3000);
        progress.interrupt();
    }

    @Override
    public void run() {
        String load = "\\";

        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\r load... " + load);
                load = switch (load) {
                    case "\\" -> "|";
                    case "|" -> "/";
                    case "/" -> "-";
                    default -> "\\";
                };
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }

    }
}
