class Server {
    protected final int id;
    protected final int qmax;
    protected final int queue;

    Server(int id, int qmax, int queue) {
        this.id = id;
        this.qmax = qmax;
        this.queue = queue;
    }

    public int checkAvailable(double arrivalTime) {
        return 0;
    }

    public boolean checkQueue() {
        if (this.queue < this.qmax) {
            return true;
        } else {
            return false;
        }
    }

    public Server enqueue() {
        return new Server(this.id, this.qmax, this.queue + 1);
    }

    public Server dequeue() {
        if (queue == 0) {
            return this;
        } else {
            return new Server(this.id, this.qmax, this.queue - 1);
        }
    }

    public Server checkRest() {
        return this;
    }

    public Server updateAvailableTime(int index, double newAvailableTime) {
        return this;
    }

    public double requiredWaitingTime(double currentTime) {
        return 0;
    }
}