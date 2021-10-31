package orderManagement.bll;


import orderManagement.dao.AbstractDAO;
import orderManagement.model.Client;


import java.util.ArrayList;


/**
 * The type Client business logic.
 */
public class ClientBLL {

    private AbstractDAO<Client> clientDAO;


    /**
     * Instantiates a new Client bll.
     */
    public ClientBLL() {
        clientDAO = new AbstractDAO<>(Client.class);
    }

    /**
     * Check validity boolean.
     *
     * @param myClient the client
     * @return the boolean
     */
    public boolean checkValidity(Client myClient) {
        if(myClient.getId() < 0) return false;
        if(myClient.getPhone().length() != 10) return false;
        if(myClient.getLastname().length() > 20) return false;
        if(myClient.getFirstname().length() > 20) return false;
        for(int i = 0; i < myClient.getPhone().length(); ++ i) {
            if(myClient.getPhone().charAt(i) < '0' || myClient.getPhone().charAt(i) > '9') return false;
        }
        return true;
    }

    /**
     * Check availability int.
     *
     * @param phone the phone
     * @return the int
     */
    public int checkAvailability(String phone) { // Return 0 if available or the ID if in the table
        ArrayList<Client> clientList = clientDAO.findAll();
        if(clientList == null) return 0;
        for(Client myClient : clientList) {
            if(myClient.getPhone().equals(phone)) return myClient.getId();
        }
        return 0;
    }

    /**
     * Find client by id client.
     *
     * @param id the id
     * @return the client
     */
    public Client findClientById(int id) {
        Client myClient = clientDAO.findById(id);
        if (myClient == null) {
            System.out.println("Product with id = " + id + " not found!");
        }
        return myClient;
    }

    /**
     * Insert client.
     *
     * @param myClient the client
     */
    public void insertClient(Client myClient) {
        if(checkValidity(myClient) == false) {
            System.out.println("Invalid Client data!");
            return;
        }
        int foundID = checkAvailability(myClient.getPhone());
        if(foundID == 0) clientDAO.insert(myClient);
        else System.out.println("Phone number already in use!");
    }

    /**
     * Update client.
     *
     * @param myClient the client
     */
    public void updateClient(Client myClient) {
        if(checkValidity(myClient) == false) {
            System.out.println("Invalid Client data!");
            return;
        }
        int foundID = checkAvailability(myClient.getPhone());
        if(foundID == 0 || foundID == myClient.getId()) clientDAO.update(myClient);
        else System.out.println("Phone number already in use!");
    }

    /**
     * Delete client.
     *
     * @param myClient the client
     */
    public void deleteClient(Client myClient) { // Delete only by id of the Client
        Client clientToDelete = findClientById(myClient.getId());
        if(clientToDelete != null) clientDAO.delete(clientToDelete);
    }

    /**
     * List my clients array list.
     *
     * @return the array list
     */
    public ArrayList<Client> listMyClients() {
        return clientDAO.findAll();
    }
}
