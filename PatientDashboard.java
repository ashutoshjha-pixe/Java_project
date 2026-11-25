package com.healthcare.gui;
import com.healthcare.dao.*;
import com.healthcare.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.Time;

public class PatientDashboard extends JFrame {
    private final int userId;
    private int patientId;
    private final DefaultTableModel model=new DefaultTableModel();
    private final JTable table=new JTable(model);
    public PatientDashboard(int uid) {
        userId=uid; setTitle("Patient Dashboard"); setSize(1000,600); setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE); UI.style(this);
        try { patientId=new PatientDAO().findIdByUser(uid); } catch(Exception e){UI.error(this,e);}
        JPanel bar=new JPanel(); JButton d=UI.button("Doctors"),b=UI.button("Book Appointment"),a=UI.button("My Appointments"),r=UI.button("Medical Records"),out=UI.button("Logout");
        for(JButton x:new JButton[]{d,b,a,r,out}) bar.add(x); add(bar,BorderLayout.NORTH); add(new JScrollPane(table));
        d.addActionListener(e->doctors()); b.addActionListener(e->book()); a.addActionListener(e->appointments()); r.addActionListener(e->records()); out.addActionListener(e->{dispose();new LoginFrame().setVisible(true);});
        doctors();
    }
    private void doctors(){ try { model.setColumnIdentifiers(new Object[]{"ID","Doctor","Specialization","Phone","Email","Available","Fee"}); for(Doctor x:new DoctorDAO().all()) model.addRow(new Object[]{x.getDoctorId(),x.getName(),x.getSpecialization(),x.getPhone(),x.getEmail(),x.getAvailableTime(),x.getFee()}); } catch(Exception e){UI.error(this,e);} }
    private void book(){
        try { String d=JOptionPane.showInputDialog(this,"Doctor ID:"); String date=JOptionPane.showInputDialog(this,"Date (YYYY-MM-DD):"); String time=JOptionPane.showInputDialog(this,"Time (HH:MM:SS):"); String reason=JOptionPane.showInputDialog(this,"Reason:");
            if(d==null||date==null||time==null)return;
            new AppointmentDAO().add(new Appointment(patientId,Integer.parseInt(d),Date.valueOf(date),Time.valueOf(time),reason)); JOptionPane.showMessageDialog(this,"Appointment booked successfully."); appointments();
        } catch(Exception e){UI.error(this,e);}
    }
    private void appointments(){ try { model.setColumnIdentifiers(new Object[]{"ID","Patient","Doctor","Date","Time","Reason","Status"}); for(String[] x:new AppointmentDAO().allByPatient(patientId)) model.addRow(x); } catch(Exception e){UI.error(this,e);} }
    private void records(){ try { model.setColumnIdentifiers(new Object[]{"ID","Patient","Doctor","Diagnosis","Prescription","Notes","Date"}); for(String[] x:new MedicalRecordDAO().allByPatient(patientId)) model.addRow(x); } catch(Exception e){UI.error(this,e);} }
}
