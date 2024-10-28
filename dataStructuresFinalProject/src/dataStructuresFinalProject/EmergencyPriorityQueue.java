package dataStructuresFinalProject;

import java.util.PriorityQueue;

public class EmergencyPriorityQueue {
    private PriorityQueue<Patient> queue;

    public EmergencyPriorityQueue() {
        queue = new PriorityQueue<>(new PatientComparator());
    }

    // Add a patient to the queue
    public void addPatient(Patient patient) {
        queue.add(patient);
    }

    // Remove and return the patient with the highest priority (most severe or earliest)
    public Patient treatPatient() {
        return queue.poll();
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // Get the size of the queue
    public int size() {
        return queue.size();
    }

    // Display the queue
    public void displayQueue() {
        System.out.println("Current Queue: " + queue);
    }
    
    public String getQueueString() {
        StringBuilder sb = new StringBuilder();
        for (Patient patient : queue) {
            sb.append(patient.toString()).append("\n");
        }
        return sb.toString();
    }
}