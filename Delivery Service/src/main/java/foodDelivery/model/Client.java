package foodDelivery.model;


import java.io.Serializable;

/**
 * The type Client.
 */
public class Client implements Serializable {

    private int clientID;
    private String username;
    private String password;

    /**
     * Instantiates a new Client.
     *
     * @param clientID the client id
     * @param username the username
     * @param password the password
     */
    public Client(int clientID, String username, String password) {
        this.clientID = clientID;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets client id.
     *
     * @return the client id
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Sets client id.
     *
     * @param clientID the client id
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientID=" + clientID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



}
