package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistent.json");
        try {
            List<LogEntry> logs = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLogs.json");
        try {
            List<LogEntry> logs = reader.read();
            assertEquals(0, logs.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLogs() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLogs.json");
        // TODO: Check all elements of the logs list
        try {
            List<LogEntry> logs = reader.read();
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(new Exercise("e1", 225, 1, 10));
            checkGoal("g1", LocalDate.parse("2024-03-05"), exercises,
                    LocalDate.parse("2025-04-04"), true, (Goal) logs.get(0));
            exercises.set(0, new Exercise("e2", 100, 10, 10));
            checkRecord("entry1", LocalDate.parse("2024-03-05"), exercises, (Record) logs.get(1));
            exercises.set(0, new Exercise("pb1", 250, 1, 10));
            checkPersonalBest("pb1", LocalDate.parse("2024-03-05"), 250, (PersonalBest) logs.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}