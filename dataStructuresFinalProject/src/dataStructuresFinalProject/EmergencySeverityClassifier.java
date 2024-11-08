package dataStructuresFinalProject;

import java.util.HashMap;
import java.util.Map;

public class EmergencySeverityClassifier {
    private Map<Integer, String> severityMap;

    public EmergencySeverityClassifier() {
        severityMap = new HashMap<>();
        initializeSeverityMap();
    }

    private void initializeSeverityMap() {
        severityMap.put(5, "Immediate life-saving intervention required.");
        severityMap.put(4, "High-risk situation: patient is disoriented, experiencing severe pain, or has vitals in the danger zone.");
        severityMap.put(3, "Multiple resources required to stabilize the patient, but vitals are not in the danger zone.");
        severityMap.put(2, "One resource required to stabilize the patient.");
        severityMap.put(1, "Patient does not require any resources to be stabilized.");
    }

    public String getSeverityDescription(int severityLevel) {
        return severityMap.getOrDefault(severityLevel, "Unknown severity level.");
    }

    public static void main(String[] args) {
        EmergencySeverityClassifier classifier = new EmergencySeverityClassifier();

        // Example usage
        int severityLevel = 4;
        System.out.println("Severity Level " + severityLevel + ": " + classifier.getSeverityDescription(severityLevel));
    }
}
