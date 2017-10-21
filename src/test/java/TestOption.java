import org.junit.jupiter.api.Test;
import util.Option;

/**
 * Created by Tomas on 10/21/17.
 */
public class TestOption {

    @Test public void testOption() {
        test(() -> 1, () -> 3.0);
    }

    private void test(Option... options) {
        System.out.println(options[0].getValue());
        System.out.println(options[1].getValue());
    }

}
