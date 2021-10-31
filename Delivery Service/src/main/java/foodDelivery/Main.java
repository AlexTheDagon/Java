package foodDelivery;


import foodDelivery.businessLayer.DeliveryService;
import foodDelivery.dataLayer.Serializator;
import foodDelivery.model.MenuItem;
import foodDelivery.presentationLayer.LoginWindow;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Main.
 */
public class Main {

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args){
        Serializator mySerializer = new Serializator();
        DeliveryService myDeliveryService = mySerializer.deSerialize();

        JFrame loginFrame = new JFrame("Login Frame");
        loginFrame.setContentPane(new LoginWindow(myDeliveryService).getLoginPanel());
        loginFrame.pack();
        loginFrame.setVisible(true);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }
}

