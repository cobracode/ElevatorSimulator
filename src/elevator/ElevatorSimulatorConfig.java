package elevator;

class ElevatorSimulatorConfig {
    public static final int MAX_ELEVATORS = 10;
    public static final int FLOOR_MIN = 1;
    public static final int FLOOR_MAX = 100;
    public static final int NUM_FLOORS = 100;

    // This is quick and just temporary; in real code, a logging
    // framework like logback or log4j2 would be used.
    public static void s(final String tag, final String msg) {
        System.out.println(tag + ": " + msg);
    }
}
