package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{
    private final String filepath;
    private final Map<String, String> data = new HashMap<>();

    public FileKV(String filepath, Map<String, String> storage) {
        this.filepath = filepath;
        data.putAll(storage);
        String content = Utils.serialize(data);
        Utils.writeFile(filepath, content);
    }

    @Override
    public void set(String key, String value) {
        String content = Utils.readFile(filepath);
        Map<String, String> data = Utils.unserialize(content);
        data.put(key, value);
        String contentUpdated = Utils.serialize(data);
        Utils.writeFile(filepath, contentUpdated);
    }

    @Override
    public void unset(String key) {
        String content = Utils.readFile(filepath);
        Map<String, String> data = Utils.unserialize(content);
        data.remove(key);
        String contentUpdated = Utils.serialize(data);
        Utils.writeFile(filepath, contentUpdated);
    }

    @Override
    public String get(String key, String defaultValue) {
        String content = Utils.readFile(filepath);
        Map<String, String> data = Utils.unserialize(content);
        return data.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(data);
    }
}
// END
