package ir.oxima.progressbutton;

/**
 * Created by hosein on 2/17/2017.
 */

public enum ButtonState {

    Connecting(1),
    Cancel(2),
    Pause(3),
    Progress(4),
    Error(5),
    Complete(6),
    Default(6);

    public static final int SIZE = Integer.SIZE;

    private int intValue;
    private static java.util.HashMap<Integer, ButtonState> mappings;

    private static java.util.HashMap<Integer, ButtonState> getMappings() {
        if (mappings == null) {
            synchronized (ButtonState.class) {
                if (mappings == null) {
                    mappings = new java.util.HashMap<Integer, ButtonState>();
                }
            }
        }
        return mappings;
    }

    ButtonState(int value) {
        intValue = value;
        getMappings().put(value, this);
    }

    public int getValue() {
        return intValue;
    }

    public static ButtonState forValue(int value) {
        return getMappings().get(value);
    }

    @Override
    public String toString() {
        switch (this) {

            case Connecting:
                return "Connecting ...";
            case Cancel:
                return "Download";
            case Pause:
                return "Resume";
            case Progress:
                return "Pause";
            case Error:
                return "Download";
            case Complete:
                return "Show";
            case Default:
                return "Download";
            default:
                return "Download";
        }
    }
}
