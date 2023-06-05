class Selfchecks extends Server {
    private final ImList<Double> availableTimes;

    Selfchecks(int id, int qmax, int queue, ImList<Double> availableTimes) {
        super(id, qmax, queue);
        this.availableTimes = availableTimes;
    }
    
    public int checkAvailable(double arrivalTime) {
        for (int i = 0; i < availableTimes.size(); i++) {
            if (availableTimes.get(i) <= arrivalTime) {
                return i;
            }
        }
        return -1;
    }

    public Server enqueue() {
        return new Selfchecks(this.id, this.qmax, this.queue + 1, this.availableTimes);
    }

    public Server dequeue() {
        if (queue == 0) {
            return this;
        } else {
            return new Selfchecks(this.id, this.qmax, this.queue - 1, this.availableTimes);
        }
    }

    public Server updateAvailableTime(int index, double newAvailableTime) {
        return new Selfchecks(this.id, this.qmax, this.queue, this.availableTimes.set(index,
                newAvailableTime));
    }

    public double requiredWaitingTime(double currentTime) {
        double earliestAvailableTime = Double.POSITIVE_INFINITY;
        for (int i = 0; i < availableTimes.size(); i++) {
            if (earliestAvailableTime > availableTimes.get(i)) {
                earliestAvailableTime = availableTimes.get(i);
            }
        }
        return earliestAvailableTime - currentTime;
    }

    public String toString(int subId) {
        return String.format("self-check %d ", this.id + subId);
    }
}