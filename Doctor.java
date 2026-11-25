package com.healthcare.model;

public class Doctor extends User {
    private int doctorId;
    private String name, specialization, phone, email, availableTime;
    private double fee;

    public Doctor() { }
    public Doctor(int id, String name, String specialization, String phone, String email, String time, double fee) {
        this.doctorId=id; this.name=name; this.specialization=specialization; this.phone=phone;
        this.email=email; this.availableTime=time; this.fee=fee;
    }
    public int getDoctorId(){ return doctorId; }
    public String getName(){ return name; }
    public String getSpecialization(){ return specialization; }
    public String getPhone(){ return phone; }
    public String getEmail(){ return email; }
    public String getAvailableTime(){ return availableTime; }
    public double getFee(){ return fee; }
    @Override public void showDashboard(){ System.out.println("Doctor Dashboard"); }
    @Override public String toString(){ return doctorId + " - " + name + " (" + specialization + ")"; }
}
