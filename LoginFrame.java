package com.healthcare.gui;
import com.healthcare.dao.UserDAO;
import com.healthcare.model.User;
import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private final JTextField username=new JTextField();
    private final JPasswordField password=new JPasswordField();
    private final JComboBox<String> role=new JComboBox<>(new String[]{"ADMIN","DOCTOR","PATIENT"});
    public LoginFrame() {
        setTitle("Online Health Care Management System - Login"); setSize(500,350); setLocationRelativeTo(null); setDefaultCloseOperation(EXIT_ON_CLOSE); UI.style(this);
        JPanel box=new JPanel(new GridLayout(5,2,12,12)); box.setBorder(BorderFactory.createEmptyBorder(35,55,35,55));
        box.add(new JLabel("Username")); box.add(username); box.add(new JLabel("Password")); box.add(password); box.add(new JLabel("Role")); box.add(role);
        JButton login=UI.button("LOGIN"), clear=UI.button("CLEAR"); box.add(login); box.add(clear);
        JLabel head=new JLabel("ONLINE HEALTH CARE MANAGEMENT SYSTEM",SwingConstants.CENTER); head.setFont(new Font("Arial",Font.BOLD,18));
        add(head,BorderLayout.NORTH); add(box); login.addActionListener(e->login()); clear.addActionListener(e->{username.setText("");password.setText("");});
    }
    private void login() {
        try {
            User u=new UserDAO().login(username.getText().trim(),new String(password.getPassword()));
            if(u==null || !u.getRole().equals(role.getSelectedItem())) { JOptionPane.showMessageDialog(this,"Invalid login details."); return; }
            dispose();
            if("ADMIN".equals(u.getRole())) new AdminDashboard().setVisible(true);
            else if("DOCTOR".equals(u.getRole())) new DoctorDashboard(u.getId()).setVisible(true);
            else new PatientDashboard(u.getId()).setVisible(true);
        } catch(Exception e) { UI.error(this,e); }
    }
}
