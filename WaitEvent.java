class WaitEvent extends Event {
    private final int serverIndex;
    private final String serverType;
    private final double waitingTime;

    WaitEvent(double time, double waitingTime, Customer customer, int serverIndex, 
            String serverType) {
        super(time, customer);
        this.waitingTime = waitingTime;
        this.serverIndex = serverIndex;
        this.serverType = serverType;
    }

    public double updateTotalWaitingTime(double totalWaitingTime) {
        return totalWaitingTime + waitingTime;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Event nextEvent(ImList<Server> serverList) {
        return new QueueEvent(this.time + this.waitingTime, 0.0,
                this.customer, this.serverIndex);
    }

    public ImList<Server> updateServerList(ImList<Server> serverList) {
        return serverList.set(serverIndex, serverList.get(serverIndex).enqueue());
    }

    public String toString() {
        return super.toString() + "waits at " + serverType + (serverIndex + 1) + "\n";
    }
}
