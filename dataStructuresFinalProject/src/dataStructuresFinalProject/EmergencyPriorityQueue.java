package dataStructuresFinalProject;

import java.util.PriorityQueue;

/**
 * Manages the priority queue for patients in the emergency system.
 * Patients are prioritized by severity (higher severity treated first),
 * and ties are broken by arrival time (earlier arrivals treated first).
 */
public class EmergencyPriorityQueue {
    private PriorityQueue<Patient> queue;

    /**
     * Constructor for the EmergencyPriorityQueue.
     * Uses a custom comparator to order patients by severity and arrival time.
     */
    public EmergencyPriorityQueue() {
        queue = new PriorityQueue<>(new Patient.SeverityComparator());
    }

    /**
     * Adds a patient to the queue.
     * 
     * @param patient The patient to add.
     */
    public void addPatient(Patient patient) {
        if (patient == null) {
            throw new IllegalArgumentException("Patient cannot be null.");
        }
        queue.add(patient);
    }

    /**
     * Removes and returns the patient with the highest priority.
     * Priority is determined by severity (higher first) and arrival time (earlier first).
     * 
     * @return The treated patient, or null if the queue is empty.
     */
    public Patient treatPatient() {
        return queue.poll();
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /**
     * Gets the size of the queue.
     * 
     * @return The number of patients in the queue.
     */
    public int size() {
        return queue.size();
    }

    /**
     * Displays the current queue contents to the console.
     * For debugging or command-line usage.
     */
    public void displayQueue() {
        System.out.println("Current Patient Queue:");
        for (Patient patient : queue) {
            System.out.println(patient);
        }
    }

    /**
     * Returns a string representation of the queue for GUI display.
     * 
     * @return A formatted string of the current queue contents.
     */
    public String getQueueString() {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Patient> tempQueue = new PriorityQueue<>(queue);
        while (!tempQueue.isEmpty()) {
            sb.append(tempQueue.poll()).append("\n");
        }
        return sb.toString();
    }
}
