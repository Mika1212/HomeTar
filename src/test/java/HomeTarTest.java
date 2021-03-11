import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.delete;
import static java.nio.file.Files.readAllBytes;
import static org.junit.jupiter.api.Assertions.*;

class HomeTarTest {

    void somePreparations() throws IOException {
        Path it1 = Paths.get("testData//in1.txt");
        Path copy1 = Paths.get("testData//inCopy1.txt");
        Files.copy(it1, copy1, StandardCopyOption.REPLACE_EXISTING);
        Path it2 = Paths.get("testData//in2.txt");
        Path copy2 = Paths.get("testData//inCopy2.txt");
        Files.copy(it2, copy2, StandardCopyOption.REPLACE_EXISTING);
    }

    void ending() throws IOException {
        Path copy1 = Paths.get("testData//inCopy1.txt");
        Path copy2 = Paths.get("testData//inCopy2.txt");
        Path output = Paths.get("testData//output.txt");
        delete(copy1);
        delete(copy2);
        delete(output);
    }

    public static boolean isEqual(File it, File other) throws IOException {

        byte[] first = readAllBytes(Paths.get(it.getPath()));
        byte[] second = readAllBytes(Paths.get(other.getPath()));
        try {
            assertArrayEquals(first, second);
        } catch (AssertionFailedError e) {
            return false;
        }
        return true;
    }

    @Test
    void isEqual() throws IOException {
        somePreparations();
        assertTrue(isEqual(new File("testData//in1.txt"), new File("testData//inCopy1.txt")));
        assertTrue(isEqual(new File("testData//in2.txt"), new File("testData//inCopy2.txt")));
        assertFalse(isEqual(new File("testData//inCopy1.txt"), new File("testData//inCopy2.txt")));
        ending();
    }

    @Test
    void main() throws IOException {
        somePreparations();
        HomeTar.main(new String[]{"hometar", "testData//in1.txt", "testData//in2.txt", "-out", "testData//output.txt"});
        HomeTar.main(new String[]{"hometar", "-u", "testData//output.txt"});
        assertTrue(isEqual(new File("testData//in1.txt"), new File("testData//inCopy1.txt")));
        assertTrue(isEqual(new File("testData//in2.txt"), new File("testData//inCopy2.txt")));
        ending();
    }


}