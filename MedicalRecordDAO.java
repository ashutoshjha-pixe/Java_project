package com.healthcare.dao;
import com.healthcare.model.MedicalRecord;
import com.healthcare.util.DBConnection;
import java.sql.*;
import java.util.*;

public class MedicalRecordDAO {
    public void add(MedicalRecord m) throws Exception {
        String q="INSERT INTO medical_records(patient_id,doctor_id,diagnosis,prescription,notes,record_date) VALUES(?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(q)) {
            p.setInt(1,m.getPatientId()); p.setInt(2,m.getDoctorId()); p.setString(3,m.getDiagnosis()); p.setString(4,m.getPrescription()); p.setString(5,m.getNotes()); p.setDate(6,m.getDate()); p.executeUpdate();
        }
    }
    public List<String[]> all() throws Exception { return query("SELECT m.record_id,p.name,d.name,m.diagnosis,m.prescription,m.notes,m.record_date FROM medical_records m JOIN patients p ON m.patient_id=p.patient_id JOIN doctors d ON m.doctor_id=d.doctor_id ORDER BY m.record_date DESC"); }
    public List<String[]> allByPatient(int id) throws Exception { return query("SELECT m.record_id,p.name,d.name,m.diagnosis,m.prescription,m.notes,m.record_date FROM medical_records m JOIN patients p ON m.patient_id=p.patient_id JOIN doctors d ON m.doctor_id=d.doctor_id WHERE m.patient_id="+id+" ORDER BY m.record_date DESC"); }
    public List<String[]> allByDoctor(int id) throws Exception { return query("SELECT m.record_id,p.name,d.name,m.diagnosis,m.prescription,m.notes,m.record_date FROM medical_records m JOIN patients p ON m.patient_id=p.patient_id JOIN doctors d ON m.doctor_id=d.doctor_id WHERE m.doctor_id="+id+" ORDER BY m.record_date DESC"); }
    private List<String[]> query(String q) throws Exception {
        List<String[]> out=new ArrayList<>();
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(q); ResultSet r=p.executeQuery()) {
            while(r.next()) out.add(new String[]{String.valueOf(r.getInt(1)),r.getString(2),r.getString(3),r.getString(4),r.getString(5),r.getString(6),String.valueOf(r.getDate(7))});
        }
        return out;
    }
}
