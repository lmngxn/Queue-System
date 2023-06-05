class ArriveEvent extends Event {
    ArriveEvent(double time, Customer customer) {
        super(time, customer);
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Event nextEvent(ImList<Server> serverList) {
        for (int i = 0; i < serverList.size(); i++) {
            int flag = serverList.get(i).checkAvailable(this.time);
            if (flag != -1) {
                double serviceTime = customer.getServiceTime();
                return new ServeEvent(this.time, serviceTime, this.customer, flag, 
                        serverList.get(i));
            }
        }
        for (int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).checkQueue()) {
                return new WaitEvent(this.time, serverList.get(i).requiredWaitingTime(this.time),
                        this.customer, serverList.get(i));
            }
        }
        return new LeaveEvent(this.time, this.customer);
    }

    public String toString() {
        return super.toString() + "arrives" + "\n";
    }
}
