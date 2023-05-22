class DoneEvent extends Event {
    private final int serverIndex;
    private final int subIndex;

    DoneEvent(double time, Customer customer, int serverIndex, int subIndex) {
        super(time, customer);
        this.serverIndex = serverIndex;
        this.subIndex = subIndex;
    }

    public ImList<Server> checkRest(ImList<Server> serverList) {
        return serverList.set(serverIndex, serverList.get(serverIndex).checkRest());
    }

    public String toString() {
        if (subIndex == 0) {
            return super.toString() + "done serving by " + (this.serverIndex + 1) + "\n";
        } else {
            return super.toString() + "done serving by self-check " + (this.serverIndex + 
                    this.subIndex) + "\n";
        }
    }
}
