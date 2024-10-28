package dataStructuresFinalProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EmergencyAppointmentGUI {
    private EmergencyPriorityQueue priorityQueue;
    private PatientArrivalList arrivalList;
    
    // GUI components
    private JFrame frame;
    private JTextField nameField;
    private JTextField severityField;
    private JTextArea displayArea;
    
    public EmergencyAppointmentGUI() {
        priorityQueue = new EmergencyPriorityQueue();
        arrivalList = new PatientArrivalList();
        
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
        
        JButton sortArrivalListButton = new JButton("Sort by Arrival Time");
        actionPanel.add(sortArrivalListButton);

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

        sortArrivalListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArrivalListSorted();
            }
        });

        frame.setVisible(true);
    }

    // Method to add a patient to the system
    private void addPatient() {
        String name = nameField.getText();
        int severity;
        
        try {
            severity = Integer.parseInt(severityField.getText());
            if (severity < 1 || severity > 5) {
                throw new NumberFormatException("Severity must be between 1 and 5");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Please enter a valid severity (1-5).", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // For simplicity, using current system time as arrival time
        int arrivalTime = (int) (System.currentTimeMillis() / 1000);

        // Create new patient and add to both PriorityQueue and LinkedList
        Patient newPatient = new Patient(name, severity, arrivalTime);
        priorityQueue.addPatient(newPatient);
        arrivalList.addPatient(newPatient);
        
        nameField.setText("");
        severityField.setText("");
        
        displayArea.append("Added: " + newPatient + "\n");
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

    // Method to display the Arrival List sorted by arrival time
    private void displayArrivalListSorted() {
        List<Patient> sortedList = arrivalList.getSortedByArrivalTime();
        displayArea.setText("Patient Arrival List (Sorted by Arrival Time):\n");
        for (Patient patient : sortedList) {
            displayArea.append(patient + "\n");
        }
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
