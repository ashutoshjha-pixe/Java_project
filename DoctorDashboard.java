package com.healthcare.gui;
import com.healthcare.dao.*;
import com.healthcare.model.MedicalRecord;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;

public class DoctorDashboard extends JFrame {
    private final int userId;
    private int doctorId;
    private final DefaultTableModel model=new DefaultTableModel();
    private final JTable table=new JTable(model);
    public DoctorDashboard(int uid) {
        userId=uid; setTitle("Doctor Dashboard"); setSize(1050,600); setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE); UI.style(this);
        try { doctorId=new DoctorDAO().findIdByUser(uid); } catch(Exception e){UI.error(this,e);}
        JPanel bar=new JPanel(); JButton a=UI.button("Appointments"),ok=UI.button("Approve"),no=UI.button("Reject"),done=UI.button("Complete"),rec=UI.button("Add Medical Record"),out=UI.button("Logout");
        for(JButton x:new JButton[]{a,ok,no,done,rec,out}) bar.add(x); add(bar,BorderLayout.NORTH); add(new JScrollPane(table));
        a.addActionListener(e->appointments()); ok.addActionListener(e->status("Approved")); no.addActionListener(e->status("Rejected")); done.addActionListener(e->status("Completed")); rec.addActionListener(e->record()); out.addActionListener(e->{dispose();new LoginFrame().setVisible(true);});
        appointments();
    }
    private void appointments(){ try { model.setColumnIdentifiers(new Object[]{"ID","Patient","Doctor","Date","Time","Reason","Status"}); for(String[] x:new AppointmentDAO().allByDoctor(doctorId)) model.addRow(x); } catch(Exception e){UI.error(this,e);} }
    private void status(String status){ int row=table.getSelectedRow(); if(row<0){JOptionPane.showMessageDialog(this,"Select appointment.");return;} try { new AppointmentDAO().status(Integer.parseInt(model.getValueAt(row,0).toString()),status); appointments(); } catch(Exception e){UI.error(this,e);} }
    private void record(){
        try { String p=JOptionPane.showInputDialog(this,"Patient ID:"); String diag=JOptionPane.showInputDialog(this,"Diagnosis:"); String pre=JOptionPane.showInputDialog(this,"Prescription:"); String notes=JOptionPane.showInputDialog(this,"Notes:");
            if(p==null||diag==null)return;
            new MedicalRecordDAO().add(new MedicalRecord(Integer.parseInt(p),doctorId,diag,pre,notes,new Date(System.currentTimeMillis()))); JOptionPane.showMessageDialog(this,"Medical record saved.");
        } catch(Exception e){UI.error(this,e);}
    }
}
