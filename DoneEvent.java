class DoneEvent extends Event {
    private final Server server;
    private final int subIndex;

    DoneEvent(double time, Customer customer, int subIndex, Server server) {
        super(time, customer);
        this.server = server;
        this.subIndex = subIndex;
    }

    public ImList<Server> updateServerList(ImList<Server> serverList) {
        return serverList.set(server.getIndex(), serverList.get(server.getIndex()).checkRest());
    }

    public String toString() {
        return super.toString() + "done serving by " + server.toString(subIndex) + "\n";
    }
}
