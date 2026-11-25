package com.healthcare;

import com.healthcare.gui.LoginFrame;
import com.healthcare.service.NotificationService;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        NotificationService service = new NotificationService();
        service.start();
        Runtime.getRuntime().addShutdownHook(new Thread(service::stopService));
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}
