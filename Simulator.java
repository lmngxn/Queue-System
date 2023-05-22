import java.util.function.Supplier;

class Simulator {
    private final int numOfServers;
    private final int numOfSelfChecks;
    private final int qmax;
    private final ImList<Double> arrivalTimes;
    private final Supplier<Double> serviceTime;
    private final Supplier<Double> restTimes;

    Simulator(int numOfServers, int numOfSelfChecks, int qmax, ImList<Double> arrivalTimes, 
            Supplier<Double> serviceTime, Supplier<Double> restTimes) {
        this.numOfServers = numOfServers;
        this.numOfSelfChecks = numOfSelfChecks;
        this.qmax = qmax;
        this.arrivalTimes = arrivalTimes;
        this.serviceTime = serviceTime;
        this.restTimes = restTimes;
    }

    public String simulate() {
        int numServed = 0;
        int numLeft = 0;
        double totalWaitingTime = 0.0;
        double avgWaitingTime;
        String output = new String();
        ImList<Server> serverList = new ImList<Server>();
        PQ<Event> pq = new PQ<Event>(new EventComp());
        
        for (int i = 1; i <= numOfServers; i++) {
            serverList = serverList.add(new Server(i, qmax, 0, 
                    new ImList<Double>().add(0.0), restTimes));
        }
        
        if (numOfSelfChecks > 0) {
            ImList<Double> selfchecksAT = new ImList<Double>();
            for (int i = 1; i <= numOfSelfChecks; i++) {
                selfchecksAT = selfchecksAT.add(0.0);
            }
            serverList = serverList.add(new Selfchecks(numOfServers + 1, qmax, 0, 
                    selfchecksAT, restTimes));
        }

        for (int i = 0; i < arrivalTimes.size(); i++) {
            pq = pq.add(new ArriveEvent(this.arrivalTimes.get(i), new Customer(i + 1, 
                    this.arrivalTimes.get(i), 0.0, serviceTime)));
        }
        Pair<Event, PQ<Event>> currentPair;
        Event currentEvent;
        while (!pq.isEmpty()) {
            currentPair = pq.poll();
            currentEvent = currentPair.first();
            pq = currentPair.second();
            output += currentEvent;
            numServed = currentEvent.updateNumServed(numServed);
            numLeft = currentEvent.updateNumLeft(numLeft);
            totalWaitingTime = currentEvent.updateTotalWaitingTime(totalWaitingTime);
            serverList = currentEvent.checkRest(serverList);
            if (currentEvent.hasNextEvent()) {
                Pair<Event, ImList<Server>> temp = currentEvent.nextEvent(serverList);
                pq = pq.add(temp.first());
                serverList = temp.second();
            }
        }
        if (numServed != 0) {
            avgWaitingTime = totalWaitingTime / numServed;
        } else {
            avgWaitingTime = 0;
        }
        output += String.format("[%.3f %s %s]", avgWaitingTime, numServed, numLeft);
        return output;
    }
}
