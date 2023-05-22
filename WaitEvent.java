class WaitEvent extends Event {
    private final int serverIndex;
    private final int subIndex;
    private final double waitingTime;

    WaitEvent(double time, double waitingTime, Customer customer, int serverIndex, int subIndex) {
        super(time, customer);
        this.waitingTime = waitingTime;
        this.serverIndex = serverIndex;
        this.subIndex = subIndex;
    }

    public double updateTotalWaitingTime(double totalWaitingTime) {
        return totalWaitingTime + waitingTime;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        return new Pair<Event, ImList<Server>>(new QueueEvent(this.time + this.waitingTime, 0.0,
                this.customer, this.serverIndex), serverList);
    }

    public String toString() {
        if (subIndex == 0) {
            return super.toString() + "waits at " + (serverIndex + 1) + "\n";
        } else {
            return super.toString() + "waits at self-check " + (serverIndex + 1) + "\n";
        }
    }
}
