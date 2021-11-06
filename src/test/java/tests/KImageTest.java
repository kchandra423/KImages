package tests;

import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KImageTest {
    private final KImage img = KImageBuilder.getKImage("src/test/resources/TiffExamples.tiff");

    KImageTest() throws IOException {
    }

    @Test
    void getWidth() {
        assertEquals(640, img.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(426, img.getHeight());
    }

    @Test
    void scale() {
        img.scale(2, 2, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        assertEquals(1280, img.getWidth());
        assertEquals(852, img.getHeight());
    }

    @Test
    void resize() {
        img.resize(340, 350, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        assertEquals(340, img.getWidth());
        assertEquals(350, img.getHeight());
    }


    @Test
    void getImage() {
        img.getImage();
    }

    @Test
    void copy() throws IOException {
        KImage k = KImageBuilder.getKImage("src/test/resources/PNGExample.png");
        KImage copy = k.copy();
        copy.resize(40, 80, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        k.resize(50, 30, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        assertEquals(50, k.getWidth());
        assertEquals(30, k.getHeight());
        assertEquals(40, copy.getWidth());
        assertEquals(80, copy.getHeight());

        k = KImageBuilder.getKImage("src/test/resources/GIFExample.gif");
        copy = k.copy();
        copy.resize(40, 80, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        k.resize(50, 30, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        assertEquals(50, k.getWidth());
        assertEquals(30, k.getHeight());
        assertEquals(40, copy.getWidth());
        assertEquals(80, copy.getHeight());
    }

    @Test
    void performance() {
        img.resize(200, 200, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        Image copy = img.getImage().getImage();
        for (int i = 0; i < 10; i++) {
            img.scale(1.1f, 1.1f, Scalr.Method.SPEED, null);
        }
        img.resize(200, 200, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        assertTrue(equals((BufferedImage) copy, (BufferedImage) img.getImage().getImage()));
    }

    private boolean equals(BufferedImage b1, BufferedImage b2) {
        if (b1.getWidth() != b2.getWidth()) {
            return false;
        }
        if (b1.getHeight() != b2.getHeight()) {
            return false;
        }
        for (int i = 0; i < b1.getWidth(); i++) {
            for (int j = 0; j < b1.getHeight(); j++) {
                if (b1.getRGB(i, j) != b2.getRGB(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

}