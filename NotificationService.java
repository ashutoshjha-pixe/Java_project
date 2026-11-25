package com.healthcare.service;

/** Demonstration background worker; it logs a heartbeat and does not send real notifications. */
public class NotificationService extends Thread {
    private volatile boolean running = true;
    public void run() {
        while(running) {
            System.out.println("[Background Thread] Appointment reminder service running...");
            try { Thread.sleep(5000); }
            catch(InterruptedException e) { Thread.currentThread().interrupt(); break; }
        }
    }
    public synchronized void stopService() { running=false; interrupt(); }
}
