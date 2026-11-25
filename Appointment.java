package com.healthcare.model;
import java.sql.Date;
import java.sql.Time;

public class Appointment {
    private int id, patientId, doctorId;
    private Date date;
    private Time time;
    private String reason, status;
    public Appointment(int patientId,int doctorId,Date date,Time time,String reason) {
        this.patientId=patientId; this.doctorId=doctorId; this.date=date; this.time=time;
        this.reason=reason; this.status="Pending";
    }
    public int getId(){return id;} public int getPatientId(){return patientId;}
    public int getDoctorId(){return doctorId;} public Date getDate(){return date;}
    public Time getTime(){return time;} public String getReason(){return reason;}
    public String getStatus(){return status;}
}
