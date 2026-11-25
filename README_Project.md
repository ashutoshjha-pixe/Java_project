# Pulse-Bridge — Online Health Care Management System

A Java Swing desktop application using JDBC and MySQL for role-based Admin, Doctor, and Patient workflows.

## Included modules
- Role-based login for ADMIN, DOCTOR, and PATIENT.
- Admin: view doctors, patients, appointments, and medical records; add doctor/patient profiles.
- Doctor: view assigned appointments, approve/reject/complete bookings, and add medical-record entries.
- Patient: browse doctors, book appointments, track their appointments, and view their medical records.
- JDBC DAO layer, PreparedStatement queries, Swing UI helpers, custom exception type, generic CRUD interface, collections, background thread, and synchronized counter.

## Technology
Java 8 language level, Java Swing, MySQL, JDBC, Maven, MySQL Connector/J 9.4.0.

## Project layout
```text
Pulse-Bridge-Source/
├── database/healthcare.sql
├── src/main/java/com/healthcare/
│   ├── Main.java
│   ├── model/       User, Patient, Doctor, Appointment, MedicalRecord
│   ├── dao/         CRUDOperations and DAO classes
│   ├── gui/         LoginFrame and role dashboards
│   ├── service/     NotificationService, AppointmentCounter
│   ├── exception/   ValidationException
│   └── util/        DBConnection
├── docs/            diagrams and Review 2 files
├── mockups/         conceptual UI mockups
├── screenshots/     add genuine running-app screenshots here
├── pom.xml
└── README.md
```

## Setup and run
1. Install JDK 8+ (or a newer JDK that supports Java 8 source compatibility), MySQL Server, and Maven.
2. Execute `database/healthcare.sql` in MySQL Workbench or the MySQL command line. **This is a reset script**: it drops and recreates the project tables.
3. Set database environment variables if required:
   - `HEALTHCARE_DB_URL` (default `jdbc:mysql://localhost:3306/healthcare?useSSL=false&serverTimezone=UTC`)
   - `HEALTHCARE_DB_USER` (default `root`)
   - `HEALTHCARE_DB_PASSWORD` (default `root`)
4. From this directory, run `mvn clean compile`.
5. Run `com.healthcare.Main` from your IDE, or configure a Maven exec plugin to launch it.

## Demo accounts from the supplied SQL
| Role | Username | Password |
|---|---|---|
| Admin | admin | admin123 |
| Doctor | doctor1 | doctor123 |
| Patient | patient1 | patient123 |

These credentials are for demonstration only. The supplied project schema stores demo passwords in plain text; do not use this authentication design in a real healthcare system. Implement password hashing and proper access controls before production use.

## Source reconstruction note
The supplied 27-page PDF contains full listings for many classes, but some GUI source lines are visibly truncated in the PDF. This project reconstructs those missing GUI lines and completes the empty DAO update methods so that the project has a coherent source tree. Therefore, this is a **reconstructed, build-oriented version based on the supplied code document**, not a byte-for-byte copy of an unavailable original ZIP. Review the code and test it against your local MySQL environment before submission.

## Known limitations
- Admin-created doctor and patient profile rows do not create corresponding login users; the seeded demo profiles are already mapped to their demo accounts.
- `NotificationService` is a background console heartbeat, not an actual email/SMS/push reminder service.
- `AppointmentDAO.update()` intentionally directs callers to the explicit status update method.
- The UI is a basic Swing academic prototype; validate role access, data privacy, and password handling before any real-world use.

## GitHub
https://github.com/sumanrock007/Pulse-Bridge
