import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HomeTarTest {

    public static boolean isEqual(File it, File other) throws IOException {
        FileReader itReader = new FileReader(it);
        FileReader otherReader = new FileReader(other);
        int itCharacter;
        int otherCharacter;

        while ((itCharacter = itReader.read()) != -1) {
            otherCharacter = otherReader.read();
            if (itCharacter != otherCharacter) {
                return false;
            }
        }
        return true;
    }

    @Test
    void isEqual() throws IOException {
        assertTrue(isEqual(new File("testData//in1.txt"), new File("testData//inCopy1.txt")));
        assertTrue(isEqual(new File("testData//in2.txt"), new File("testData//inCopy2.txt")));
        assertFalse(isEqual(new File("testData//inCopy1.txt"), new File("testData//inCopy2.txt")));
    }

    @Test
    void main() throws IOException {
        HomeTar.main(new String[]{"hometar", "testData//in1.txt", "testData//in2.txt", "-out", "testData//output.txt"});
        HomeTar.main(new String[]{"hometar", "-u", "testData//output.txt"});
        assertTrue(isEqual(new File("testData//in1.txt"), new File("testData//inCopy1.txt")));
        assertTrue(isEqual(new File("testData//in2.txt"), new File("testData//inCopy2.txt")));
    }


}