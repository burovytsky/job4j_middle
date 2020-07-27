package ru.job4j.multithreading.resource_synchronization;

import java.util.*;

public class DynamicList<T> implements Iterable<T> {
    private Object[] objects = new Object[10];
    private int index = 0;
    private int modCount = 0;

    public void add(T element) {
        if (index >= objects.length) {
            int newSize = (int) (objects.length + objects.length * 1.5);
            objects = Arrays.copyOf(objects, newSize);
        }
        this.objects[index++] = element;
        modCount++;
    }

    public T get(int position) {
        Objects.checkIndex(position, index);
        Object target = this.objects[position];
        return (T) target;
    }

    @Override
    public Iterator<T> iterator() {
        int expectedModCount = modCount;
        return new Iterator<T>() {
            int point = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return index > point;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return (T) objects[point++];
            }
        };
    }
}
