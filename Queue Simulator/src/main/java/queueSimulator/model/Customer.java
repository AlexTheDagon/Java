package queueSimulator.model;

public class Customer implements Comparable{
    private int id;
    private int arrival;
    private int serviceTime;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getArrival() { return arrival; }
    public void setArrival(int arrival) { this.arrival = arrival; }

    public int getServiceTime() { return serviceTime; }
    public void setServiceTime(int serviceTime) { this.serviceTime = serviceTime; }

    public Customer(int id, int arrival, int serviceTime) {
        this.id = id;
        this.arrival = arrival;
        this.serviceTime = serviceTime;
    }

    public void decreaseServiceTime() {
        this.serviceTime--;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", arrival=" + arrival +
                ", serviceTime=" + serviceTime +
                '}';
    }

    @Override
    public int compareTo(Object compareTo) {
        int arrivalCompare = this.arrival - ((Customer) compareTo).getArrival();
        int serviceCompare = this.serviceTime - ((Customer) compareTo).getServiceTime();
        if(arrivalCompare == 0)return serviceCompare;
        return arrivalCompare;
    }
}
