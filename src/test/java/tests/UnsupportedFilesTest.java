package tests;

import kchandra423.kImages.KImageBuilder;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UnsupportedFilesTest {
    @Test
    public void unsupported() {
        assertThrows(IOException.class, () -> {
            KImageBuilder.getKImage("src/test/resources/invalid/UnsupportedExample.arw");
        });
    }

    @Test
    public void invalidLocation() {
        assertThrows(FileNotFoundException.class, () -> {
            KImageBuilder.getKImage("src/invalidDirectory/hi.jpg");
        });
    }
}
