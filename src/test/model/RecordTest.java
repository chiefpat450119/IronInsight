package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RecordTest {
    Record r1;
    Record r2;

    @BeforeEach
    public void runBefore() {
        r1 = new Record(LocalDate.parse("2024-02-18"), "r1");
        r2 = new Record(LocalDate.parse("2025-01-01"), "r2");
    }

    @Test
    public void testConstructor() {
        assertEquals(LocalDate.parse("2024-02-18"), r1.getDate());
        assertEquals(LocalDate.parse("2025-01-01"), r2.getDate());
        assertEquals("r1", r1.getName());
        assertEquals("r2", r2.getName());
        assertEquals(0, r1.getExercises().size());
        assertEquals(0, r2.getExercises().size());
    }

    @Test
    public void testAddExercise() {
        Exercise e = new Exercise("a", 1, 2, 3);
        r1.addExercise(e);
        assertEquals(e, r1.getExercises().get(0));
    }

    @Test
    public void testAddExerciseTwice() {
        Exercise e1 = new Exercise("a", 1, 2, 3);
        Exercise e2 = new Exercise("b", 2, 3, 4);
        r2.addExercise(e1);
        assertEquals(e1, r2.getExercises().get(0));
        r2.addExercise(e2);
        assertEquals(e1, r2.getExercises().get(0));
        assertEquals(e2, r2.getExercises().get(1));
    }

    @Test
    public void testSummary() {
        assertEquals("r1 on 2024-02-18 with 0 exercises:", r1.summary());
        Exercise e1 = new Exercise("a", 1, 2, 3);
        Exercise e2 = new Exercise("b", 2, 3, 4);
        r2.addExercise(e1);
        r2.addExercise(e2);
        String newLine = System.getProperty("line.separator");
        assertEquals("r2 on 2025-01-01 with 2 exercises:" +
                newLine +
                "2 reps of 1 lbs on a at RPE 3" +
                newLine +
                "3 reps of 2 lbs on b at RPE 4", r2.summary());
    }
}
