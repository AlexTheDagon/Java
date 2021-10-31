package queueSimulator.model;

import javafx.stage.Stage;
import queueSimulator.utility.*;
import queueSimulator.view.GUI;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueManager extends Thread{

    volatile AtomicInteger currentTime;
    private SimulationConstraints myConstraints;
    private ArrayList<CustomerQueue> myQueues;
    private CustomerGenerator customers;
    private float totalWaitingTime;
    private int nrClients;
    private int maxClients;
    private int peakHour;
    private int stopManager;
    GUI gui;
    public QueueManager(SimulationConstraints myConstraints, GUI myGuy) {
        this.currentTime = new AtomicInteger(0);
        this.myConstraints = myConstraints;
        customers = new CustomerGenerator(myConstraints);
        myQueues = new ArrayList<CustomerQueue>();
        totalWaitingTime = 0.0f;
        maxClients = 0;
        peakHour = 0;
        stopManager = 0;
        gui = myGuy;
        for(int i = 1; i <= myConstraints.getQ(); ++i){
            CustomerQueue myCustomerQueue = new CustomerQueue(i, currentTime, i);
            myQueues.add(myCustomerQueue);
        }
    }

    public CustomerQueue getMinWaitingQueue() {
        CustomerQueue minQ = myQueues.get(0);
        for(CustomerQueue q : myQueues) {
            if(q.getCurrentQueueEnd() < minQ.getCurrentQueueEnd()) {
                minQ = q;
            }
        }
        return minQ;
    }

    public String waitingCustomers() {
        String s = "Waiting customers: ";
        for(Customer x : customers.getMyCustomers()) { s = s + "(" + x.getId() + ", " + x.getArrival() + ", " + x.getServiceTime() + ")  "; }
        return s;
    }

    public String printShopStatus() {
        String logPart = "Time " + currentTime.get() + "\n";
        logPart = logPart + waitingCustomers() + "\n";
        for(CustomerQueue q : myQueues) logPart = logPart + q.printContent();
        logPart = logPart + "\n";
        return logPart;
    }

    public void work() {
        ArrayList<Customer> served = new ArrayList<>();
        for(Customer c : customers.getMyCustomers()) {
            if(currentTime.get() >= c.getArrival()) {
                getMinWaitingQueue().addCustomer(c);
                served.add(c);
            }
        }
        for(Customer c : served) {
            customers.removeCustomer(c);
        }
    }

    public int getStopManager() { return stopManager; }


    public void run() {
        customers.print();
        for(CustomerQueue cQ : myQueues) {
            cQ.start();
        }
        try {
            File file = new File("log.txt");
            FileWriter fw = new FileWriter(file);
            PrintWriter pw = new PrintWriter(fw);
            Thread.sleep(100);
            while(currentTime.get() <= myConstraints.getMaxSimulation()) {
                work();
                pw.println(printShopStatus());
                gui.getMyShop().setText(printShopStatus());
                nrClients = 0;
                if(currentTime.get() == myConstraints.getMaxSimulation()) {
                    for(CustomerQueue cQ : myQueues) {
                        cQ.terminate();
                    }
                }
                for(CustomerQueue cQ : myQueues) {
                    nrClients += cQ.getSize();
                }
                if(nrClients > maxClients) {
                    maxClients = nrClients;
                    peakHour = currentTime.get();
                }
                currentTime.getAndAdd(1);
                Thread.sleep(1000);
            }
            for(CustomerQueue cQ : myQueues) {
                totalWaitingTime += cQ.getWaitingTime();
                cQ.terminate();
            }
            pw.println();
            pw.println("Total waiting time = " + totalWaitingTime);
            totalWaitingTime /= myConstraints.getN();
            pw.println("Average waiting time = " + totalWaitingTime);
            pw.println("Peak Hour = " + peakHour);
            pw.close();
        } catch (InterruptedException|IOException ex) {
            System.out.println("Crashed Queue Manager");
        }
        stopManager = 1;
    }
}
