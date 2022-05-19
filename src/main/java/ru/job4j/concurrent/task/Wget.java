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
            byte[] dataBuffer = new byte[1024];
            long size = new URL(url).openConnection().getContentLength();
            int bytesRead;
            int downloadData = 0;
            int counter = 0;
            int index = 0;
            long endTime;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                downloadData += bytesRead;
                counter += bytesRead;
                index = showInfo(counter, size, index);
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                if (downloadData >= speed * 1024) {
                    endTime = System.currentTimeMillis() - startTime;
                    if (endTime < 1000) {
                        Thread.sleep(1000 - endTime);
                    }
                    downloadData = 0;
                    startTime = System.currentTimeMillis();
                }
            }
        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private int showInfo(int downloadData, long size, int index) {
        String[] process = {"     ", ".    ", "..   ", "...  ", ".... ", ""};
        if (index == process.length - 1) {
            index = 0;
        }
        float percent = (float) downloadData / size * 100;
        System.out.printf("\rЗагрузка%s%.0f%s", process[index++], percent, "%");
        return index;
    }

    public static void main(String[] args) throws InterruptedException {
        String url = "https://proof.ovh.net/files/10Mb.dat";
        int speed = 1024;
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
    }
}
