
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HomeTarTest {

    @Test
    void uKey() throws IOException {
        String a = "outputForUkey.txt";
        String forTest1 = "testForUkey1.txt";
        String forTest2 = "testForUkey2.txt";
        String test1 = "test1";
        String test2 = "test2";
        HomeTar.uKey(a);

        assertEquals(new File(forTest1), new File(test1));
        assertEquals(new File(forTest2), new File(test2));
    }

    @Test
    void outKey() {
    }
}