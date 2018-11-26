package edu.ib;

import java.time.LocalTime;

public class AtomicClock1 implements Runnable {

    private Thread worker;
    protected volatile boolean isRunning = false;
    private int interval;

    public AtomicClock1() {
        interval = 1000;
    }

    public AtomicClock1(int interval) {
        this.interval = interval;
    }


    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }


    public void start() {
        worker = new Thread(this, " Clock thread");
        worker.start();

    }

    public void stop() {
        isRunning = false;
    }

    public void interrupt() {

        isRunning = false;
        worker.interrupt();
    }


    @Override
    public void run() {
        isRunning = true;
        while (isRunning) {

            try {
                System.out.println(LocalTime.now());
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Failed to complete operation");
            }

        }

    }
}
