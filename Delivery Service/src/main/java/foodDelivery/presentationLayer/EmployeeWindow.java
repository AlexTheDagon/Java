package foodDelivery.presentationLayer;

import foodDelivery.businessLayer.DeliveryService;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * The type Employee window.
 */
public class EmployeeWindow implements Observer {
    private JPanel employeePanel;
    private JTextArea orderNotificationTextArea;
    /**
     * The My delivery service.
     */
    DeliveryService myDeliveryService;

    /**
     * Gets employee panel.
     *
     * @return the employee panel
     */
    public JPanel getEmployeePanel() {
        return employeePanel;
    }

    /**
     * Instantiates a new Employee window.
     *
     * @param paramMyDeliveryService the param my delivery service
     */
    public EmployeeWindow(DeliveryService paramMyDeliveryService) {
        myDeliveryService = paramMyDeliveryService;
        myDeliveryService.addObserver(this);
        orderNotificationTextArea.setText("No new orders have been placed.");
    }

    @Override
    public void update(Observable o, Object arg)
    {
        orderNotificationTextArea.setText(arg.toString());
    }
}
