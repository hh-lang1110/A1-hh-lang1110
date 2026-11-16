import java.util.List;

/**
 * A dietitian who advises patients on nutrition and dietary plans.
 */
public class Dietitian extends HealthProfessional {

    /** Professional qualification held by the dietitian. */
    private final String qualification;

    /**
     * Constructs a dietitian.
     *
     * @param id            digits-only identifier
     * @param name          full name
     * @param workingDays   weekdays the dietitian works
     * @param qualification professional qualification
     */
    public Dietitian(String id, String name, List<String> workingDays, String qualification) {
        super(id, name, workingDays);
        if (qualification == null || qualification.isBlank()) {
            throw new IllegalArgumentException("Qualification must not be blank.");
        }
        this.qualification = qualification.trim();
    }

    @Override
    public String getSpecialtyType() {
        return "Dietitian";
    }

    @Override
    public String describeRole() {
        return "Advises patients on nutrition and personalised dietary plans.";
    }

    /**
     * Returns the dietitian's shared details plus the qualification.
     *
     * @return human-readable summary
     */
    @Override
    public String toString() {
        return super.toString()
                + " | Qualification: " + qualification;
    }
}
