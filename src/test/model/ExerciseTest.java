package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExerciseTest {
    Exercise e1;
    Exercise e2;
    Exercise e3;
    @BeforeEach
    public void runBefore() {
        e1 = new Exercise("a", 10, 8, 5);
        e2 = new Exercise("b", 20, 5, 10);
        e3 = new Exercise("c", 100, 2, 3);
    }

    @Test
    public void testConstructor() {
        assertEquals("a", e1.getName());
        assertEquals(10, e1.getWeight());
        assertEquals(8, e1.getReps());
        assertEquals(5, e1.getRpe());

        assertEquals("b", e2.getName());
        assertEquals(20, e2.getWeight());
        assertEquals(5, e2.getReps());
        assertEquals(10, e2.getRpe());
    }

    @Test
    public void testSummary() {
        String expected = 5 + " reps of " + 20 + " lbs on " + "b" + " at RPE " + 10;
        assertEquals(expected, e2.summary());
        expected = 2 + " reps of " + 100 + " lbs on " + "c" + " at RPE " + 3;
        assertEquals(expected, e3.summary());
    }
}