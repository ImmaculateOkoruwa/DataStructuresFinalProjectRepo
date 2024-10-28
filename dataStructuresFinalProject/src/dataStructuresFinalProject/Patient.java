package dataStructuresFinalProject;

import java.util.Comparator;

// Patient class to hold patient data
public class Patient {
    String name;
    int severity; // 1 (low) to 5 (high)
    int arrivalTime; // Sequential number to indicate arrival

    public Patient(String name, int severity, int arrivalTime) {
        this.name = name;
        this.severity = severity;
        this.arrivalTime = arrivalTime;
    }
    
    

    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the severity
	 */
	public int getSeverity() {
		return severity;
	}



	/**
	 * @param severity the severity to set
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}



	/**
	 * @return the arrivalTime
	 */
	public int getArrivalTime() {
		return arrivalTime;
	}



	/**
	 * @param arrivalTime the arrivalTime to set
	 */
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}



	@Override
    public String toString() {
        return name + " (Severity: " + severity + ", Arrival: " + arrivalTime + ")";
    }
}

// Comparator to handle priority based on severity first, then arrival time
class PatientComparator implements Comparator<Patient> {
    @Override
    public int compare(Patient p1, Patient p2) {
        if (p1.severity != p2.severity) {
            return p2.severity - p1.severity; // Higher severity comes first
        }
        return p1.arrivalTime - p2.arrivalTime; // Earlier arrival comes first if severity is the same
    }
}

