package elevator;

class ElevatorCallRequestSameFloorException extends RuntimeException {
    ElevatorCallRequestSameFloorException(final String msg) {
        super(msg);
    }
}
