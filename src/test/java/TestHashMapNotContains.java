import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tomas on 10/21/17.
 */
public class TestHashMapNotContains {

    @Test public void testHashMap() {
        final Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        System.out.println(map.get(2));
    }

}
