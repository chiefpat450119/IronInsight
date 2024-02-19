package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonalBestTest {
    PersonalBest p1;
    PersonalBest p2;

    @BeforeEach
    public void runBefore() {
        p1 = new PersonalBest(LocalDate.parse("2024-02-18"), "a", 100);
        p2 = new PersonalBest(LocalDate.parse("2024-03-18"), "b", 200);
    }

    @Test
    public void testConstructor() {
        assertEquals(LocalDate.parse("2024-02-18"), p1.getDate());
        assertEquals(LocalDate.parse("2024-03-18"), p2.getDate());
        assertEquals("a", p1.getName());
        assertEquals("b", p2.getName());
        assertEquals(100, p1.getPbWeight());
        assertEquals(200, p2.getPbWeight());
        assertEquals(100, p1.getExercises().get(0).getWeight());
        assertEquals(200, p2.getExercises().get(0).getWeight());
    }

    @Test
    public void testSummary() {
        assertEquals("PB on a of 100 lbs", p1.summary());
        assertEquals("PB on b of 200 lbs", p2.summary());
    }
}
