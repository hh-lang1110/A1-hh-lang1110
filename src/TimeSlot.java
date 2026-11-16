import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Fixed daily clinic schedule. The clinic runs from 08:00 to 16:00 in
 * 30-minute slots. Changing the opening hours or interval only requires
 * updating the values in this class; the rest of the booking system keeps
 * working against the same slot list.
 */
public final class TimeSlot {

    /** All valid appointment slots in chronological order. */
    private static final List<String> ALL_SLOTS = new ArrayList<>();

    static {
        for (int hour = 8; hour < 16; hour++) {
            ALL_SLOTS.add(String.format("%02d:00", hour));
            ALL_SLOTS.add(String.format("%02d:30", hour));
        }
        ALL_SLOTS.add("16:00");
    }

    /** Private constructor: this class only exposes static helpers. */
    private TimeSlot() {
    }

    /**
     * Returns an unmodifiable copy of all valid slots.
     *
     * @return list of slot labels in order
     */
    public static List<String> getAllSlots() {
        return Collections.unmodifiableList(ALL_SLOTS);
    }

    /**
     * Checks whether a slot label exists in the schedule.
     *
     * @param slot slot label to check
     * @return true if the slot is valid
     */
    public static boolean isValid(String slot) {
        return slot != null && ALL_SLOTS.contains(slot);
    }

    /**
     * Returns the chronological position of a slot, used for ordering.
     *
     * @param slot slot label
     * @return index in the schedule, or -1 if the slot is invalid
     */
    public static int orderIndex(String slot) {
        return ALL_SLOTS.indexOf(slot);
    }
}
