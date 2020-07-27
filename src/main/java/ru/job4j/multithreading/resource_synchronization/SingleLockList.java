package ru.job4j.multithreading.resource_synchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final DynamicList<T> list = new DynamicList<>();

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private synchronized DynamicList<T> copy(DynamicList<T> list) {
        DynamicList<T> rsl = new DynamicList<>();
        for (T t : list) {
            rsl.add(t);
        }
        return rsl;
    }
}
