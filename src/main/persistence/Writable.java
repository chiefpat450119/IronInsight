package persistence;

import org.json.JSONObject;

// Interface representing classes that can be written to Json
// Data persistence implementation is based on JsonSerializationDemo.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}