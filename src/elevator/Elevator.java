package elevator;

class Elevator {
    private final int id;
    private int floor;

    Elevator(final int id, final int floor) {
        this.id = id;
        this.floor = floor;
    }

    int getId() {
        return id;
    }

    int getFloor() {
        return floor;
    }

    public String toString() {
        return "Elevator " + id + " on floor " + floor;
    }

    public void transfer(int requestFloor, int destinationFloor) {
        ElevatorSimulatorConfig.s("Elevator", String.format("%s performing call request from floor %d to floor %d.",
                this, requestFloor, destinationFloor));

        // Elevator ends up on destination floor after transfer.
        floor = destinationFloor;
    }
}
