package com.healthcare.dao;
import com.healthcare.model.Appointment;
import com.healthcare.util.DBConnection;
import java.sql.*;
import java.util.*;

public class AppointmentDAO implements CRUDOperations<Appointment> {
    public void add(Appointment a) throws Exception {
        String q="INSERT INTO appointments(patient_id,doctor_id,appointment_date,appointment_time,reason) VALUES(?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(q)) {
            p.setInt(1,a.getPatientId()); p.setInt(2,a.getDoctorId()); p.setDate(3,a.getDate()); p.setTime(4,a.getTime()); p.setString(5,a.getReason()); p.executeUpdate();
        }
    }
    public void update(Appointment a) throws Exception {
        throw new UnsupportedOperationException("Use status(id, value) to update appointment status.");
    }
    public void delete(int id) throws Exception {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("DELETE FROM appointments WHERE appointment_id=?")) { p.setInt(1,id); p.executeUpdate(); }
    }
    public void status(int id,String status) throws Exception {
        if(!Arrays.asList("Pending","Approved","Rejected","Completed").contains(status)) throw new IllegalArgumentException("Invalid appointment status.");
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("UPDATE appointments SET status=? WHERE appointment_id=?")) { p.setString(1,status); p.setInt(2,id); p.executeUpdate(); }
    }
    public List<String[]> all() throws Exception { return query("SELECT a.appointment_id,p.name patient,d.name doctor,a.appointment_date,a.appointment_time,a.reason,a.status FROM appointments a JOIN patients p ON a.patient_id=p.patient_id JOIN doctors d ON a.doctor_id=d.doctor_id ORDER BY a.appointment_date"); }
    public List<String[]> allByPatient(int patientId) throws Exception { return query("SELECT a.appointment_id,p.name patient,d.name doctor,a.appointment_date,a.appointment_time,a.reason,a.status FROM appointments a JOIN patients p ON a.patient_id=p.patient_id JOIN doctors d ON a.doctor_id=d.doctor_id WHERE a.patient_id="+patientId+" ORDER BY a.appointment_date"); }
    public List<String[]> allByDoctor(int doctorId) throws Exception { return query("SELECT a.appointment_id,p.name patient,d.name doctor,a.appointment_date,a.appointment_time,a.reason,a.status FROM appointments a JOIN patients p ON a.patient_id=p.patient_id JOIN doctors d ON a.doctor_id=d.doctor_id WHERE a.doctor_id="+doctorId+" ORDER BY a.appointment_date"); }
    private List<String[]> query(String q) throws Exception {
        List<String[]> out=new ArrayList<>();
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(q); ResultSet r=p.executeQuery()) {
            while(r.next()) out.add(new String[]{String.valueOf(r.getInt(1)),r.getString(2),r.getString(3),String.valueOf(r.getDate(4)),String.valueOf(r.getTime(5)),r.getString(6),r.getString(7)});
        }
        return out;
    }
}
