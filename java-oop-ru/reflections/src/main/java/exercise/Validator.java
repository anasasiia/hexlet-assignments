package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        List<String> notValidFields = new ArrayList<>();
        for (Field field: address.getClass().getDeclaredFields()) {
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (notNull != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) == null) {
                        notValidFields.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return notValidFields;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        List<String> fieldsNotValidByNull = validate(address);
        Map<String, List<String>> notValidFields = new HashMap<>();
        fieldsNotValidByNull.forEach(field -> notValidFields.put(field, List.of("can not be null")));
        for (Field field: address.getClass().getDeclaredFields()) {
            MinLength minLength = field.getAnnotation(MinLength.class);
            if (minLength != null) {
                field.setAccessible(true);
                try {
                    if (field.get(address) != null) {
                        if (field.get(address).toString().length() < 3) {
                            if (notValidFields.containsKey(field.getName())) {
                                notValidFields.get(field.getName()).add("length less than 3");
                            }
                            notValidFields.put(field.getName(), List.of("length less than 3"));
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return notValidFields;
    }
}
// END
