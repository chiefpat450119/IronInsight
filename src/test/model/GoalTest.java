package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class GoalTest {
    Goal g1;
    Goal g2;

    @BeforeEach
    public void runBefore() {
        g1 = new Goal(LocalDate.now(), "goal", "a goal");
        g2 = new Goal(LocalDate.parse("2025-01-02"), "goal 2", "different goal");
    }

    @Test
    public void testConstructor() {
        assertEquals(LocalDate.now(), g1.getDate());
        assertEquals(LocalDate.parse("2025-01-02"), g2.getDate());
        assertEquals("goal", g1.getName());
        assertEquals("goal 2", g2.getName());
        assertEquals("a goal", g1.getDescription());
        assertEquals("different goal", g2.getDescription());
        assertFalse(g1.isCompleted());
        assertFalse(g2.isCompleted());
    }

    @Test
    public void testAddTarget() {
        g1.addTarget(LocalDate.parse("2025-01-02"), 225, "Bench press");
        assertEquals(LocalDate.parse("2025-01-02"), g1.getTargetDate());
        assertEquals("Bench press", g1.getExercises().get(0).getName());
    }

    @Test
    public void testAddTargetTwice() {
        g2.addTarget(LocalDate.parse("2025-02-02"), 315, "Squat");
        assertEquals(LocalDate.parse("2025-02-02"), g2.getTargetDate());
        assertEquals("Squat", g2.getExercises().get(0).getName());

        g2.addTarget(LocalDate.parse("2025-03-02"), 315, "Deadlift");
        assertEquals(LocalDate.parse("2025-03-02"), g2.getTargetDate());
        assertEquals("Squat", g2.getExercises().get(0).getName());
        assertEquals("Deadlift", g2.getExercises().get(1).getName());
    }

    @Test
    public void testSummary() {
        assertEquals("Incomplete goal, a goal", g1.summary());
        g2.setCompleted();
        assertEquals("Completed goal 2, different goal", g2.summary());
    }

    @Test
    public void testSetCompleted() {
        assertFalse(g1.isCompleted());
        g1.setCompleted();
        assertTrue(g1.isCompleted());
    }
}
