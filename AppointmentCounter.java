package com.healthcare.service;
public class AppointmentCounter {
    private int count;
    public synchronized void increment(){ count++; }
    public synchronized int getCount(){ return count; }
}
