package tests;

import kchandra423.kImages.KImageBuilder;
import kchandra423.movableKImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MovableKImageTest {
    private final MovableKImage img = new MovableKImage(KImageBuilder.getKImage("src/test/resources/BMPExample.bmp"));

    MovableKImageTest() throws IOException {
    }

    @Test
    void translate() {
        assertEquals(0, img.getX());
        assertEquals(0, img.getY());
        img.translate(50, 40);
        assertEquals(50, img.getX());
        assertEquals(40, img.getY());
    }

    @Test
    void moveTo() {
        img.moveTo(30, 50);

        assertEquals(30, img.getX());
        assertEquals(50, img.getY());
    }

    @Test
    void rotate() {
        assertEquals(0, img.getAngle());
        img.rotate(5);
        assertEquals(5, img.getAngle());
    }

    @Test
    void setAngle() {
        img.setAngle(10);
        assertEquals(10, img.getAngle());
    }

    @Test
    void setReflected() {
        assertFalse(img.isReflected());
        img.setReflected(true);
        assertTrue(img.isReflected());
    }

    @Test
    void getMode() {
        assertEquals(MovableKImage.ImageMode.CORNER, img.getMode());
    }

    @Test
    void copy() throws IOException {
        MovableKImage mk = new MovableKImage(KImageBuilder.getKImage("src/test/resources/GIFExample.gif"));
        MovableKImage copy = mk.copy();
        copy.resize(40, 80, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        copy.translate(40, 60);
        mk.rotate((float) Math.PI);
        mk.resize(50, 30, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);


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
        copy.resize(40, 80, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);
        copy.translate(40, 60);
        mk.rotate((float) Math.PI);
        mk.resize(50, 30, Scalr.Method.SPEED, Scalr.Mode.FIT_EXACT);


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