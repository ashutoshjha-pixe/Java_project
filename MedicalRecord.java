package com.healthcare.model;
import java.sql.Date;

public class MedicalRecord {
    private int patientId, doctorId;
    private String diagnosis, prescription, notes;
    private Date date;
    public MedicalRecord(int patientId,int doctorId,String diagnosis,String prescription,String notes,Date date) {
        this.patientId=patientId; this.doctorId=doctorId; this.diagnosis=diagnosis;
        this.prescription=prescription; this.notes=notes; this.date=date;
    }
    public int getPatientId(){return patientId;} public int getDoctorId(){return doctorId;}
    public String getDiagnosis(){return diagnosis;} public String getPrescription(){return prescription;}
    public String getNotes(){return notes;} public Date getDate(){return date;}
}
