/**
 * A single appointment: links a patient to a chosen health professional at a
 * fixed day's time slot. An appointment can be made with any subtype of
 * HealthProfessional.
 */
public class Appointment implements Comparable<Appointment> {

    /** The patient who is booked. */
    private final Patient patient;

    /** The professional the patient wants to see. */
    private final HealthProfessional professional;

    /** Scheduled time slot, e.g. "09:30". */
    private final String slot;

    /**
     * Constructs an appointment. Required details are validated here so an
     * appointment can never be created in an incomplete or invalid state.
     *
     * @param patient      booked patient
     * @param professional chosen professional
     * @param slot         scheduled time slot
     */
    public Appointment(Patient patient, HealthProfessional professional, String slot) {
        if (patient == null) {
            throw new IllegalArgumentException("Appointment requires a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("Appointment requires a professional.");
        }
        if (!TimeSlot.isValid(slot)) {
            throw new IllegalArgumentException("Invalid time slot: " + slot);
        }
        this.patient = patient;
        this.professional = professional;
        this.slot = slot;
    }

    /**
     * Returns the booked patient.
     *
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the chosen professional.
     *
     * @return professional
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the scheduled slot.
     *
     * @return slot label
     */
    public String getSlot() {
        return slot;
    }

    /**
     * Orders appointments from earliest to latest slot. Appointments at the
     * same slot may appear in any order.
     *
     * @param other appointment to compare against
     * @return negative, zero or positive as required by Comparable
     */
    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(TimeSlot.orderIndex(this.slot), TimeSlot.orderIndex(other.slot));
    }

    /**
     * Returns a readable appointment summary, including the professional's
     * details.
     *
     * @return human-readable string representation
     */
    @Override
    public String toString() {
        return "Slot: " + slot
                + " | Patient: " + patient
                + " | Professional: " + professional.getName()
                + " (" + professional.getSpecialtyType() + ")";
    }
}
