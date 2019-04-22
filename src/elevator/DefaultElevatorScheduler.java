package elevator;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

class DefaultElevatorScheduler implements ElevatorScheduler {
    private static final String TAG = "DefaultElevatorScheduler";

    private Collection<Elevator> elevators;

    @Override
    public void setElevators(final Collection<Elevator> elevators) {
        this.elevators = elevators;
    }

    @Override
    public void callRequest(final int requestFloor, final int destinationFloor) {
        ElevatorSimulatorConfig.s(TAG, "Requesting transfer from floor " + requestFloor + " to floor " + destinationFloor);

        if (destinationFloor < ElevatorSimulatorConfig.FLOOR_MIN || destinationFloor > ElevatorSimulatorConfig.FLOOR_MAX) {
            throw new IllegalArgumentException(String.format("Destination floor %d is not within building: %d - %d.",
                    destinationFloor, ElevatorSimulatorConfig.FLOOR_MIN, ElevatorSimulatorConfig.FLOOR_MAX));
        }

        // Pick nearest elevator
        final Elevator closestElevator = chooseElevator(requestFloor);

        // Schedule elevator to perform call request
        closestElevator.transfer(requestFloor, destinationFloor);
    }

    private Elevator chooseElevator(final int requestFloor) {
        // Pick the first elevator that's closest (if tie)
        final Optional<Elevator> closestElevator = elevators.stream().min(new Comparator<Elevator>() {
            @Override
            public int compare(final Elevator left, final Elevator right) {
                return Math.abs(left.getFloor() - requestFloor) <= Math.abs(right.getFloor() - requestFloor) ? -1 : 1;
            }
        });

        return closestElevator.get();
    }
}
