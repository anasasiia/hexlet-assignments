package exercise;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class AppTest {

    @Test
    void testApp() {
        String[][] image = {
                {"*", "*", "-"},
                {"-", "*", "*"},
                {"*", "-", "*"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*", "*", "*", "-", "-"},
                {"*", "*", "*", "*", "-", "-"},
                {"-", "-", "*", "*", "*", "*"},
                {"-", "-", "*", "*", "*", "*"},
                {"*", "*", "-", "-", "*", "*"},
                {"*", "*", "-", "-", "*", "*"},
        };
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testAppSmall() {
        String[][] image = {
                {"*", "*"},
                {"-", "*"},
                {"*", "-"},
        };
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = {
                {"*", "*", "*", "*"},
                {"*", "*", "*", "*"},
                {"-", "-", "*", "*"},
                {"-", "-", "*", "*"},
                {"*", "*", "-", "-"},
                {"*", "*", "-", "-"},
        };
        assertThat(actual).isEqualTo(expected);
    }

   @Test
    void testAppEmpty() {
        String[][] image = new String[0][0];
        String[][] actual = App.enlargeArrayImage(image);
        String[][] expected = new String[0][0];
        assertThat(actual).isEqualTo(expected);
    }

}
// END
