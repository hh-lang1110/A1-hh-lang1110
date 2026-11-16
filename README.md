# PROG2004 Assessment 1 — Health Service Appointment System

## 1. Project naming

The project folder, submission zip and WakaTime file must be named
`A1-<MySCU username>` (e.g. `A1-lfolkman.zip`). This scaffold currently uses
the placeholder folder `A1-hanhao`. Confirm your actual MySCU account name and
rename the folder and final zip before submission.

## 2. Source layout

```
A1-hanhao/
  src/
    AssignmentOne.java        // main: text menu; scripted demonstration is a menu option
    HealthProfessional.java   // abstract base class (Part 1)
    GeneralPractitioner.java  // subtype: GP (Part 2)
    Specialist.java            // subtype: specialist (Part 2)
    Nurse.java                 // subtype: nurse (Part 2)
    Dietitian.java            // subtype: dietitian (Part 2)
    Patient.java              // patient name and mobile contact
    Appointment.java          // one appointment, time-comparable (Part 4)
    AppointmentManager.java   // collection management: add, filter, sort, cancel (Part 5)
    TimeSlot.java             // fixed daily schedule, 08:00–16:00 in 30-minute slots
```

All classes use the default package, matching an introductory course layout.

## 3. Class responsibilities

| Class | Responsibility | Brief part |
|---|---|---|
| `HealthProfessional` | Shared ID, name, working days; controlled access; `equals`/`hashCode`; abstract specialty and role descriptions | Part 1 |
| `GeneralPractitioner` | GP subtype with daily patient capacity and medication advice | Part 2 |
| `Specialist` | Specialist subtype with a sub-specialty focus | Part 2 |
| `Nurse` | Nurse subtype with hospital unit and blood-sample task | Part 2 |
| `Dietitian` | Dietitian subtype with professional qualification | Part 2 |
| `Patient` | Patient name and validated mobile number | Part 4 |
| `Appointment` | Links a patient, a professional and a slot; validates required details; orders by slot | Part 4 |
| `AppointmentManager` | Stores appointments; rejects double bookings; filters by ID or phone; sorts; cancels by ID+slot or phone | Part 5 |
| `TimeSlot` | Holds the fixed, ordered slot list; validates slots; exposes chronological index | Part 5 |
| `AssignmentOne` | Builds sample data, drives the text menu, and runs the end-to-end demonstration on demand from a menu option | Parts 3, 6 |

## 4. How the six parts are covered

- **Part 1 — Health professional model:** `HealthProfessional` stores the
  common details, validates them in the constructor, exposes read-only
  access, and provides a readable `toString()`. A convenience constructor is
  provided for cases where the caller does not supply working days.
- **Part 2 — Specialisation:** four subtypes extend the base class, reuse the
  shared code through `super(...)`, and add their own fields and behaviour.
  The base class is `abstract`, so a plain generic professional cannot be
  instantiated directly. Subtypes also offer a convenience constructor where
  a sensible default can be applied.
- **Part 3 — Working with professionals:** the demonstration creates a mixed
  list held through the common `HealthProfessional` type and prints every
  detail polymorphically; it runs from the first menu option.
- **Part 4 — Appointment model:** `Appointment` links any professional type to
  a patient and slot, refuses incomplete or invalid appointments, and can be
  ordered against another appointment.
- **Part 5 — Managing appointments:** `AppointmentManager` adds appointments,
  rejects double bookings for the same professional and slot, filters by
  professional ID or patient phone, returns time-sorted views, and cancels by
  ID+slot or phone. Every failure returns a warning message.
- **Part 6 — Working with appointments:** the menu demonstration runs the
  full flow, including normal cases and error cases (double booking, cancelling
  a non-existent appointment), on a temporary manager that does not affect
  bookings added interactively.

## 5. Four pillars of OOP — mapping for the video

| Pillar | Where it appears in this design |
|---|---|
| **Encapsulation** | Fields are `private final`; data is read through getters; the working-days list is defensively copied; all validation happens in constructors so objects cannot enter an invalid state. |
| **Inheritance** | `GeneralPractitioner`, `Specialist`, `Nurse` and `Dietitian` extend `HealthProfessional` and reuse the shared fields, validation and `toString()` via `super`. |
| **Polymorphism** | A `List<HealthProfessional>` holds mixed subtypes; the same call to `toString()` or `getSpecialtyType()` lets each subtype respond in its own way. |
| **Abstraction** | The abstract base class declares the common contract (`getSpecialtyType`, `describeRole`) without committing to a specialty; callers depend on the common type rather than concrete subtypes. |

## 6. Compile and run

From the project root:

```
javac -d build src/*.java
java -cp build AssignmentOne
```

In VS Code, open the project folder and use the Run/Debug button on
`AssignmentOne`. The program starts directly at the text menu. Choose option 1
to run the scripted demonstration (Part 3 and Part 6); the other options add
an appointment, list sorted appointments, filter by professional ID or patient
phone, cancel by ID+slot or phone, and list professionals.

## 7. Submission checklist

- [ ] Rename folder and zip to `A1-<username>`; WakaTime file named
      `A1-username-WakaTime.[png/jpg/pdf]`
- [ ] Push the project to a GitHub repository with regular, descriptive commits
- [ ] Attach the WakaTime time-tracking report
- [ ] Record the ≤5-minute four-pillar video, upload to SCU OneDrive, add the shareable link
- [ ] Add the GenAI declaration in the submission comments
- [ ] Re-read every class and rewrite comments in your own words; be ready to explain each line

## 8. Academic integrity note

This project is a study reference scaffold. The brief classifies the
assessment as GenAI Level 2: AI may assist with ideas, learning and debugging,
but the submitted code and comments must be your own work. Before submitting,
make sure you can explain every class, field and method in this design.
