package elevator;

class Floor {
    private static final String TAG = "\nFloor";

    private ElevatorScheduler scheduler;

    // Set once and immutable
    private final int num;

    Floor(final int num, final ElevatorScheduler scheduler) {
        this.num = num;
        this.scheduler = scheduler;
    }

    int getNum() {
        return num;
    }

    void callRequest(final int destinationFloor) {
        ElevatorSimulatorConfig.s(TAG, "Call request from floor " + num + " to floor " + destinationFloor);

        if (destinationFloor == num) {
            throw new ElevatorCallRequestSameFloorException("Already on destination floor " + destinationFloor);
        }

        scheduler.callRequest(num, destinationFloor);
    }
}
