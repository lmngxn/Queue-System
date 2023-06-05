class ServeEvent extends Event {
    private final int subIndex;
    private final Server server;
    private final double serviceTime;

    ServeEvent(double time, double serviceTime, Customer customer, int subIndex,
            Server server) {
        super(time, customer);
        this.subIndex = subIndex;
        this.server = server;
        this.serviceTime = serviceTime;
    }

    public int updateNumServed(int numServed) {
        return numServed + 1;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Event nextEvent(ImList<Server> serverList) {
        return new DoneEvent(this.time + this.serviceTime, this.customer, 
                    this.subIndex, this.server);
    }

    public ImList<Server> updateServerList(ImList<Server> serverList) {
        return serverList.set(server.getIndex(), serverList.get(server.getIndex()).dequeue()
                    .updateAvailableTime(subIndex, this.time + this.serviceTime));
    }
    
    public String toString() {
        return super.toString() + "serves by " + server.toString(subIndex) + "\n";
    }
}