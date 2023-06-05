class WaitEvent extends Event {
    private final Server server;
    private final double waitingTime;

    WaitEvent(double time, double waitingTime, Customer customer, Server server) {
        super(time, customer);
        this.waitingTime = waitingTime;
        this.server = server;
    }

    public double updateTotalWaitingTime(double totalWaitingTime) {
        return totalWaitingTime + waitingTime;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Event nextEvent(ImList<Server> serverList) {
        return new QueueEvent(this.time + this.waitingTime, 0.0,
                this.customer, this.server);
    }

    public ImList<Server> updateServerList(ImList<Server> serverList) {
        return serverList.set(server.getIndex(), serverList.get(server.getIndex()).enqueue());
    }

    public String toString() {
        return super.toString() + "waits at " + server.toString(0) + "\n";
    }
}
