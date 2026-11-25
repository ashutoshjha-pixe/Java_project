package com.healthcare.model;

public class Patient extends User {
    private int patientId, age;
    private String name, gender, phone, email, address;

    public Patient() { }
    public Patient(int id, String name, int age, String gender, String phone, String email, String address) {
        this.patientId=id; this.name=name; this.age=age; this.gender=gender;
        this.phone=phone; this.email=email; this.address=address;
    }
    public int getPatientId(){ return patientId; }
    public String getName(){ return name; }
    public int getAge(){ return age; }
    public String getGender(){ return gender; }
    public String getPhone(){ return phone; }
    public String getEmail(){ return email; }
    public String getAddress(){ return address; }
    @Override public void showDashboard(){ System.out.println("Patient Dashboard"); }
}
