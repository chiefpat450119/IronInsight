package persistence;

import org.json.JSONObject;

// Data persistence implementation is based on JsonSerializationDemo.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}