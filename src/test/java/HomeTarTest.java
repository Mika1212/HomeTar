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

    public static boolean isEqual(Path it, Path other) throws IOException {

        byte[] first = readAllBytes(it);
        byte[] second = readAllBytes(other);

        assertArrayEquals(first, second);
        return true;
    }

    @Test
    void isEqual() throws IOException {
        Path in1 = Paths.get("testData//in1.txt");
        Path in2 = Paths.get("testData//in2.txt");
        Path inCopy1 = Paths.get("testData//inCopy1.txt");
        Path inCopy2 = Paths.get("testData//inCopy2.txt");
        assertTrue(isEqual(in1, inCopy1));
        assertTrue(isEqual(in2, inCopy2));
    }

    @Test
    void main() throws IOException {
        Path in1 = Paths.get("testData//in1.txt");
        Path in2 = Paths.get("testData//in2.txt");
        Path inCopy1 = Paths.get("testData//inCopy1.txt");
        Path inCopy2 = Paths.get("testData//inCopy2.txt");
        Path output = Paths.get("testData//output.txt");

        HomeTar.main(new String[]{"hometar",  "-out", String.valueOf(output), String.valueOf(in1), String.valueOf(in2)});
        deleteIfExists(in1);
        deleteIfExists(in2);
        HomeTar.main(new String[]{"hometar", "-u", String.valueOf(output)});
        assertTrue(isEqual(in1, inCopy1));
        assertTrue(isEqual(in2, inCopy2));
    }


}