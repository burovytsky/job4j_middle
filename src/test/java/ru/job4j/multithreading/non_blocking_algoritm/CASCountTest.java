package ru.job4j.multithreading.non_blocking_algoritm;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CASCountTest {

    @Test
    public void increment() {
        CASCount casCount = new CASCount();
        new Thread(casCount::increment).start();
        new Thread(casCount::increment).start();
        new Thread(casCount::increment).start();
        new Thread(casCount::increment).start();
        new Thread(casCount::increment).start();
        new Thread(casCount::increment).start();
        new Thread(casCount::increment).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertThat(casCount.get(), is(7));
    }
}