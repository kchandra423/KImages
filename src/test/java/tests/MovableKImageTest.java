package tests;

import kchandra423.kImages.KImageBuilder;
import kchandra423.movableKImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovableKImageTest {
    private final MovableKImage img = new MovableKImage(KImageBuilder.getKImage("src/test/resources/BMPExample.bmp"));

    MovableKImageTest() throws IOException {
    }

    @Test
    void getWidth() throws IOException {
        assertEquals(640, img.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(480, img.getHeight());
    }

    @Test
    void resize() {
        img.resize(340, 350, null, Scalr.Mode.FIT_EXACT);
        assertEquals(340, img.getWidth());
        assertEquals(350, img.getHeight());
    }

    @Test
    void getMode() {
        assertEquals(MovableKImage.ImageMode.CORNER, img.getMode());
    }

    @Test
    void copy() throws IOException {
        MovableKImage mk = new MovableKImage(KImageBuilder.getKImage("src/test/resources/GIFExample.gif"));
        MovableKImage copy = mk.copy();
        copy.resize(40, 80, null, Scalr.Mode.FIT_EXACT);
        copy.translate(40, 60);
        mk.rotate((float) Math.PI);
        mk.resize(50, 30, null, Scalr.Mode.FIT_EXACT);


        assertEquals(50, mk.getWidth());
        assertEquals(30, mk.getHeight());

        assertEquals(40, copy.getWidth());
        assertEquals(80, copy.getHeight());


        assertEquals(40, copy.getX());
        assertEquals(60, copy.getY());

        assertEquals(0, copy.getAngle());

        assertEquals(0, mk.getX());
        assertEquals(0, mk.getY());


        assertEquals((float) Math.PI, mk.getAngle());

        mk = new MovableKImage(KImageBuilder.getKImage("src/test/resources/PNGExample.png"));
        copy = mk.copy();
        copy.resize(40, 80, null, Scalr.Mode.FIT_EXACT);
        copy.translate(40, 60);
        mk.rotate((float) Math.PI);
        mk.resize(50, 30, null, Scalr.Mode.FIT_EXACT);


        assertEquals(50, mk.getWidth());
        assertEquals(30, mk.getHeight());

        assertEquals(40, copy.getWidth());
        assertEquals(80, copy.getHeight());


        assertEquals(40, copy.getX());
        assertEquals(60, copy.getY());

        assertEquals(0, copy.getAngle());

        assertEquals(0, mk.getX());
        assertEquals(0, mk.getY());


        assertEquals((float) Math.PI, mk.getAngle());
    }
}