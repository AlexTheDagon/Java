package queueSimulator.utility;

import queueSimulator.model.Customer;
import queueSimulator.model.SimulationConstraints;

import java.util.ArrayList;
import java.util.Collections;

public class CustomerGenerator {
    private SimulationConstraints myConstraints;
    private ArrayList<Customer> myCustomers = new ArrayList<>();

    public Customer generateOneCustomer(int id) {
        int minArrival = myConstraints.getMinArrival();
        int maxArrival = myConstraints.getMaxArrival();
        int minService = myConstraints.getMinService();
        int maxService = myConstraints.getMaxService();
        int xArrival = (int)Math.floor(Math.random()*(maxArrival-minArrival+1)+minArrival);
        int xService = (int)Math.floor(Math.random()*(maxService-minService+1)+minArrival);
        xService = (int)Math.min(xService, myConstraints.getMaxSimulation() - xArrival);
        Customer x = new Customer(id, xArrival, xService);
        return x;
    }

    public ArrayList<Customer> getMyCustomers () {
        return myCustomers;
    }

    public void removeCustomer(Customer c) {
        myCustomers.remove(c);
    }
    public CustomerGenerator(SimulationConstraints constraints) {
        this.myConstraints = constraints;
        System.out.println(myConstraints);
        int n = myConstraints.getN();
        for(int id = 1; id <= n; ++id){
            myCustomers.add(generateOneCustomer(id));
        }
        Collections.sort(this.myCustomers);
        int id = 0;
        for(Customer x: myCustomers) {
            id ++;
            x.setId(id);
        }
    }

    public void print() {
        for(Customer x: myCustomers) {
            System.out.println(x);
        }
        System.out.println();
    }
}
