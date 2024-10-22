package projectUnittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructuresFinalProject.EmergencyPriorityQueue;
import dataStructuresFinalProject.Patient;

public class EmergencyPriorityQueueTest {
    
    @Test
    public void testAddAndTreatPatient() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();
        Patient patient1 = new Patient("John Doe", 3, 1);
        Patient patient2 = new Patient("Jane Smith", 5, 2);
        Patient patient3 = new Patient("Alice Brown", 3, 3);
        
        queue.addPatient(patient1);
        queue.addPatient(patient2);
        queue.addPatient(patient3);
        
        // Test that the patient with the highest severity is treated first
        assertEquals("Jane Smith (Severity: 5, Arrival: 2)", queue.treatPatient().toString());

        // Test that in case of same severity, the earliest arrival is treated first
        assertEquals("John Doe (Severity: 3, Arrival: 1)", queue.treatPatient().toString());

        assertEquals("Alice Brown (Severity: 3, Arrival: 3)", queue.treatPatient().toString());
    }
    
    @Test
    public void testEmptyQueue() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();
        assertTrue(queue.isEmpty());
        assertNull(queue.treatPatient());
    }
}