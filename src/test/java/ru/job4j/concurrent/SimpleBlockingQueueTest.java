package ru.job4j.concurrent;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class SimpleBlockingQueueTest {
    private final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();

    @Test
    public void when1Consumer1Producer() throws InterruptedException {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.offer(i);
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 9; i++) {
                queue.poll();
            }
        }, "Consumer");

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(queue.size(), is(1));
    }

    @Test
    public void when2Consumer1Producer() throws InterruptedException {
        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.offer(i);
            }
        }, "Producer");

        Thread consumer = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.poll();
            }
        }, "Consumer");
        Thread consumer2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.poll();
            }
        }, "Consumer2");

        producer.start();
        consumer.start();
        consumer2.start();
        producer.join();
        consumer.join();
        consumer2.join();
        assertTrue(queue.isEmpty());
    }
}