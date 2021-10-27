package tests;

import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationTest {
    @Test
    public void checkImageSimilarity() throws IOException {
        KImage image = KImageBuilder.getKImage("src/test/resources/JPEGExample.jpeg");
        assertEquals(image.getX(), 0);
        assertEquals(image.getY(), 0);
        image.moveTo(50, 100);
        assertEquals(image.getX(), 50);
        assertEquals(image.getY(), 100);
    }
}
