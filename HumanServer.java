import java.util.function.Supplier;

class HumanServer extends Server {
    private final double availableTime;
    private final Supplier<Double> restTimes;

    HumanServer(int id, int qmax, int queue, double availableTime, Supplier<Double> restTimes) {
        super(id, qmax, queue);
        this.availableTime = availableTime;
        this.restTimes = restTimes;
    }

    public int checkAvailable(double currentTime) {
        if (availableTime <= currentTime) {
            return 0;
        } else {
            return -1;
        }
    }

    public Server enqueue() {
        return new HumanServer(this.id, this.qmax, this.queue + 1, this.availableTime, 
                this.restTimes);
    }

    public Server dequeue() {
        if (queue == 0) {
            return this;
        } else {
            return new HumanServer(this.id, this.qmax, this.queue - 1, this.availableTime, 
                    this.restTimes);
        }
    }

    public Server checkRest() {
        double restTime = restTimes.get();
        if (restTime > 0) {
            return new HumanServer(this.id, this.qmax, this.queue, this.availableTime + restTime, 
                this.restTimes);
        } else {
            return this;
        }
    }
    
    public Server updateAvailableTime(int index, double newAvailableTime) {
        return new HumanServer(this.id, this.qmax, this.queue, newAvailableTime, this.restTimes);
    }

    public double requiredWaitingTime(double currentTime) {
        return this.availableTime - currentTime;
    }

    public String toString() {
        return "";
    }
}
