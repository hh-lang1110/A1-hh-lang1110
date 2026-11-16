import java.util.List;

/**
 * A general practitioner (GP): a doctor who handles common, everyday health
 * problems and refers patients on to specialists when needed.
 */
public class GeneralPractitioner extends HealthProfessional {

    /** Maximum number of patients the GP can see on one working day. */
    private final int dailyPatientCapacity;

    /**
     * Convenience constructor using a default daily capacity of 20 patients.
     *
     * @param id          digits-only identifier
     * @param name        full name
     * @param workingDays weekdays the GP works
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays) {
        this(id, name, workingDays, 20);
    }

    /**
     * Constructs a general practitioner.
     *
     * @param id                  digits-only identifier
     * @param name                full name
     * @param workingDays         weekdays the GP works
     * @param dailyPatientCapacity daily patient capacity, must be positive
     */
    public GeneralPractitioner(String id, String name, List<String> workingDays,
                               int dailyPatientCapacity) {
        super(id, name, workingDays);
        if (dailyPatientCapacity <= 0) {
            throw new IllegalArgumentException("Daily capacity must be a positive number.");
        }
        this.dailyPatientCapacity = dailyPatientCapacity;
    }

    @Override
    public String getSpecialtyType() {
        return "General Practitioner";
    }

    @Override
    public String describeRole() {
        return "Diagnoses common illnesses and refers patients to specialists.";
    }

    /**
     * Simulates the GP issuing medication advice for a patient.
     *
     * @param patientName name of the patient
     * @return a prescription note
     */
    public String prescribeMedication(String patientName) {
        return getName() + " (GP) prescribes medication for " + patientName + ".";
    }

    /**
     * Returns the GP's shared details plus the subtype-specific capacity.
     *
     * @return human-readable summary
     */
    @Override
    public String toString() {
        return super.toString()
                + " | Daily patient capacity: " + dailyPatientCapacity;
    }
}
