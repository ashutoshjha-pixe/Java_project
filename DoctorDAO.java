package com.healthcare.dao;
import com.healthcare.model.Doctor;
import com.healthcare.util.DBConnection;
import java.sql.*;
import java.util.*;

public class DoctorDAO implements CRUDOperations<Doctor> {
    public void add(Doctor d) throws Exception {
        String q="INSERT INTO doctors(name,specialization,phone,email,available_time,consultation_fee) VALUES(?,?,?,?,?,?)";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(q)) {
            p.setString(1,d.getName()); p.setString(2,d.getSpecialization()); p.setString(3,d.getPhone());
            p.setString(4,d.getEmail()); p.setString(5,d.getAvailableTime()); p.setDouble(6,d.getFee()); p.executeUpdate();
        }
    }
    public void update(Doctor d) throws Exception {
        String q="UPDATE doctors SET name=?,specialization=?,phone=?,email=?,available_time=?,consultation_fee=? WHERE doctor_id=?";
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement(q)) {
            p.setString(1,d.getName()); p.setString(2,d.getSpecialization()); p.setString(3,d.getPhone());
            p.setString(4,d.getEmail()); p.setString(5,d.getAvailableTime()); p.setDouble(6,d.getFee()); p.setInt(7,d.getDoctorId());
            p.executeUpdate();
        }
    }
    public void delete(int id) throws Exception {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("DELETE FROM doctors WHERE doctor_id=?")) { p.setInt(1,id); p.executeUpdate(); }
    }
    public List<Doctor> all() throws Exception {
        List<Doctor> out=new ArrayList<>();
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT * FROM doctors ORDER BY name"); ResultSet r=p.executeQuery()) {
            while(r.next()) out.add(new Doctor(r.getInt("doctor_id"),r.getString("name"),r.getString("specialization"),r.getString("phone"),r.getString("email"),r.getString("available_time"),r.getDouble("consultation_fee")));
        }
        return out;
    }
    public int findIdByUser(int userId) throws Exception {
        try(Connection c=DBConnection.getConnection(); PreparedStatement p=c.prepareStatement("SELECT doctor_id FROM doctors WHERE user_id=?")) {
            p.setInt(1,userId); try(ResultSet r=p.executeQuery()) { if(r.next()) return r.getInt(1); }
        }
        throw new Exception("Doctor profile not found for this user.");
    }
}
