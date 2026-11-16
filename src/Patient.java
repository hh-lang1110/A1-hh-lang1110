/**
 * A patient who attends the health service. The patient's name and mobile
 * contact number are recorded on each appointment.
 */
public class Patient {

    /** Patient's full name. */
    private final String name;

    /** Contact mobile number, digits only. */
    private final String mobile;

    /**
     * Constructs a patient.
     *
     * @param name   patient name
     * @param mobile contact mobile number
     */
    public Patient(String name, String mobile) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Patient name must not be blank.");
        }
        if (mobile == null || !mobile.matches("\\d{10,15}")) {
            throw new IllegalArgumentException("Mobile number must be 10 to 15 digits.");
        }
        this.name = name.trim();
        this.mobile = mobile.trim();
    }

    /**
     * Returns the patient's name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the patient's mobile number.
     *
     * @return mobile number
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * Returns a readable patient summary.
     *
     * @return human-readable string representation
     */
    @Override
    public String toString() {
        return name + " (mobile: " + mobile + ")";
    }

    /**
     * Two patients are equal when their name and mobile number match.
     *
     * @param o object to compare against
     * @return true if both details match
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Patient)) {
            return false;
        }
        Patient other = (Patient) o;
        return name.equals(other.name) && mobile.equals(other.mobile);
    }

    /**
     * Returns the hash code based on name and mobile.
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return name.hashCode() + mobile.hashCode();
    }
}
