package dataStructuresFinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmergencyAppointmentGUI {
    private EmergencyPriorityQueue priorityQueue;
    private PatientArrivalList arrivalList;
    private EmergencySeverityClassifier severityClassifier;

    // GUI components
    private JFrame frame;
    private JTextField nameField;
    private JTextField severityField;
    private JTextArea displayArea;

    public EmergencyAppointmentGUI() {
        priorityQueue = new EmergencyPriorityQueue();
        arrivalList = new PatientArrivalList();
        severityClassifier = new EmergencySeverityClassifier(); // Initialize the severity classifier

        // Initialize GUI
        frame = new JFrame("Emergency Appointment System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // North panel - Add patient form
        JPanel addPatientPanel = new JPanel(new GridLayout(3, 2));
        addPatientPanel.add(new JLabel("Patient Name:"));
        nameField = new JTextField();
        addPatientPanel.add(nameField);

        addPatientPanel.add(new JLabel("Severity (1-5):"));
        severityField = new JTextField();
        addPatientPanel.add(severityField);

        JButton addPatientButton = new JButton("Add Patient");
        addPatientPanel.add(addPatientButton);

        JButton treatPatientButton = new JButton("Treat Next Patient");
        addPatientPanel.add(treatPatientButton);

        frame.add(addPatientPanel, BorderLayout.NORTH);

        // Center panel - Display area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        // South panel - Buttons for actions
        JPanel actionPanel = new JPanel();
        JButton displayQueueButton = new JButton("Display Patient Queue");
        actionPanel.add(displayQueueButton);

        JButton displayArrivalListButton = new JButton("Display Arrival List");
        actionPanel.add(displayArrivalListButton);

        frame.add(actionPanel, BorderLayout.SOUTH);

        // Action Listeners
        addPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPatient();
            }
        });

        treatPatientButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                treatPatient();
            }
        });

        displayQueueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayQueue();
            }
        });

        displayArrivalListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArrivalList();
            }
        });

        frame.setVisible(true);
    }

    // Method to add a patient to the system
    private void addPatient() {
        String name = nameField.getText().trim(); 
        int severity;

        // Validate name
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Name cannot be empty. Please enter a valid name.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            severity = Integer.parseInt(severityField.getText());
            if (severity < 1 || severity > 5) {
                throw new NumberFormatException("Severity must be between 1 and 5");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid severity (1-5).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Get severity description from the classifier
        String severityDescription = severityClassifier.getSeverityDescription(severity);

        // For simplicity, using current system time as arrival time
        int arrivalTime = (int) (System.currentTimeMillis() / 1000);

        // Create new patient and add to both PriorityQueue and LinkedList
        Patient newPatient = new Patient(name, severity, arrivalTime);
        priorityQueue.addPatient(newPatient);
        arrivalList.addPatient(newPatient);

        nameField.setText("");
        severityField.setText("");

        // Display the added patient's info with severity description
        displayArea.append("Added: " + newPatient + "\nSeverity Level " + severity + ": " + severityDescription + "\n\n");
    }

    // Method to treat the next patient
    private void treatPatient() {
        if (priorityQueue.isEmpty()) {
            displayArea.append("No patients to treat.\n");
        } else {
            Patient treated = priorityQueue.treatPatient();
            displayArea.append("Treated: " + treated + "\n");
        }
    }

    // Method to display the Priority Queue
    private void displayQueue() {
        displayArea.setText("Current Patient Queue (by severity):\n" + priorityQueue.getQueueString());
    }

    // Method to display the Arrival List (unsorted)
    private void displayArrivalList() {
        displayArea.setText("Patient Arrival List:\n" + arrivalList.getArrivalListString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EmergencyAppointmentGUI();
            }
        });
    }
}
