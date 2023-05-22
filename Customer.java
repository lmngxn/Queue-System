import java.util.function.Supplier;

class Customer implements Comparable<Customer> {
    private final int id;
    private final double arrivalTime;
    private final double waitingTime;
    private final Supplier<Double> serviceTime;

    Customer(int id, double arrivalTime, double waitingTime, Supplier<Double> serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.waitingTime = waitingTime;
        this.serviceTime = serviceTime;
    }

    public Customer updateWaitingTime(double waitingTime) {
        return new Customer(this.id, this.arrivalTime, waitingTime, this.serviceTime);
    }

    public double getWaitingTime() {
        return waitingTime;
    }

    public double getServiceTime() {
        return this.serviceTime.get();
    }

    public int compareTo(Customer other) {
        return this.id - other.id;
    }
    
    public String toString() {
        return String.format("%s ", this.id);
    }
}
