package ru.job4j.multithreading.exam;

public class Switcher {
    public static void main(String[] args) {
        MasterSlaveBarrier barrier = new MasterSlaveBarrier();
        Thread first = new Thread(
                () -> {
                    while (true) {
                        System.out.println("Thread A");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.tryMaster();
                        barrier.doneMaster();
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    while (true) {
                        System.out.println("Thread B");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        barrier.trySlave();
                        barrier.doneSlave();
                    }
                }
        );
        first.start();
        second.start();
    }
}
