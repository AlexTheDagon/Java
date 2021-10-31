package orderManagement.model;

/**
 * The type Client.
 */
public class Client {
    private int id;
    private String phone;
    private String firstname;
    private String lastname;

    /**
     * Instantiates a new Client.
     */
    public Client() { }

    /**
     * Instantiates a new Client.
     *
     * @param id        the id
     * @param phone     the phone
     * @param firstname the firstname
     * @param lastname  the lastname
     */
    public Client(int id, String phone, String firstname, String lastname) {
        this.id = id;
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Instantiates a new Client.
     *
     * @param phone     the phone
     * @param firstname the firstname
     * @param lastname  the lastname
     */
    public Client(String phone, String firstname, String lastname) {
        this.phone = phone;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets firstname.
     *
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Sets firstname.
     *
     * @param firstname the firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Gets lastname.
     *
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Sets lastname.
     *
     * @param lastname the lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
