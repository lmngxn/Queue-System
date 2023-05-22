import java.util.function.Supplier;

class Server {
    protected final int id;
    protected final int qmax;
    protected final int queue;
    protected final ImList<Double> availableTimes;
    protected final Supplier<Double> restTimes;

    Server(int id, int qmax, int queue, ImList<Double> availableTimes, Supplier<Double> restTimes) {
        this.id = id;
        this.qmax = qmax;
        this.queue = queue;
        this.availableTimes = availableTimes;
        this.restTimes = restTimes;
    }

    public int checkAvailable(double arrivalTime) {
        if (availableTimes.get(0) <= arrivalTime) {
            return 0;
        } else {
            return -1;
        }
    }

    public boolean checkQueue() {
        if (this.queue < this.qmax) {
            return true;
        } else {
            return false;
        }
    }

    public Server checkRest() {
        double temp = restTimes.get();
        if (temp > 0) {
            return new Server(this.id, this.qmax, this.queue, this.availableTimes.set(0, 
                    this.availableTimes.get(0) + temp), this.restTimes);
        } else {
            return this;
        }
    }

    public Server enqueue() {
        return new Server(this.id, this.qmax, this.queue + 1, this.availableTimes, this.restTimes);
    }

    public Server dequeue() {
        if (queue == 0) {
            return this;
        } else {
            return new Server(this.id, this.qmax, this.queue - 1, this.availableTimes, 
                    this.restTimes);
        }
    }

    public Server updateAvailableTime(int index, double newAvailableTime) {
        return new Server(this.id, this.qmax, this.queue, this.availableTimes.set(index,
                newAvailableTime), this.restTimes);
    }

    public double requiredWaitingTime(double currentTime) {
        return this.availableTimes.get(0) - currentTime;
    }

    public String toString() {
        return String.format("%s", this.id);
    }
}