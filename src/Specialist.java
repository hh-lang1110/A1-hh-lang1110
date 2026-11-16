import java.util.List;

/**
 * A specialist: a doctor who focuses on a particular branch of medicine,
 * such as cardiology, paediatrics or radiology.
 */
public class Specialist extends HealthProfessional {

    /** The focused area of medicine, e.g. "Cardiologist". */
    private final String subSpecialty;

    /**
     * Constructs a specialist.
     *
     * @param id          digits-only identifier
     * @param name        full name
     * @param workingDays weekdays the specialist works
     * @param subSpecialty focused area of medicine
     */
    public Specialist(String id, String name, List<String> workingDays, String subSpecialty) {
        super(id, name, workingDays);
        if (subSpecialty == null || subSpecialty.isBlank()) {
            throw new IllegalArgumentException("Specialty focus must not be blank.");
        }
        this.subSpecialty = subSpecialty.trim();
    }

    @Override
    public String getSpecialtyType() {
        return "Specialist (" + subSpecialty + ")";
    }

    @Override
    public String describeRole() {
        return "Provides expert diagnosis and treatment within a focused medical area.";
    }

    /**
     * Returns the specialist's shared details plus the focused area.
     *
     * @return human-readable summary
     */
    @Override
    public String toString() {
        return super.toString()
                + " | Sub-specialty: " + subSpecialty;
    }
}
