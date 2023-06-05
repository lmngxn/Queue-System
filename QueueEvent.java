class QueueEvent extends Event {
    private final Server server;
    private final double addtnWaitingTime;

    QueueEvent(double time, double waitingTime, Customer customer, Server server) {
        super(time, customer);
        this.addtnWaitingTime = waitingTime;
        this.server = server;
    }
    
    public double updateTotalWaitingTime(double totalWaitingTime) {
        return totalWaitingTime + addtnWaitingTime;
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Event nextEvent(ImList<Server> serverList) {
        int flag = serverList.get(server.getIndex()).checkAvailable(this.time);
        if (flag != -1) {
            double serviceTime = customer.getServiceTime();
            return new ServeEvent(this.time, serviceTime, this.customer, flag, 
                serverList.get(server.getIndex()));
        } else {
            return new QueueEvent(this.time + 
                serverList.get(server.getIndex()).requiredWaitingTime(this.time),
                serverList.get(server.getIndex()).requiredWaitingTime(this.time), 
                this.customer, serverList.get(server.getIndex()));
        }
    }

    public String toString() {
        return "";
    }
}
