class ServeEvent extends Event {
    private final int serverIndex;
    private final int subIndex;
    private final double serviceTime;

    ServeEvent(double time, double serviceTime, Customer customer, int serverIndex, int subIndex) {
        super(time, customer);
        this.serverIndex = serverIndex;
        this.subIndex = subIndex;
        this.serviceTime = serviceTime;
    }

    public int updateNumServed(int numServed) {
        return numServed + 1;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        return new Pair<Event, ImList<Server>>(new DoneEvent(this.time + this.serviceTime,
                this.customer, this.serverIndex, this.subIndex), serverList.set(this.serverIndex, 
                serverList.get(serverIndex).dequeue()));
    }
    
    public String toString() {
        if (subIndex == 0) {
            return super.toString() + "serves by " + (this.serverIndex + 1) + "\n";
        } else {
            return super.toString() + "serves by self-check " + (this.serverIndex +
                    this.subIndex) + "\n";
        }
    }
}