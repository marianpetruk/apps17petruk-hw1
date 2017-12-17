package json;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 * Changed by Marian Petruk on 17/12/2017
 */
public class JsonObject extends Json {

    private final HashMap<String, Json> JSONPairs = new HashMap<String, Json>();


    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair pair : jsonPairs) {
            JSONPairs.put(pair.key, pair.value);
        }

    }

    @Override
    public String toJson() {
        StringBuilder jsonString = new StringBuilder("{");
        Set<String> keys = JSONPairs.keySet();
        int index = 0;
        for (String key : keys) {
            index++;
            jsonString.append("'");
            jsonString.append(key);
            jsonString.append("': ");
            jsonString.append(JSONPairs.get(key).toJson());
            if (index < keys.size()) {
                jsonString.append(", ");
            }
        }
        return jsonString + "}";
    }

    public void add(JsonPair jsonPair) {
        JSONPairs.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        if (JSONPairs.containsKey(name)) {
            return JSONPairs.get(name);
        }
        return null;
    }

    public JsonObject projection(String... names) {
        JsonObject projectedObject = new JsonObject();
        for (String name : names) {
            if (JSONPairs.containsKey(name)) {
                JsonPair projectedPair = new JsonPair(name, JSONPairs.get(name));
                projectedObject.add(projectedPair);
            }
        }
        return projectedObject;
    }
}
