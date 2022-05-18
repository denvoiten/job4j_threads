package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int index = 0;
        String[] process = {".    |", "..   /", "...  -", ".... \\"};
        try {
            while (!Thread.currentThread().isInterrupted()) {
                if (index == process.length) {
                    index = 0;
                }
                System.out.print("\rLoading" + process[index++]);
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        try {
            progress.start();
            Thread.sleep(10000);
            progress.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
