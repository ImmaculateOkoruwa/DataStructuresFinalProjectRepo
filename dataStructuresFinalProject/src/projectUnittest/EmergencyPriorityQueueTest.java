package projectUnittest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dataStructuresFinalProject.EmergencyPriorityQueue;
import dataStructuresFinalProject.Patient;

public class EmergencyPriorityQueueTest {

    @Test
    public void testAddPatient() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();

        // Add patients to the queue
        Patient patient1 = new Patient("John Doe", 3, 1);
        Patient patient2 = new Patient("Jane Smith", 5, 2);

        queue.addPatient(patient1);
        queue.addPatient(patient2);

        // Verify that the queue is not empty after adding patients
        assertFalse(queue.isEmpty());

        // Verify that the correct number of patients were added
        assertEquals(2, queue.size());
    }

    @Test
    public void testTreatPatient() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();

        // Add patients to the queue
        Patient patient1 = new Patient("John Doe", 3, 1);
        Patient patient2 = new Patient("Jane Smith", 5, 2);
        Patient patient3 = new Patient("Alice Brown", 3, 3);

        queue.addPatient(patient1);
        queue.addPatient(patient2);
        queue.addPatient(patient3);

        // Verify that patients are treated in the correct order
        assertEquals("Name: Jane Smith, Severity: 5, Arrival Time: 2", queue.treatPatient().toString());
        assertEquals("Name: John Doe, Severity: 3, Arrival Time: 1", queue.treatPatient().toString());
        assertEquals("Name: Alice Brown, Severity: 3, Arrival Time: 3", queue.treatPatient().toString());

        // Verify that the queue is empty after treating all patients
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();

        // Verify that a new queue is empty
        assertTrue(queue.isEmpty());

        // Add a patient and verify the queue is no longer empty
        queue.addPatient(new Patient("John Doe", 3, 1));
        assertFalse(queue.isEmpty());

        // Treat the patient and verify the queue is empty again
        queue.treatPatient();
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testSizeMethod() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();

        // Verify initial size of the queue
        assertEquals(0, queue.size());

        // Add patients and verify size updates
        queue.addPatient(new Patient("John Doe", 3, 1));
        queue.addPatient(new Patient("Jane Smith", 5, 2));
        assertEquals(2, queue.size());

        // Treat a patient and verify size decreases
        queue.treatPatient();
        assertEquals(1, queue.size());

        // Treat the last patient and verify size is zero
        queue.treatPatient();
        assertEquals(0, queue.size());
    }

    @Test
    public void testEmptyQueueBehavior() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();

        // Verify behavior of treating a patient from an empty queue
        assertNull(queue.treatPatient());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testQueueOrderWithMultiplePatients() {
        EmergencyPriorityQueue queue = new EmergencyPriorityQueue();

        // Add patients with varying severities and arrival times
        Patient patient1 = new Patient("Emily White", 2, 5);
        Patient patient2 = new Patient("David Green", 4, 3);
        Patient patient3 = new Patient("Sophia Blue", 4, 2);

        queue.addPatient(patient1);
        queue.addPatient(patient2);
        queue.addPatient(patient3);

        // Verify that patients are treated in the correct order
        assertEquals("Name: Sophia Blue, Severity: 4, Arrival Time: 2", queue.treatPatient().toString());
        assertEquals("Name: David Green, Severity: 4, Arrival Time: 3", queue.treatPatient().toString());
        assertEquals("Name: Emily White, Severity: 2, Arrival Time: 5", queue.treatPatient().toString());

        // Verify that the queue is empty after all patients are treated
        assertTrue(queue.isEmpty());
    }
}
