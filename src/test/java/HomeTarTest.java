import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomeTarTest {

    @Test
    void isEqual() throws IOException {
        assertTrue(HomeTar.isEqual(new File("outKeyInput1.txt"), new File("outKeyInput1.txt")));
        assertTrue(HomeTar.isEqual(new File("outKeyInput2.txt"), new File("outKeyInput2.txt")));
        assertFalse(HomeTar.isEqual(new File("uKeyInput.txt"), new File("outKeyInput1.txt")));
    }

    @Test
    void outKey() throws IOException {
        assertTrue(HomeTar.isEqual(new File("outKeyExpected.txt"), new File("outKeyOutput.txt")));
        assertFalse(HomeTar.isEqual(new File("uKeyInput.txt"), new File("outKeyOutput.txt")));
    }

    @Test
    void uKey() throws IOException {
        assertTrue(HomeTar.isEqual(new File("uKeyExpected1.txt"), new File("uKeyOutput1.txt")));
        assertTrue(HomeTar.isEqual(new File("uKeyExpected2.txt"), new File("uKeyOutput2.txt")));
        assertTrue(HomeTar.isEqual(new File("outKeyInput1.txt"), new File("uKeyOutput1.txt")));
        assertTrue(HomeTar.isEqual(new File("outKeyInput2.txt"), new File("uKeyOutput2.txt")));
    }
}