package com.healthcare.dao;
import com.healthcare.model.Patient;
import com.healthcare.util.DBConnection;
import java.sql.*;
import java.util.*;

public class PatientDAO implements CRUDOperations<Patient> {
    public void add(Patient p) throws Exception {
        String q="INSERT INTO patients(name,age,gender,phone,email,address) VALUES(?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement s=c.prepareStatement(q)) {
            s.setString(1,p.getName()); s.setInt(2,p.getAge()); s.setString(3,p.getGender()); s.setString(4,p.getPhone()); s.setString(5,p.getEmail()); s.setString(6,p.getAddress()); s.executeUpdate();
        }
    }
    public void update(Patient p) throws Exception {
        String q="UPDATE patients SET name=?,age=?,gender=?,phone=?,email=?,address=? WHERE patient_id=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement s=c.prepareStatement(q)) {
            s.setString(1,p.getName()); s.setInt(2,p.getAge()); s.setString(3,p.getGender()); s.setString(4,p.getPhone()); s.setString(5,p.getEmail()); s.setString(6,p.getAddress()); s.setInt(7,p.getPatientId()); s.executeUpdate();
        }
    }
    public void delete(int id) throws Exception {
        try(Connection c=DBConnection.getConnection(); PreparedStatement s=c.prepareStatement("DELETE FROM patients WHERE patient_id=?")) { s.setInt(1,id); s.executeUpdate(); }
    }
    public List<Patient> all() throws Exception {
        List<Patient> out=new ArrayList<>();
        try(Connection c=DBConnection.getConnection(); PreparedStatement s=c.prepareStatement("SELECT * FROM patients ORDER BY name"); ResultSet r=s.executeQuery()) {
            while(r.next()) out.add(new Patient(r.getInt("patient_id"),r.getString("name"),r.getInt("age"),r.getString("gender"),r.getString("phone"),r.getString("email"),r.getString("address")));
        }
        return out;
    }
    public int findIdByUser(int uid) throws Exception {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT patient_id FROM patients WHERE user_id=?")) {
            p.setInt(1,uid); try(ResultSet r=p.executeQuery()) { if(r.next()) return r.getInt(1); }
        }
        throw new Exception("Patient profile not found.");
    }
}
