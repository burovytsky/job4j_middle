package ru.job4j.multithreading.wait_notify_notifyAll;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    private final int total;
    @GuardedBy("this")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        count++;
        if (count == total) {
            this.notifyAll();
        }
    }

    public synchronized void await() {
        while (total != count) {
            try {
                System.out.println(Thread.currentThread().getName() + "  waiting....");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Method await() finished");
    }
}
