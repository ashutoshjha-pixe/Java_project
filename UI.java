package com.healthcare.gui;
import javax.swing.*;
import java.awt.*;

public final class UI {
    private UI() { }
    public static void style(JFrame f) {
        f.getContentPane().setBackground(new Color(245,248,250));
        UIManager.put("Button.font",new Font("Arial",Font.BOLD,13));
        UIManager.put("Label.font",new Font("Arial",Font.PLAIN,13));
    }
    public static JButton button(String text){ return new JButton(text); }
    public static void error(Component c,Exception e){ JOptionPane.showMessageDialog(c,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE); }
}
