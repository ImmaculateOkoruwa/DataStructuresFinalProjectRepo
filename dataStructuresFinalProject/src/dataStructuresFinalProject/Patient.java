package dataStructuresFinalProject;

import java.util.Comparator;

/**
 * Represents a patient in the emergency appointment system.
 * Each patient has a name, severity, and arrival time.
 */
public class Patient {
    private String name;
    private int severity;  // Severity of the patient's condition (1 to 5)
    private int arrivalTime; // Time of arrival (used for tie-breaking)

    /**
     * Constructs a new Patient with the given details.
     * Validates and formats the name.
     *
     * @param name        The patient's name.
     * @param severity    The severity level (1-5).
     * @param arrivalTime The arrival time in seconds.
     */
    public Patient(String name, int severity, int arrivalTime) {
        // Validate and format the name
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name must only contain alphabetic characters and spaces.");
        }
        this.name = formatName(name);

        // Validate severity
        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException("Severity must be between 1 and 5.");
        }
        this.severity = severity;

        this.arrivalTime = arrivalTime;
    }

    // Helper method to format name
    private String formatName(String name) {
        String[] words = name.toLowerCase().split("\\s+");
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                formattedName.append(Character.toUpperCase(word.charAt(0)))
                             .append(word.substring(1))
                             .append(" ");
            }
        }
        return formattedName.toString().trim();
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty or null.");
        }
        if (!name.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("Name must only contain alphabetic characters and spaces.");
        }
        this.name = formatName(name);
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        if (severity < 1 || severity > 5) {
            throw new IllegalArgumentException("Severity must be between 1 and 5.");
        }
        this.severity = severity;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Severity: " + severity + ", Arrival Time: " + arrivalTime;
    }


    /**
     * Comparator for ordering patients by severity (higher first), breaking ties by arrival time (earlier first).
     */
    public static class SeverityComparator implements Comparator<Patient> {
        @Override
        public int compare(Patient p1, Patient p2) {
            if (p1.getSeverity() != p2.getSeverity()) {
                return Integer.compare(p2.getSeverity(), p1.getSeverity()); // Higher severity first
            }
            return Integer.compare(p1.getArrivalTime(), p2.getArrivalTime()); // Earlier arrival first
        }
    }
}
