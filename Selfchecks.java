import java.util.function.Supplier;

class Selfchecks extends Server {
    Selfchecks(int id, int qmax, int queue, ImList<Double> availableTimes, 
            Supplier<Double> restTimes) {
        super(id, qmax, queue, availableTimes, restTimes);
    }
    
    public int checkAvailable(double arrivalTime) {
        for (int i = 0; i < availableTimes.size(); i++) {
            if (availableTimes.get(i) <= arrivalTime) {
                return i + 1;
            }
        }
        return -1;
    }

    public Server checkRest() {
        return this;
    }

    public Server enqueue() {
        return new Selfchecks(this.id, this.qmax, this.queue + 1, this.availableTimes, 
                this.restTimes);
    }

    public Server dequeue() {
        if (queue == 0) {
            return this;
        } else {
            return new Selfchecks(this.id, this.qmax, this.queue - 1, this.availableTimes, 
                    this.restTimes);
        }
    }

    public Server updateAvailableTime(int index, double newAvailableTime) {
        return new Selfchecks(this.id, this.qmax, this.queue, this.availableTimes.set(index - 1, 
                newAvailableTime), this.restTimes);
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
}