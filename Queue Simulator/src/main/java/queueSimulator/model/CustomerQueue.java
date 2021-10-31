package queueSimulator.model;

import java.io.PrintWriter;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomerQueue extends Thread{

    private volatile AtomicInteger currentTime;

    private int currentInternalTime;
    private int currentQueueEnd;
    private int waitingTime;
    private int timeDelay;
    private int id;

    private boolean running;
    private BlockingQueue<Customer> myQueue;

    public CustomerQueue(int iD, AtomicInteger currenttime, int timedelay) {
        currentTime = currenttime;

        currentInternalTime = 0;
        currentQueueEnd = 0;
        waitingTime = 0;
        timeDelay = timedelay;
        id = iD;

        running = true;
        myQueue = new LinkedBlockingDeque<Customer>();
    }

    public int status() {
        if(myQueue.isEmpty()) return 0;
        return 1;
    }

    public void terminate() {
        running = false;
    }

    public int getCurrentQueueEnd () {
        return currentQueueEnd;
    }

    public void addCustomer(Customer x) {
        myQueue.add(x);
        currentQueueEnd = Math.max(currentQueueEnd, x.getArrival()) + x.getServiceTime();
    }

    public int getWaitingTime() { return waitingTime; }

    public int getSize() { return myQueue.size(); }

    public String printContent() {
        String s ="Queue " + id + ": ";
        for(Customer x : myQueue) { s = s + "(" + x.getId() + ", " + x.getArrival() + ", " + x.getServiceTime() + ")  "; }
        if(this.status() == 0) s = s + "closed";
        s = s + "\n";
        return s;
    }

    public void serveCustomer() {
        if(!myQueue.isEmpty()) {
            myQueue.element().decreaseServiceTime();
            if(myQueue.element().getServiceTime() <= 0) {
                try {
                    myQueue.take();
                } catch (InterruptedException ie) {
                    System.out.println("Caught exception at deleting " + ie);
                }
            }
        }
    }

    public void run() {
        try {
            Thread.sleep(timeDelay);
            while(running) {
                if(currentTime.get() == currentInternalTime){
                    if(myQueue.size() > 0)waitingTime += (myQueue.size() - 1);
                    serveCustomer();
                    ++currentInternalTime;
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("Crashed Queue " + timeDelay);
        }

    }
}
