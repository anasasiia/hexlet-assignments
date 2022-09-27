package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3));

        // Случай 1: взять k элементов из пустого листа
        List<Integer> list1 = new ArrayList<>();
        List<Integer> actualList1 = App.take(list1, 4);
        assertThat(actualList1).isEmpty();

        // Случай 2: взять k элементов из n листа, где k > n
        List<Integer> actualList2 = App.take(list, 6);
        assertThat(actualList2).isEqualTo(list);

        // Случай 3: взять k элементов из n листа, где k < n
        List<Integer> actualList3 = App.take(list, 2);
        List<Integer> expectedList = list.subList(0, 2);
        assertThat(actualList3).isEqualTo(expectedList);

        // END
    }
}
