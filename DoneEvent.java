class DoneEvent extends Event {
    private final int serverIndex;
    private final int subIndex;
    private final String serverType;

    DoneEvent(double time, Customer customer, int serverIndex, int subIndex, String serverType) {
        super(time, customer);
        this.serverIndex = serverIndex;
        this.subIndex = subIndex;
        this.serverType = serverType;
    }

    public ImList<Server> updateServerList(ImList<Server> serverList) {
        if (serverType == "") {
            return serverList.set(serverIndex, serverList.get(serverIndex).checkRest());
        }
        return serverList;
    }

    public String toString() {
        return super.toString() + "done serving by " + serverType + (this.serverIndex + 
                this.subIndex + 1) + "\n";
    }
}
