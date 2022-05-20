package ru.job4j.buffer;

public class Buffer {
    private final StringBuilder buff = new StringBuilder();

    public synchronized void add(int value) {
        System.out.print(value);
        buff.append(value);
    }

    @Override
    public synchronized String toString() {
        return buff.toString();
    }
}
