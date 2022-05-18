package ru.job4j.concurrent.task;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("test")) {
            byte[] dataBuffer = new byte[speed];
            long size = new URL(url).openConnection().getContentLength();
            int bytesRead;
            int downloadData = 0;
            int index = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, speed)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                long endTime = System.currentTimeMillis() - startTime;
                downloadData += bytesRead;
                index = showInfo(downloadData, size, index);
                if (endTime < 1000) {
                    Thread.sleep(1000 - endTime);
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int showInfo(int downloadData, long size, int index) {
        String[] process = {".    ", "..   ", "...  ", ".... "};
        if (index == process.length) {
            index = 0;
        }
        float percent = (float) downloadData / size * 100;
        String result = String.format("Загрузка%s%.0f%s", process[index++], percent, "%");
        System.out.print("\r" + result);
        return index;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 2) {
            throw new IllegalArgumentException("Not all arguments entered");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
