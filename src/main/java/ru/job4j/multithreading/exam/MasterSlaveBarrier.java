package ru.job4j.multithreading.exam;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class MasterSlaveBarrier {
    @GuardedBy("this")
    private final AtomicInteger flag = new AtomicInteger(0);

    public synchronized void tryMaster() {
        while (flag.get() != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void trySlave() {
        while (flag.get() != 1) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void doneMaster() {
        flag.incrementAndGet();
        notify();
    }

    public synchronized void doneSlave() {
        flag.decrementAndGet();
        notify();
    }
}