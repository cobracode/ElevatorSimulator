package elevator;

import java.util.Collection;

interface ElevatorScheduler {
    /**
     * Set the elevators that the scheduler will use to perform the call requests on.
     * @param elevators
     */
    void setElevators(final Collection<Elevator> elevators);

    /**
     * Perform a call request from source floor to destination floor.
     * @param sourceFloor
     * @param destFloor
     */
    void callRequest(int sourceFloor, int destFloor);
}
