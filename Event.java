class Event implements Comparable<Event> {
    protected final double time;
    protected final Customer customer;

    Event(double time, Customer customer) {
        this.time = time;
        this.customer = customer;
    }

    public boolean hasNextEvent() {
        return false;
    }

    public int updateNumServed(int numServed) {
        return numServed;
    }

    public int updateNumLeft(int numLeft) {
        return numLeft;
    }

    public double updateTotalWaitingTime(double totalWaitingTime) {
        return totalWaitingTime;
    }

    public ImList<Server> updateServerList(ImList<Server> serverList) {
        return serverList;
    }

    public Event nextEvent(ImList<Server> serverList) {
        return this;
    }

    public int compareTo(Event other) {
        if (this.time > other.time) {
            return 1;
        } else if (this.time == other.time) {
            return this.customer.compareTo(other.customer);
        } else {
            return -1;
        }
    }

    public String toString() {
        return String.format("%.3f ", time) + this.customer;
    }
}