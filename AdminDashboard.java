package com.healthcare.gui;
import com.healthcare.dao.*;
import com.healthcare.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class AdminDashboard extends JFrame {
    private final DefaultTableModel model=new DefaultTableModel();
    private final JTable table=new JTable(model);
    public AdminDashboard() {
        setTitle("Admin Dashboard"); setSize(1050,650); setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE); UI.style(this);
        JPanel bar=new JPanel();
        JButton d=UI.button("Doctors"),p=UI.button("Patients"),a=UI.button("Appointments"),m=UI.button("Medical Records"),addD=UI.button("Add Doctor"),addP=UI.button("Add Patient"),logout=UI.button("Logout");
        for(JButton b:new JButton[]{d,p,a,m,addD,addP,logout}) bar.add(b);
        add(bar,BorderLayout.NORTH); add(new JScrollPane(table));
        d.addActionListener(e->doctors()); p.addActionListener(e->patients()); a.addActionListener(e->appointments()); m.addActionListener(e->records());
        addD.addActionListener(e->addDoctor()); addP.addActionListener(e->addPatient()); logout.addActionListener(e->{dispose();new LoginFrame().setVisible(true);});
        doctors();
    }
    private void doctors(){ try { model.setColumnIdentifiers(new Object[]{"ID","Name","Specialization","Phone","Email","Available","Fee"}); for(Doctor x:new DoctorDAO().all()) model.addRow(new Object[]{x.getDoctorId(),x.getName(),x.getSpecialization(),x.getPhone(),x.getEmail(),x.getAvailableTime(),x.getFee()}); } catch(Exception e){UI.error(this,e);} }
    private void patients(){ try { model.setColumnIdentifiers(new Object[]{"ID","Name","Age","Gender","Phone","Email","Address"}); for(Patient x:new PatientDAO().all()) model.addRow(new Object[]{x.getPatientId(),x.getName(),x.getAge(),x.getGender(),x.getPhone(),x.getEmail(),x.getAddress()}); } catch(Exception e){UI.error(this,e);} }
    private void appointments(){ try { model.setColumnIdentifiers(new Object[]{"ID","Patient","Doctor","Date","Time","Reason","Status"}); for(String[] x:new AppointmentDAO().all()) model.addRow(x); } catch(Exception e){UI.error(this,e);} }
    private void records(){ try { model.setColumnIdentifiers(new Object[]{"ID","Patient","Doctor","Diagnosis","Prescription","Notes","Date"}); for(String[] x:new MedicalRecordDAO().all()) model.addRow(x); } catch(Exception e){UI.error(this,e);} }
    private void addDoctor(){
        try { String n=JOptionPane.showInputDialog(this,"Doctor name:"); if(n==null)return; String s=JOptionPane.showInputDialog(this,"Specialization:"); if(s==null)return;
            String ph=JOptionPane.showInputDialog(this,"Phone:"); String em=JOptionPane.showInputDialog(this,"Email:"); String t=JOptionPane.showInputDialog(this,"Available time:"); String fee=JOptionPane.showInputDialog(this,"Consultation fee:");
            new DoctorDAO().add(new Doctor(0,n,s,ph,em,t,Double.parseDouble(fee))); doctors();
        } catch(Exception e){UI.error(this,e);}
    }
    private void addPatient(){
        try { String n=JOptionPane.showInputDialog(this,"Patient name:"); if(n==null)return; String age=JOptionPane.showInputDialog(this,"Age:");
            String g=JOptionPane.showInputDialog(this,"Gender:"); String ph=JOptionPane.showInputDialog(this,"Phone:"); String em=JOptionPane.showInputDialog(this,"Email:"); String ad=JOptionPane.showInputDialog(this,"Address:");
            new PatientDAO().add(new Patient(0,n,Integer.parseInt(age),g,ph,em,ad)); patients();
        } catch(Exception e){UI.error(this,e);}
    }
}
