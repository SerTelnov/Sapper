package UI.UIElements.jsonParser;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.InputStream;

/**
 * Created by @author Telnov Sergey on 08.09.2017.
 */

public class JsonParser {
    private JsonReader jsonReader;

    public JsonParser() {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream in = classLoader.getResourceAsStream("timeResults.json");
        this.jsonReader = Json.createReader(in);
    }

    public void parseInput() {
        JsonObject obj = jsonReader.readObject();
        JsonArray results = obj.getJsonArray("results");
        for (JsonObject result : results.getValuesAs(JsonObject.class)) {
            System.out.println(result.getJsonString("time"));
        }
    }
}
