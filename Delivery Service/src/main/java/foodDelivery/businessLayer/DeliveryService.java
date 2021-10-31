package foodDelivery.businessLayer;

import foodDelivery.model.BaseProduct;
import foodDelivery.model.Client;
import foodDelivery.model.MenuItem;
import foodDelivery.model.Order;

import java.awt.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * The type Delivery service.
 */
public class DeliveryService extends Observable implements IDeliveryServiceProcessing, Serializable {

    /**
     * The My clients.
     */
    HashMap<String, Client> myClients = new HashMap<String, Client>();
    /**
     * The Product list.
     */
    ArrayList<MenuItem> productList = new ArrayList<>();
    /**
     * The My orders.
     */
    HashMap<Order, ArrayList<MenuItem> > myOrders = new HashMap<Order, ArrayList<MenuItem>>();
    /**
     * The Report counter.
     */
    int[] reportCounter = {0, 0, 0, 0, 0};

    static private Function<String, MenuItem> mapToItem = (line) -> {
        String[] p = line.split(",");// a CSV has comma separated lines
        MenuItem item = new BaseProduct(p[0],
                            Double.parseDouble(p[1]),
                            Integer.parseInt(p[2]),
                            Integer.parseInt(p[3]),
                            Integer.parseInt(p[4]),
                            Integer.parseInt(p[5]),
                            Integer.parseInt(p[6]));
        return item;
    };

    @Override
    public void importCSV(String inputFilePath) { // https://dzone.com/articles/how-to-read-a-big-csv-file-with-java-8-and-stream
        List<MenuItem> inputList = new ArrayList<MenuItem>();

        try{
            File inputF = new File(inputFilePath);
            InputStream inputFS = new FileInputStream(inputF);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputFS));
            // skip the header of the csv
            inputList = new ArrayList<>(br.lines().skip(1).map(mapToItem).collect(Collectors.toMap(MenuItem::getTitle, a -> a, (a, b) -> a)).values());
            br.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        this.productList = (ArrayList<MenuItem>) inputList;
    }

    @Override
    public String validateLogin(String user, String pass) { //Return the given message: Invalid login of user inexistent
        if(user.equals("administrator") || user.equals("employee"))  {
            if(user.equals(pass)) return "Succesful Login"; // Check if the user and pass match
            return "Invalid Username/Password";
        }
        if(myClients.containsKey(user) == false) return "Username not found!\n";
        if(myClients.get(user).getPassword().equals(pass)) return "Succesful Login";
        return "Invalid Username/Password";
    }

    @Override
    public String registerClient(String user, String pass) {
        Client myClient = new Client(myClients.size(), user, pass);
        if(user.equals("administrator") || user.equals("employee")) return "Invalid Username";
        if(myClients.containsKey(user)) return "Username already in use!";
        myClients.put(user, myClient);
        return "Succesfuly Registered";
    }

    @Override
    public String[] convItemToTableFormat(MenuItem x) {
        String[] myItemConverted = new String[7];
        myItemConverted[0] = x.getTitle();
        myItemConverted[1] = String.valueOf(x.getRating());
        myItemConverted[2] = String.valueOf(x.getCalories());
        myItemConverted[3] = String.valueOf(x.getProtein());
        myItemConverted[4] = String.valueOf(x.getFat());
        myItemConverted[5] = String.valueOf(x.getSodium());
        myItemConverted[6] = String.valueOf(x.computePrice());
        return myItemConverted;
    }

    @Override
    public ArrayList<String[]> convToTableFormat() {
        ArrayList<String[]>  productListTableFormat = new ArrayList<String[]>();
        for(MenuItem x : productList) {
            String[] myItem = convItemToTableFormat(x);
            productListTableFormat.add(myItem);
        }
        return productListTableFormat;
    }

    @Override
    public ArrayList<MenuItem> getProductList() {
        return productList;
    }

    @Override
    public HashMap<Order, ArrayList<MenuItem>> getMyOrders() {
        return myOrders;
    }

    @Override
    public void removeProductByIndex(int index) {
        productList.remove(index);
    }

    @Override
    public MenuItem getProductByIndex(int index) {
        return productList.get(index);
    }

    @Override
    public Client getClientByUsername(String user) {
        return myClients.get(user);
    }

    @Override
    public void listProd() {
        for(MenuItem x : productList) System.out.println(x);
    }

    @Override
    public void listUsers() {
        for (Map.Entry<String, Client> e : myClients.entrySet())
            System.out.println("Key: " + e.getKey()
                    + " Value: " + e.getValue());
    }

    @Override
    public void listOrders() {
        for (Map.Entry<Order, ArrayList<MenuItem> > e : myOrders.entrySet()) {
            System.out.println(e.getKey());
            for(MenuItem mI : e.getValue()) System.out.println(mI);
            System.out.println();
        }

    }

    @Override
    public void placeOrder(Order myOrder, ArrayList<MenuItem> orderedProducts) {
        myOrders.put(myOrder, orderedProducts);
    }

    @Override
    public void notifyEmployee(String bill) {
        setChanged();
        notifyObservers(bill);
    }

    @Override
    public int computeProductListPrice(ArrayList<MenuItem> myProductList) {
        int totalPrice = 0;
        for(MenuItem x : myProductList) {
            totalPrice += x.computePrice();
        }
        return totalPrice;
    }

    @Override
    public String getUserById(int id) {
        for(Map.Entry<String, Client> x : myClients.entrySet()) {
            if(x.getValue().getClientID() == id) return x.getValue().getUsername();
        }
        return null;
    }

    @Override
    public String getReportName(int type) {
        String reportName = "Report_" + type + "_" + reportCounter[type] + ".txt";
        reportCounter[type] ++;
        return reportName;
    }

}
