package ru.job4j.cas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CASCountTest {
    CASCount casCount = new CASCount();

    @Test
    public void where0AndIncrementThen30() throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                casCount.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 15; i++) {
                casCount.increment();
            }
        });
        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
        assertEquals(30, casCount.get());
    }
}