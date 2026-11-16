import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Demonstration entry point for the appointment system.
 *
 * <p>On start the program shows a text menu. The scripted section that creates
 * sample professionals and appointments and exercises the manager's normal and
 * error cases is available as the first menu option; the remaining options
 * drive the booking operations interactively.
 */
public class AssignmentOne {

    /** Shared scanner used by the interactive menu. */
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Program entry point.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        List<HealthProfessional> professionals = createProfessionals();
        AppointmentManager manager = new AppointmentManager();

        runMenu(manager, professionals);

        scanner.close();
    }

    /**
     * Part 3: creates a realistic mix of professionals and prints their details
     * through the common HealthProfessional type.
     *
     * @return the created professionals
     */
    private static List<HealthProfessional> createProfessionals() {
        List<HealthProfessional> professionals = new ArrayList<>();

        professionals.add(new GeneralPractitioner("101", "Dr Alice Wong",
                List.of("Monday", "Wednesday", "Friday"), 20));
        professionals.add(new GeneralPractitioner("102", "Dr Brian Lee",
                List.of("Tuesday", "Thursday"), 18));
        professionals.add(new GeneralPractitioner("103", "Dr Cara Xu",
                List.of("Monday", "Tuesday", "Thursday", "Saturday"), 22));
        professionals.add(new Specialist("201", "Dr David Kim",
                List.of("Wednesday", "Friday"), "Cardiologist"));
        professionals.add(new Nurse("301", "Nurse Ella Moore",
                List.of("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"), "Emergency"));
        professionals.add(new Dietitian("401", "Dietitian Fiona Brown",
                List.of("Tuesday", "Thursday"), "APD"));

        return professionals;
    }

    /**
     * Prints every professional held in the common-typed list.
     *
     * @param professionals professionals to display
     */
    private static void demonstrateProfessionals(List<HealthProfessional> professionals) {
        System.out.println("=== Part 3: Health professionals ===");
        for (HealthProfessional professional : professionals) {
            System.out.println(professional);
            System.out.println("   Role: " + professional.describeRole());
        }
        System.out.println();
    }

    /**
     * Part 6: end-to-end scripted demonstration, covering confirmed bookings,
     * filtering, sorting, cancellation and error cases.
     *
     * @param manager appointment manager to exercise
     */
    private static void demonstrateAppointments(AppointmentManager manager,
                                              List<HealthProfessional> professionals) {
        System.out.println("=== Part 6: Appointment system demonstration ===");

        manager.addAppointment(new Appointment(
                new Patient("Grace Tan", "0412345678"), findById(professionals, "101"), "09:00"));
        manager.addAppointment(new Appointment(
                new Patient("Henry Zhang", "0423456789"), findById(professionals, "201"), "10:30"));
        manager.addAppointment(new Appointment(
                new Patient("Ivy Chen", "0434567890"), findById(professionals, "301"), "08:30"));
        manager.addAppointment(new Appointment(
                new Patient("Jack Liu", "0412345678"), findById(professionals, "102"), "11:00"));

        System.out.println("-- All appointments, time order --");
        printAppointments(manager.getSortedAppointments(manager.getAllAppointments()));

        System.out.println("-- Filter by professional ID 101 --");
        printAppointments(manager.filterByProfessionalId("101"));

        System.out.println("-- Filter by patient phone 0412345678 --");
        printAppointments(manager.filterByPatientPhone("0412345678"));

        System.out.println("-- Error case: double booking for 101 at 09:00 --");
        System.out.println(manager.addAppointment(new Appointment(
                new Patient("Kelly Wu", "0445678901"), findById(professionals, "101"), "09:00")));

        System.out.println("-- Cancel appointment: professional 101 at 09:00 --");
        System.out.println(manager.cancelByProfessionalAndSlot("101", "09:00"));

        System.out.println("-- Error case: cancel a non-existent appointment --");
        System.out.println(manager.cancelByProfessionalAndSlot("999", "14:00"));

        System.out.println("-- Remaining appointments after cancellation --");
        printAppointments(manager.getSortedAppointments(manager.getAllAppointments()));
        System.out.println();
    }

    /**
     * Runs the Part 3 and Part 6 scripted demonstration on a temporary manager,
     * so the sample bookings do not mix with appointments added interactively.
     *
     * @param professionals available professionals
     */
    private static void runScriptedDemonstration(List<HealthProfessional> professionals) {
        demonstrateProfessionals(professionals);
        demonstrateAppointments(new AppointmentManager(), professionals);
    }

    /**
     * Runs the interactive text menu until the user chooses to exit.
     *
     * @param manager      appointment manager
     * @param professionals available professionals
     */
    private static void runMenu(AppointmentManager manager,
                                List<HealthProfessional> professionals) {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Choose an option: ");
            switch (choice) {
                case "1" -> runScriptedDemonstration(professionals);
                case "2" -> menuAddAppointment(manager, professionals);
                case "3" -> printAppointments(manager.getSortedAppointments(manager.getAllAppointments()));
                case "4" -> menuFilterByProfessional(manager);
                case "5" -> menuFilterByPhone(manager);
                case "6" -> menuCancelByProfessionalAndSlot(manager);
                case "7" -> menuCancelByPhone(manager);
                case "8" -> printProfessionals(professionals);
                case "9" -> {
                    running = false;
                    System.out.println("Exiting menu. Goodbye.");
                }
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    /** Prints the menu options. */
    private static void printMenu() {
        System.out.println();
        System.out.println("1. Run scripted demonstration");
        System.out.println("2. Add appointment");
        System.out.println("3. List all appointments (sorted)");
        System.out.println("4. Filter by professional ID");
        System.out.println("5. Filter by patient phone");
        System.out.println("6. Cancel by professional ID and slot");
        System.out.println("7. Cancel by patient phone");
        System.out.println("8. List professionals");
        System.out.println("9. Exit");
    }

    /**
     * Prompts for details and adds an appointment from the menu.
     *
     * @param manager      appointment manager
     * @param professionals available professionals
     */
    private static void menuAddAppointment(AppointmentManager manager,
                                           List<HealthProfessional> professionals) {
        String patientName = readLine("Patient name: ");
        String mobile = readLine("Patient mobile: ");
        String professionalId = readLine("Professional ID: ");
        System.out.println("Available slots: " + TimeSlot.getAllSlots());
        String slot = readLine("Slot (e.g. 09:30): ");

        HealthProfessional professional = findById(professionals, professionalId);
        if (professional == null) {
            System.out.println("Warning: no professional found with ID " + professionalId + ".");
            return;
        }
        try {
            Appointment appointment = new Appointment(new Patient(patientName, mobile), professional, slot);
            System.out.println(manager.addAppointment(appointment));
        } catch (IllegalArgumentException e) {
            System.out.println("Warning: " + e.getMessage());
        }
    }

    /** Filters and prints appointments by professional ID. */
    private static void menuFilterByProfessional(AppointmentManager manager) {
        String id = readLine("Professional ID: ");
        printAppointments(manager.filterByProfessionalId(id));
    }

    /** Filters and prints appointments by patient phone. */
    private static void menuFilterByPhone(AppointmentManager manager) {
        String mobile = readLine("Patient mobile: ");
        printAppointments(manager.filterByPatientPhone(mobile));
    }

    /** Cancels an appointment by professional ID and slot. */
    private static void menuCancelByProfessionalAndSlot(AppointmentManager manager) {
        String id = readLine("Professional ID: ");
        String slot = readLine("Slot (e.g. 09:30): ");
        System.out.println(manager.cancelByProfessionalAndSlot(id, slot));
    }

    /** Cancels appointments by patient phone. */
    private static void menuCancelByPhone(AppointmentManager manager) {
        String mobile = readLine("Patient mobile: ");
        System.out.println(manager.cancelByPatientPhone(mobile));
    }

    /**
     * Finds a professional by ID.
     *
     * @param professionals professionals to search
     * @param id            ID to match
     * @return matching professional, or null if none matches
     */
    private static HealthProfessional findById(List<HealthProfessional> professionals, String id) {
        for (HealthProfessional professional : professionals) {
            if (professional.getId().equals(id)) {
                return professional;
            }
        }
        return null;
    }

    /**
     * Prints a list of appointments, or a message when empty.
     *
     * @param appointments appointments to display
     */
    private static void printAppointments(List<Appointment> appointments) {
        if (appointments.isEmpty()) {
            System.out.println("(No appointments to show.)");
            return;
        }
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }

    /**
     * Prints every available professional.
     *
     * @param professionals professionals to display
     */
    private static void printProfessionals(List<HealthProfessional> professionals) {
        for (HealthProfessional professional : professionals) {
            System.out.println(professional);
        }
    }

    /**
     * Reads one trimmed line from the scanner.
     *
     * @param prompt message shown to the user
     * @return trimmed input line
     */
    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
}
