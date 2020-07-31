package ru.job4j.multithreading.non_blocking_algoritm;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class NonBlockingCacheTest {
    @Test
    public void add() throws InterruptedException, OptimisticException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        Base model1 = new Base("name1", 3, "desc1", 0);
        Base model2 = new Base("name2", 3, "desc2", 1);
        Base model3 = new Base("name3", 3, "desc3", 1);
        Base model4 = new Base("name4", 3, "desc4", 2);
        Base model5 = new Base("name5", 3, "desc5", 2);
        Base model6 = new Base("name6", 3, "desc6", 2);
        NonBlockingCache cache = new NonBlockingCache();
        cache.add(model1);
        Thread thread1 = new Thread(() -> {
            try {
                cache.update(model2);
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                cache.update(model3);
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        Thread thread3 = new Thread(() -> {
            try {
                cache.update(model4);
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        Thread thread4 = new Thread(() -> {
            try {
                cache.update(model5);
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });
        Thread thread5 = new Thread(() -> {
            try {
                cache.update(model6);
            } catch (OptimisticException e) {
                ex.set(e);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();
        thread5.join();
        assertThat(ex.get().getMessage(), is("incorrect model version"));
    }

}