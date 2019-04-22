package elevator;

import java.util.ArrayList;
import java.util.Collection;

public class ElevatorSimulator {
    private static String TAG = "ElevatorSimulator";

    private final Floor[] floors = new Floor[ElevatorSimulatorConfig.NUM_FLOORS];

    private ArrayList<Elevator> elevators;

    public ElevatorSimulator(final int numElevators, final ElevatorScheduler scheduler) {
        if (numElevators < 1 || numElevators > ElevatorSimulatorConfig.MAX_ELEVATORS) {
            throw new IllegalArgumentException(
                String.format("Cannot create ElevatorSimulator: numElevators must be between %d and %d; was %d.",
                    1, ElevatorSimulatorConfig.MAX_ELEVATORS, numElevators));
        }

        if (scheduler == null) {
            throw new IllegalArgumentException("null ElevatorScheduler");
        }

        ElevatorSimulatorConfig.s(TAG, "Creating ElevatorSimulator with " + numElevators + " elevators and " + scheduler + " scheduler.");

        createFloors(scheduler);
        scheduler.setElevators(createElevators(numElevators));
    }

    private void createFloors(final ElevatorScheduler scheduler) {
        for (int x = 0; x < ElevatorSimulatorConfig.NUM_FLOORS; x++) {
            floors[x] = new Floor(x + 1, scheduler);
        }
    }

    /**
     * Elevators are initialized all on the first (lowest) floor.
     * @param numElevators
     */
    private Collection<Elevator> createElevators(final int numElevators) {
        elevators = new ArrayList<>(numElevators);

        for (int x = 0; x < numElevators; x++) {
            elevators.add(new Elevator(x + 1, ElevatorSimulatorConfig.FLOOR_MIN));
        }

        return elevators;
    }

    private void run() {
        ElevatorSimulatorConfig.s(TAG, "Beginning elevator simulation");

        // Typical use case. Starting in the morning, everyone and all
        // elevators on ground floor. Going up to their office floors.
        floors[0].callRequest(20);
        floors[0].callRequest(15);
        floors[0].callRequest(88);
        floors[0].callRequest(10);
        floors[0].callRequest(100);
        floors[0].callRequest(77);

        // Near lunch time; people start going down
        floors[99].callRequest(50);
        floors[87].callRequest(14);
        floors[14].callRequest(45);
        floors[19].callRequest(22);

        // Exceptional cases
        // Source floor and destination floor cannot be same
        try {
            floors[49].callRequest(50);
        } catch (final ElevatorCallRequestSameFloorException e) {
            ElevatorSimulatorConfig.s(TAG, e.toString());
        }

        // Cannot go above max floor
        try {
            floors[49].callRequest(ElevatorSimulatorConfig.FLOOR_MAX + 1);
        } catch (final IllegalArgumentException e) {
            ElevatorSimulatorConfig.s(TAG, e.toString());
        }

        // ...or below min floor
        try {
            floors[49].callRequest(ElevatorSimulatorConfig.FLOOR_MIN - 1);
        } catch (final IllegalArgumentException e) {
            ElevatorSimulatorConfig.s(TAG, e.toString());
        }
    }

    public static void main(final String[] args) {
        final ElevatorSimulator sim = new ElevatorSimulator(10, new DefaultElevatorScheduler());
        sim.run();
    }
}
