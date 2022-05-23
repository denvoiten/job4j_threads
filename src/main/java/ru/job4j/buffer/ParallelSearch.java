package ru.job4j.buffer;

import ru.job4j.concurrent.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        final Thread consumer = new Thread(
                () -> {
                  try {
                        while (!Thread.currentThread().isInterrupted()) {
                            System.out.println(queue.poll());
                        }
                    } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                   }
                }
        );
        consumer.start();
        new Thread(
                () -> {
                    for (int index = 0; index != 3; index++) {
                        try {
                            queue.offer(index);
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    consumer.interrupt();
                }
        ).start();
    }
}
