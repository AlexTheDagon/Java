package foodDelivery.presentationLayer;

import foodDelivery.businessLayer.DeliveryService;
import foodDelivery.dataLayer.Serializator;

import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * The type Login window.
 */
public class LoginWindow {
    /**
     * The My delivery service.
     */
    DeliveryService myDeliveryService;
    /**
     * The My serializer.
     */
    Serializator mySerializer;
    private JFrame administratorFrame;
    private JFrame clientFrame;
    private JFrame employeeFrame;

    private JTextField usernameField;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JPanel loginPanel;

    /**
     * Gets login panel.
     *
     * @return the login panel
     */
    public JPanel getLoginPanel() {
        return loginPanel;
    }

    /**
     * Instantiates a new Login window.
     *
     * @param paramMyDeliveryService the param my delivery service
     */
    public LoginWindow(DeliveryService paramMyDeliveryService) {

        myDeliveryService = paramMyDeliveryService;
        mySerializer = new Serializator();

        registerButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();
            String response = myDeliveryService.registerClient(user, pass);
            JOptionPane.showMessageDialog(loginPanel, response);
            mySerializer.serialize(myDeliveryService);
        });

        loginButton.addActionListener(e -> {
            String user = usernameField.getText();
            String pass = passwordField.getText();

            String response = myDeliveryService.validateLogin(user, pass);
            JOptionPane.showMessageDialog(loginPanel, response);

            if(response.equals("Succesful Login")) {
                if(user.equals("administrator")) {
                    JFrame administratorFrame = new JFrame("Admin Frame");
                    administratorFrame.setContentPane(new AdminWindow(myDeliveryService).getAdminPanel());
                    administratorFrame.pack();
                    administratorFrame.setVisible(true);
                }
                if(user.equals("employee")) {
                    JFrame employeeFrame = new JFrame("Emplyee Frame");
                    employeeFrame.setContentPane(new EmployeeWindow(myDeliveryService).getEmployeePanel());
                    employeeFrame.pack();
                    employeeFrame.setVisible(true);

                }
                if(user.equals("administrator") == false && user.equals("employee") == false) {
                    JFrame clientFrame = new JFrame("Client Frame");
                    clientFrame.setContentPane(new ClientWIndow(myDeliveryService, user).getClientPanel());
                    clientFrame.pack();
                    clientFrame.setVisible(true);
                }
            }
        });
    }

}
