package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    // TODO: add comments for everything
    @Test
    void testWriterInvalidFile() {
        try {
            List<LogEntry> logs = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLogs() {
        try {
            List<LogEntry> logs = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLogs.json");
            writer.open();
            writer.write(logs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyLogs.json");
            logs = reader.read();
            assertEquals(0, logs.size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLogs() {
        try {
            List<LogEntry> logs = new ArrayList<>();
            Exercise e = new Exercise("Squats", 225, 1, 10);
            Record record = new Record(LocalDate.parse("2024-03-05"), "Squats");
            record.addExercise(e);
            Goal goal = new Goal(LocalDate.parse("2024-03-05"), "Squats", "Squats");
            goal.addTarget(LocalDate.parse("2025-03-05"), 225, "Squats");
            PersonalBest pb = new PersonalBest(LocalDate.parse("2024-03-05"), "Squats", 225);
            logs.add(record);
            logs.add(goal);
            logs.add(pb);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLogs.json");
            writer.open();
            writer.write(logs);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralLogs.json");
            logs = reader.read();
            List<Exercise> exercises = new ArrayList<>();
            exercises.add(e);
            checkRecord("Squats", LocalDate.parse("2024-03-05"), exercises, (Record) logs.get(0));
            checkGoal("Squats", LocalDate.parse("2024-03-05"), exercises,
                    LocalDate.parse("2025-03-05"), false, (Goal) logs.get(1));
            checkPersonalBest("Squats", LocalDate.parse("2024-03-05"), 225, (PersonalBest) logs.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}