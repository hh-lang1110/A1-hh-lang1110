import java.util.ArrayList;
import java.util.List;

/**
 * Represents a generic health professional at the health service.
 *
 * <p>This abstract class captures the information and behaviour shared by every
 * type of health professional, regardless of their specialty. Subclasses are
 * expected to supply their own specialty description and role description.
 *
 * <p>Note for study: this file is a reference implementation. The Javadoc and
 * inline comments in your own submission must be written by you.
 */
public abstract class HealthProfessional {

    /** Digits-only identifier of the professional. */
    private final String id;

    /** Full name of the professional. */
    private final String name;

    /** Fixed collection of weekdays the professional works, e.g. "Monday". */
    private final List<String> workingDays;

    /**
     * Convenience constructor using a default working day of Monday.
     *
     * @param id   digits-only identifier
     * @param name full name
     */
    protected HealthProfessional(String id, String name) {
        this(id, name, List.of("Monday"));
    }

    /**
     * Constructs a HealthProfessional with the common shared details.
     *
     * @param id          digits-only professional identifier
     * @param name        full name of the professional
     * @param workingDays weekdays the professional works on
     * @throws IllegalArgumentException if any required detail is missing or invalid
     */
    protected HealthProfessional(String id, String name, List<String> workingDays) {
        if (id == null || !id.matches("\\d+")) {
            throw new IllegalArgumentException("Professional ID must contain digits only.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Professional name must not be blank.");
        }
        if (workingDays == null || workingDays.isEmpty()) {
            throw new IllegalArgumentException("At least one working day is required.");
        }
        this.id = id;
        this.name = name.trim();
        this.workingDays = new ArrayList<>(workingDays);
    }

    /**
     * Returns the professional's identifier.
     *
     * @return digits-only ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the professional's name.
     *
     * @return full name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a copy of the working days so the internal collection cannot be
     * modified from outside the object.
     *
     * @return copy of the working days list
     */
    public List<String> getWorkingDays() {
        return new ArrayList<>(workingDays);
    }

    /**
     * Returns the display name of this professional's specialty.
     *
     * @return specialty type label
     */
    public abstract String getSpecialtyType();

    /**
     * Returns a short description of what this role does.
     *
     * @return role description
     */
    public abstract String describeRole();

    /**
     * Returns a readable summary containing the shared details.
     *
     * @return human-readable string representation
     */
    @Override
    public String toString() {
        return "ID: " + id
                + " | Name: " + name
                + " | Specialty: " + getSpecialtyType()
                + " | Working days: " + workingDays;
    }

    /**
     * Two professionals are considered equal when they share the same ID.
     *
     * @param o object to compare against
     * @return true if the IDs match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional other = (HealthProfessional) o;
        return id.equals(other.id);
    }

    /**
     * Returns the hash code based on the ID.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
