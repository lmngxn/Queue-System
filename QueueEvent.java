class QueueEvent extends Event {
    private final int serverIndex;
    private final double addtnWaitingTime;

    QueueEvent(double time, double waitingTime, Customer customer, int serverIndex) {
        super(time, customer);
        this.addtnWaitingTime = waitingTime;
        this.serverIndex = serverIndex;
    }
    
    public double updateTotalWaitingTime(double totalWaitingTime) {
        return totalWaitingTime + addtnWaitingTime;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        int flag = serverList.get(serverIndex).checkAvailable(this.time);
        if (flag != -1) {
            double serviceTime = customer.getServiceTime();
            return new Pair<Event, ImList<Server>>(new ServeEvent(this.time, serviceTime, 
                this.customer, serverIndex, flag), serverList.set(serverIndex, 
                serverList.get(serverIndex).updateAvailableTime(flag, this.time + serviceTime)));
        } else {
            return new Pair<Event, ImList<Server>>(new QueueEvent(this.time + 
                serverList.get(serverIndex).requiredWaitingTime(this.time), 
                serverList.get(serverIndex).requiredWaitingTime(this.time),
                this.customer, serverIndex), serverList);
        }
    }

    public String toString() {
        return "";
    }
}
