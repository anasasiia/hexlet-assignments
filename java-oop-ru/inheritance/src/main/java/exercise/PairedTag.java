package exercise;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag{
    private final String body;
    private final List<Tag> children;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> children) {
        setName(name);
        setAttributes(attributes);
        this.body = body;
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        if (!Objects.equals(body, "")) {
            sb.append(body);
        }
        String childrenStringFormat = children.stream()
                                    .map(Tag::toString)
                                    .collect(Collectors.joining(""));
        sb.append(childrenStringFormat);
        sb.append("</").append(getName()).append(">");
        return sb.toString();
        }
    }

// END
