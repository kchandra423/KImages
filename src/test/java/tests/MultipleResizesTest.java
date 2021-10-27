package tests;

import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MultipleResizesTest {
    @Test
    public void checkImageSimilarity() throws IOException {
        KImage image = KImageBuilder.getKImage("src/test/resources/JPEGExample.jpeg");
        image.resize(250, 250, Scalr.Method.ULTRA_QUALITY);

        BufferedImage buffered = (BufferedImage) image.getImage().getImage();

        for (int i = 0; i < 10; i++) {
            image.scale(1.01f, 1.01f, Scalr.Method.SPEED);
        }
        image.resize(250, 250, Scalr.Method.ULTRA_QUALITY);
        assertTrue(imagesEqual(buffered, (BufferedImage) image.getImage().getImage()));
    }

    private boolean imagesEqual(BufferedImage img1, BufferedImage img2) {
        if (img1.getHeight() != img2.getHeight() || img1.getWidth() != img2.getWidth()) {
            return false;
        }
        for (int i = 0; i < img1.getWidth(); i++) {
            for (int j = 0; j < img1.getHeight(); j++) {
                if (img1.getRGB(i, j) != img2.getRGB(i, j)){
                    return false;
                }

            }
        }
        return true;
    }
}
