class ServeEvent extends Event {
    private final int serverIndex;
    private final int subIndex;
    private final String serverType;
    private final double serviceTime;

    ServeEvent(double time, double serviceTime, Customer customer, int serverIndex, int subIndex,
            String serverType) {
        super(time, customer);
        this.serverIndex = serverIndex;
        this.subIndex = subIndex;
        this.serverType = serverType;
        this.serviceTime = serviceTime;
    }

    public int updateNumServed(int numServed) {
        return numServed + 1;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Event nextEvent(ImList<Server> serverList) {
        return new DoneEvent(this.time + this.serviceTime, this.customer, this.serverIndex, 
                    this.subIndex, this.serverType);
    }

    public ImList<Server> updateServerList(ImList<Server> serverList) {
        return serverList.set(serverIndex, serverList.get(serverIndex).dequeue()
                    .updateAvailableTime(subIndex, this.time + this.serviceTime));
    }
    
    public String toString() {
        return super.toString() + "serves by " + serverType + (this.serverIndex + this.subIndex +
                1) + "\n";
    }
}