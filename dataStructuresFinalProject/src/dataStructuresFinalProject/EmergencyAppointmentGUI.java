package dataStructuresFinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI for the Emergency Appointment System.
 * Manages patient data, including name validation and queue operations.
 */
public class EmergencyAppointmentGUI {
    private EmergencyPriorityQueue priorityQueue; // Priority Queue to manage patients by severity
    private PatientArrivalList arrivalList;      // List to track patient arrivals
    private EmergencySeverityClassifier severityClassifier; // Severity description provider

    // GUI components
    private JFrame frame;              // Main application window
    private JTextField nameField;      // Input field for patient name
    private JTextField severityField;  // Input field for patient severity
    private JTextArea displayArea;     // Area to display system messages and patient lists

    public EmergencyAppointmentGUI() {
        // Initialize system components
        priorityQueue = new EmergencyPriorityQueue();
        arrivalList = new PatientArrivalList();
        severityClassifier = new EmergencySeverityClassifier();

        // Setup the main frame
        frame = new JFrame("Emergency Appointment System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Create the "Add Patient" form in the North section of the window
        JPanel addPatientPanel = new JPanel(new GridLayout(3, 2));
        addPatientPanel.add(new JLabel("Patient Name:"));
        nameField = new JTextField(); // Input for patient's name
        addPatientPanel.add(nameField);

        addPatientPanel.add(new JLabel("Severity (1-5):"));
        severityField = new JTextField(); // Input for severity level
        addPatientPanel.add(severityField);

        JButton addPatientButton = new JButton("Add Patient"); // Button to add a new patient
        addPatientPanel.add(addPatientButton);

        JButton treatPatientButton = new JButton("Treat Next Patient"); // Button to treat the next patient
        addPatientPanel.add(treatPatientButton);

        frame.add(addPatientPanel, BorderLayout.NORTH); // Add the form to the top of the frame

        // Create the display area in the Center section of the window
        displayArea = new JTextArea();
        displayArea.setEditable(false); // Make display area read-only
        JScrollPane scrollPane = new JScrollPane(displayArea); // Add scroll functionality
        frame.add(scrollPane, BorderLayout.CENTER);

        // Create action buttons in the South section of the window
        JPanel actionPanel = new JPanel();
        JButton displayQueueButton = new JButton("Display Patient Queue"); // Button to show priority queue
        actionPanel.add(displayQueueButton);

        JButton displayArrivalListButton = new JButton("Display Arrival List"); // Button to show arrival list
        actionPanel.add(displayArrivalListButton);

        frame.add(actionPanel, BorderLayout.SOUTH); // Add buttons to the bottom of the frame

        // Add action listeners for button functionality
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient(); // Handle adding a patient
            }
        });

        treatPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treatPatient(); // Handle treating the next patient
            }
        });

        displayQueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayQueue(); // Display the priority queue
            }
        });

        displayArrivalListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArrivalList(); // Display the arrival list
            }
        });

        frame.setVisible(true); // Make the frame visible
    }

    /**
     * Adds a patient to the system after validating input.
     */
    private void addPatient() {
        String name = nameField.getText().trim(); // Get and trim the name input
        int severity;

        // Validate the name
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Name cannot be empty. Please enter a valid name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!name.matches("[a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(frame, "Name can only contain alphabetic characters and spaces.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Format the name: capitalize each word
        name = formatName(name);

        // Validate the severity
        try {
            severity = Integer.parseInt(severityField.getText());
            if (severity < 1 || severity > 5) {
                throw new NumberFormatException("Severity must be between 1 and 5.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid severity (1-5).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get severity description from the classifier
        String severityDescription = severityClassifier.getSeverityDescription(severity);

        // Use the current system time as the arrival time
        int arrivalTime = (int) (System.currentTimeMillis() / 1000);

        // Create a new patient and add to both PriorityQueue and LinkedList
        Patient newPatient = new Patient(name, severity, arrivalTime);
        priorityQueue.addPatient(newPatient);
        arrivalList.addPatient(newPatient);

        // Clear the input fields for new entries
        nameField.setText("");
        severityField.setText("");

        // Display the added patient's info with severity description
        displayArea.append("Added: " + newPatient + "\nSeverity Level " + severity + ": " + severityDescription + "\n\n");
    }

    /**
     * Treats the next patient from the priority queue.
     */
    private void treatPatient() {
        if (priorityQueue.isEmpty()) {
            displayArea.append("No patients to treat.\n");
        } else {
            Patient treated = priorityQueue.treatPatient(); // Remove the highest priority patient
            displayArea.append("Treated: " + treated + "\n");
        }
    }

    /**
     * Displays the current priority queue.
     */
    private void displayQueue() {
        displayArea.setText("Current Patient Queue (by severity):\n" + priorityQueue.getQueueString());
    }

    /**
     * Displays the patient arrival list in order of arrival.
     */
    private void displayArrivalList() {
        displayArea.setText("Patient Arrival List:\n" + arrivalList.getArrivalListString());
    }

    /**
     * Formats the name to capitalize the first letter of each word.
     * @param name The raw name input.
     * @return Formatted name.
     */
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmergencyAppointmentGUI(); // Start the GUI
            }
        });
    }
}
