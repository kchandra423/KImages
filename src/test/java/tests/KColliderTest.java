package tests;

import kchandra423.kColliders.KCollider;
import kchandra423.kImages.KImageBuilder;
import kchandra423.movableKImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class KColliderTest {
    static {
        KCollider.setDefaultAreaDensity(10);
    }

    private final KCollider img = new KCollider(KImageBuilder.getKImage("src/test/resources/SmallPNG.png"));

    KColliderTest() throws IOException {
    }


    @Test
    void intersects() throws IOException {
        KCollider k = new KCollider(KImageBuilder.getKImage("src/test/resources/SmallPNG.png"));
        KCollider k2 = new KCollider(KImageBuilder.getKImage("src/test/resources/PNGExample.png"));
        assertTrue(k.intersects(k2));
        k2.resize(250,250);
        assertTrue(k.intersects(k2));
        k2.translate(400,0);
        assertFalse(k.intersects(k2));
    }

    @Test
    void getBounds() throws IOException {
        KCollider k = new KCollider(KImageBuilder.getKImage("src/test/resources/SmallPNG.png"), 1);
        Rectangle r = new Rectangle(100, 50, 163, 320);
        assertEquals(r, k.getBounds());
    }


    @Test
    void copy() throws IOException {
        MovableKImage mk = new KCollider(KImageBuilder.getKImage("src/test/resources/GifExample.gif"), 20);
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

        mk = new KCollider(KImageBuilder.getKImage("src/test/resources/PNGExample.png"), 20);
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