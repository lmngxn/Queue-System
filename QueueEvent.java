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

    public Event nextEvent(ImList<Server> serverList) {
        int flag = serverList.get(serverIndex).checkAvailable(this.time);
        if (flag != -1) {
            double serviceTime = customer.getServiceTime();
            String serverType = serverList.get(serverIndex).toString();
            return new ServeEvent(this.time, serviceTime, this.customer, serverIndex, flag, 
                    serverType);
        } else {
            return new QueueEvent(this.time + 
                serverList.get(serverIndex).requiredWaitingTime(this.time),
                serverList.get(serverIndex).requiredWaitingTime(this.time), 
                this.customer, serverIndex);
        }
    }

    public String toString() {
        return "";
    }
}
