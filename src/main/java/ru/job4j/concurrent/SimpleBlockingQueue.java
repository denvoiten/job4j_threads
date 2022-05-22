package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {
    private static final int MAX_SIZE = 10;
    private int size = 0;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    public synchronized void offer(T value) throws InterruptedException {
        while (queue.size() == MAX_SIZE) {
                wait();
        }
        queue.offer(value);
        size++;
        notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
        while (queue.isEmpty()) {
                wait();
        }
        T rsl = queue.poll();
        size--;
        notifyAll();
        return rsl;
    }

    public synchronized int size() {
        return size;
    }

    public synchronized boolean isEmpty() {
        return queue.isEmpty();
    }
}