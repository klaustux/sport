package lt.eimis.view;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by eimis on 26/01/16.
 */
public class StatisticsViewTest {

    private List<Integer> values = new ArrayList<>();

    private List<String> prefixes = new ArrayList<>();

    @Test
    @Ignore
    public void testOnCreatePlayersStats() throws Exception {
        values = Arrays.asList(1, 1, 2, 3, 3, 4, 5, 6, 6, 6, 7, 8, 9, 10, 11, 11, 11);

        for (int i = 0; i < values.size(); i++) {
            prefixes.add(String.valueOf(i + 1));
        }

        for (int i = 0; i < values.size(); i++) {
            int begin = i;
            int end = i;
            while (end < values.size() - 1
                   && values.get(end + 1) == values.get(i)) {
                end++;
            }
            while (begin > 0 && values.get(begin - 1) == values.get(i)) {
                begin--;
            }
            if (begin != end) {
                prefixes.set(i, begin + 1 + "-" + (end + 1));
            }
        }

        for (int i = 0; i < prefixes.size(); i++) {
            System.out.println(prefixes.get(i) + " "
                               + values.get(i));
        }
    }
}