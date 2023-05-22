class ArriveEvent extends Event {
    ArriveEvent(double time, Customer customer) {
        super(time, customer);
    }

    public boolean hasNextEvent() {
        return true;
    }

    public Pair<Event, ImList<Server>> nextEvent(ImList<Server> serverList) {
        for (int i = 0; i < serverList.size(); i++) {
            int flag = serverList.get(i).checkAvailable(this.time);
            if (flag != -1) {
                double serviceTime = customer.getServiceTime();
                return new Pair<Event, ImList<Server>>(
                    new ServeEvent(this.time, serviceTime, this.customer, i, flag), 
                    serverList.set(i, serverList.get(i).updateAvailableTime(flag, this.time +
                    serviceTime)));
            }
        }
        for (int i = 0; i < serverList.size(); i++) {
            if (serverList.get(i).checkQueue()) {
                int flag = serverList.get(i).checkAvailable(Double.POSITIVE_INFINITY);
                return new Pair<Event, ImList<Server>>(
                    new WaitEvent(this.time, serverList.get(i).requiredWaitingTime(this.time),
                    this.customer, i, flag), serverList.set(i, serverList.get(i).enqueue()));
            }
        }
        return new Pair<Event, ImList<Server>>(new LeaveEvent(this.time, this.customer), 
        serverList);
    }

    public String toString() {
        return super.toString() + "arrives" + "\n";
    }
}
