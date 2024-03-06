package persistence;

import model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// General test methods for reading and writing.
public class JsonTest {
    // EFFECTS: Pass if logEntry has given name and date.
    protected void checkLogEntry(String name, LocalDate date, LogEntry logEntry) {
        assertEquals(name, logEntry.getName());
        assertEquals(date, logEntry.getDate());
    }

    // EFFECTS: Pass if record has given attributes.
    protected void checkRecord(String name, LocalDate date,
                               List<Exercise> exercises, Record record) {
        checkLogEntry(name, date, record);
        checkExercises(exercises, record);
    }

    // EFFECTS: Pass if personalBest has given attributes.
    protected void checkPersonalBest(String name, LocalDate date,
                                     int pbWeight, PersonalBest personalBest) {
        checkLogEntry(name, date, personalBest);
        assertEquals(pbWeight, personalBest.getPbWeight());

    }

    // EFFECTS: Pass if personalBest has given attributes.
    protected void checkGoal(String name, LocalDate date,
                             List<Exercise> exercises, LocalDate targetDate,
                             boolean completed, Goal goal) {
        checkLogEntry(name, date, goal);
        checkExercises(exercises, goal);
    }

    // EFFECTS: Pass if record has a list of exercises matching exercises.
    protected void checkExercises(List<Exercise> exercises, Record record) {
        assertEquals(exercises.size(), record.getExercises().size());
        for (int i = 0; i < exercises.size(); i++) {
            checkExercise(exercises.get(i), record.getExercises().get(i));
        }
    }

    // EFFECTS: Check whether two exercise objects have the same attributes
    protected void checkExercise(Exercise e1, Exercise e2) {
        assertEquals(e1.getName(), e2.getName());
        assertEquals(e1.getReps(), e2.getReps());
        assertEquals(e1.getWeight(), e2.getWeight());
        assertEquals(e1.getRpe(), e2.getRpe());
    }

}