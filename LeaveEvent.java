class LeaveEvent extends Event {
    LeaveEvent(double time, Customer customer) {
        super(time, customer);
    }

    public int updateNumLeft(int numLeft) {
        return numLeft + 1;
    }

    public String toString() {
        return super.toString() + "leaves\n";
    }
}
