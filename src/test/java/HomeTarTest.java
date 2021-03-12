import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import static java.nio.file.Files.*;
import static org.junit.jupiter.api.Assertions.*;

class HomeTarTest {

    @BeforeEach
    void somePreparations() throws IOException {
        Path in1 = Paths.get("testData//in1.txt");
        Path copy1 = Paths.get("testData//inCopy1.txt");
        Files.copy(in1, copy1, StandardCopyOption.REPLACE_EXISTING);
        Path in2 = Paths.get("testData//in2.txt");
        Path copy2 = Paths.get("testData//inCopy2.txt");
        Files.copy(in2, copy2, StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterEach
    void ending() throws IOException {
        Path copy1 = Paths.get("testData//inCopy1.txt");
        Path copy2 = Paths.get("testData//inCopy2.txt");
        Path output = Paths.get("testData//output.txt");
        deleteIfExists(copy1);
        deleteIfExists(copy2);
        deleteIfExists(output);
    }

    public static boolean isEqual(File it, File other) throws IOException {

        byte[] first = readAllBytes(Paths.get(it.getPath()));
        byte[] second = readAllBytes(Paths.get(other.getPath()));

        assertArrayEquals(first, second);
        return true;
    }

    @Test
    void isEqual() throws IOException {
        assertTrue(isEqual(new File("testData//in1.txt"), new File("testData//inCopy1.txt")));
        assertTrue(isEqual(new File("testData//in2.txt"), new File("testData//inCopy2.txt")));
    }

    @Test
    void main() throws IOException {
        Path in1 = Paths.get("testData//in1.txt");
        Path in2 = Paths.get("testData//in2.txt");
        Path inCopy1 = Paths.get("testData//inCopy1.txt");
        Path inCopy2 = Paths.get("testData//inCopy2.txt");
        Path output = Paths.get("testData//output.txt");

        HomeTar.main(new String[]{"hometar", String.valueOf(inCopy1), String.valueOf(inCopy2), "-out", String.valueOf(output)});
        deleteIfExists(inCopy1);
        deleteIfExists(inCopy2);
        HomeTar.main(new String[]{"hometar", "-u", String.valueOf(output)});
        assertTrue(isEqual(new File(String.valueOf(in1)), new File(String.valueOf(inCopy1))));
        assertTrue(isEqual(new File(String.valueOf(in2)), new File(String.valueOf(inCopy2))));
    }


}