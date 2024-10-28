package dataStructuresFinalProject;

import java.util.LinkedList;
import java.util.List;

public class PatientArrivalList {
    private LinkedList<Patient> arrivalList;

    public PatientArrivalList() {
        arrivalList = new LinkedList<>();
    }

    // Add a patient at the end of the list
    public void addPatient(Patient patient) {
        arrivalList.add(patient);
    }

    // Remove the patient who arrived first
    public Patient treatFirstPatient() {
        return arrivalList.pollFirst();
    }

    // Display the arrival list
    public void displayArrivalList() {
        for (Patient p : arrivalList) {
            System.out.println(p);
        }
    }

    // Get the size of the list
    public int size() {
        return arrivalList.size();
    }
    
    // Method to get the arrival list as a formatted string
    public String getArrivalListString() {
        StringBuilder sb = new StringBuilder();
        for (Patient patient : arrivalList) {
            sb.append(patient.toString()).append("\n");
        }
        return sb.toString();
    }
    
    // Method to sort the list by arrival time using Selection Sort
    public List<Patient> getSortedByArrivalTime() {
        List<Patient> sortedList = new LinkedList<>(arrivalList);
        
        for (int i = 0; i < sortedList.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < sortedList.size(); j++) {
                if (sortedList.get(j).getArrivalTime() < sortedList.get(minIndex).getArrivalTime()) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            Patient temp = sortedList.get(minIndex);
            sortedList.set(minIndex, sortedList.get(i));
            sortedList.set(i, temp);
        }
        
        return sortedList;
    }
}
