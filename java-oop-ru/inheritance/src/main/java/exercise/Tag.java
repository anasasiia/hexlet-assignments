package exercise;

import java.util.Map;

// BEGIN
public class Tag {
    private String name;
    private Map<String, String> attributes;

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public String getName() {
        return name;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("<").append(getName());
        if (!getAttributes().isEmpty()) {
            for (Map.Entry<String, String> entry: getAttributes().entrySet()) {
                sb.append(" ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
            }
        }
        sb.append(">");
        return sb.toString();
    }
}
// END
