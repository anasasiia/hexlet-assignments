package exercise;

import lombok.*;

// BEGIN
@AllArgsConstructor
@Getter
@Setter
// END
class User {
    int id;
    String firstName;
    String lastName;
    int age;
}
