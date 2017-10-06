package score;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FileScoreService extends SimpleScoreService {
    private Gson gson;
    private File file;

    public FileScoreService(File file) {
        this.gson = new Gson();
        this.file = file;

        if (file.exists()) {
            try {
                results = gson.fromJson(new BufferedReader(new FileReader(file)), new ScoreMapToken().getType());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public FileScoreService(String path) {
        this(new File(path));
    }

    @Override
    public void addScore(int score, Difficulty difficulty) {
        super.addScore(score, difficulty);
        if (!file.exists()) {
            new File(file.getName().substring(0, file.getName().lastIndexOf('/'))).mkdirs();
        }
        try (PrintWriter pw = new PrintWriter(file)) {
            pw.print(gson.toJson(results));
        } catch (FileNotFoundException e) {
            assert false;
        }
    }

    @JsonAdapter(ScoreMapDeserializer.class)
    class ScoreMapToken extends TypeToken<Map<Difficulty, TreeSet<Integer>>> {
    }

    public class ScoreMapDeserializer implements JsonDeserializer<Map<Difficulty, TreeSet<Integer>>> {
        @Override
        public Map<Difficulty, TreeSet<Integer>> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            Map<Difficulty, TreeSet<Integer>> result = new HashMap<>(results);

            JsonObject jsonObject = json.getAsJsonObject();
            for (Difficulty difficulty : Difficulty.values()) {
                for (JsonElement score : jsonObject.get(difficulty.name()).getAsJsonArray()) {
                    results.get(difficulty).add(score.getAsInt());
                }
            }
            return result;
        }
    }

    public static void main(String[] args) {
        ScoreService a = new FileScoreService("lol/kek/test.txt");
        a.addScore(1, Difficulty.EASY);
    }
}
