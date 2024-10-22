package dataStructuresFinalProject;

import java.util.LinkedList;

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
}
