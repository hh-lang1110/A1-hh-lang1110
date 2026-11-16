import java.util.List;

/**
 * A nurse who supports doctors, carries out clinical tasks such as taking blood
 * samples, and looks after patients on a particular unit.
 */
public class Nurse extends HealthProfessional {

    /** The unit the nurse works on, e.g. "Emergency" or "Ward 3". */
    private final String unit;

    /**
     * Constructs a nurse.
     *
     * @param id          digits-only identifier
     * @param name        full name
     * @param workingDays weekdays the nurse works
     * @param unit        hospital unit the nurse belongs to
     */
    public Nurse(String id, String name, List<String> workingDays, String unit) {
        super(id, name, workingDays);
        if (unit == null || unit.isBlank()) {
            throw new IllegalArgumentException("Nurse unit must not be blank.");
        }
        this.unit = unit.trim();
    }

    @Override
    public String getSpecialtyType() {
        return "Nurse";
    }

    @Override
    public String describeRole() {
        return "Supports clinical care and carries out nursing tasks for patients.";
    }

    /**
     * Simulates the nurse taking a blood sample from a patient.
     *
     * @param patientName name of the patient
     * @return a task note
     */
    public String takeBloodSample(String patientName) {
        return getName() + " (Nurse) takes a blood sample from " + patientName + ".";
    }

    /**
     * Returns the nurse's shared details plus the unit.
     *
     * @return human-readable summary
     */
    @Override
    public String toString() {
        return super.toString()
                + " | Unit: " + unit;
    }
}
