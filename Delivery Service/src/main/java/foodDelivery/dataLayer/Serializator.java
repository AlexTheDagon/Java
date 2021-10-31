package foodDelivery.dataLayer;

import foodDelivery.businessLayer.DeliveryService;

import java.io.*;

/**
 * The type Serializator.
 */
public class Serializator {


    /**
     * The File name.
     */
    String fileName = "file.txt";

    /**
     * Serialize.
     *
     * @param myDeliveryService the my delivery service
     */
    public void serialize(DeliveryService myDeliveryService) {
        try
        {
            FileOutputStream myFile = new FileOutputStream(fileName);
            ObjectOutputStream myOutput = new ObjectOutputStream(myFile);
            myOutput.writeObject(myDeliveryService);
            myOutput.close();
            myFile.close();
            System.out.println("Object has been serialized");
        } catch(IOException ex) { ex.printStackTrace(); }
    }


    /**
     * De serialize delivery service.
     *
     * @return the delivery service
     */
    public DeliveryService deSerialize() {
        try
        {
            FileInputStream myFile = new FileInputStream(fileName);
            ObjectInputStream myInput = new ObjectInputStream(myFile);
            DeliveryService myDeliveryService = (DeliveryService)myInput.readObject();
            myInput.close();
            myFile.close();
            System.out.println("Object has been deserialized ");
            return myDeliveryService;
        } catch(Exception ex) {
            DeliveryService myDeliveryService = new DeliveryService();
            return myDeliveryService;
        }
    }
}
