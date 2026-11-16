import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Stores and manages all appointments for the current day. Supports adding
 * appointments, viewing and filtering them, sorting by time, and cancelling
 * them. Every operation returns a feedback message; failures produce a
 * warning message rather than crashing the program.
 */
public class AppointmentManager {

    /** All appointments currently held for the day. */
    private final List<Appointment> appointments = new ArrayList<>();

    /**
     * Adds an appointment. A booking is rejected if the chosen professional
     * already holds an appointment in the same slot.
     *
     * @param appointment appointment to add
     * @return feedback message describing success or rejection
     */
    public String addAppointment(Appointment appointment) {
        if (appointment == null) {
            return "Warning: no appointment was provided.";
        }
        String professionalId = appointment.getProfessional().getId();
        String slot = appointment.getSlot();

        for (Appointment existing : appointments) {
            if (existing.getProfessional().getId().equals(professionalId)
                    && existing.getSlot().equals(slot)) {
                return "Warning: " + appointment.getProfessional().getName()
                        + " is already booked at " + slot + ". Booking rejected.";
            }
        }
        appointments.add(appointment);
        return "Booking confirmed: " + appointment.getPatient().getName()
                + " with " + appointment.getProfessional().getName() + " at " + slot + ".";
    }

    /**
     * Returns a copy of all appointments.
     *
     * @return copy of the appointment list
     */
    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointments);
    }

    /**
     * Filters appointments by the professional's ID.
     *
     * @param professionalId digits-only ID to match
     * @return matching appointments
     */
    public List<Appointment> filterByProfessionalId(String professionalId) {
        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /**
     * Filters appointments by the patient's mobile number.
     *
     * @param mobile patient mobile number to match
     * @return matching appointments
     */
    public List<Appointment> filterByPatientPhone(String mobile) {
        List<Appointment> matches = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(mobile)) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /**
     * Returns a time-ordered copy of the given appointments.
     *
     * @param source appointments to sort
     * @return sorted copy, earliest slot first
     */
    public List<Appointment> getSortedAppointments(List<Appointment> source) {
        List<Appointment> sorted = new ArrayList<>(source);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Cancels the appointment matching a professional ID and a slot.
     *
     * @param professionalId professional ID to match
     * @param slot           slot to match
     * @return feedback message
     */
    public String cancelByProfessionalAndSlot(String professionalId, String slot) {
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)
                    && appointment.getSlot().equals(slot)) {
                appointments.remove(appointment);
                return "Appointment cancelled: " + appointment.getPatient().getName()
                        + " with " + appointment.getProfessional().getName()
                        + " at " + slot + ".";
            }
        }
        return "Warning: no appointment found for professional " + professionalId
                + " at " + slot + ". Nothing was cancelled.";
    }

    /**
     * Cancels every appointment booked under a patient's mobile number.
     *
     * @param mobile patient mobile number to match
     * @return feedback message
     */
    public String cancelByPatientPhone(String mobile) {
        List<Appointment> toRemove = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobile().equals(mobile)) {
                toRemove.add(appointment);
            }
        }
        if (toRemove.isEmpty()) {
            return "Warning: no appointment found for mobile " + mobile
                    + ". Nothing was cancelled.";
        }
        appointments.removeAll(toRemove);
        return "Cancelled " + toRemove.size() + " appointment(s) for mobile " + mobile + ".";
    }
}
