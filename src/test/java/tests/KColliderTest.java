package tests;

import kchandra423.kColliders.KCollider;
import kchandra423.kImages.KImageBuilder;
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
    void intersects() throws IOException {
//        KCollider img1 = new KCollider(KImageBuilder.getKImage("src/test/resources/PNGExample.png"));
//        KCollider img2 = new KCollider(KImageBuilder.getKImage("src/test/resources/PNGExample.png"));


    }

    @Test
    void getBounds() throws IOException {
//        KCollider img1 = new KCollider(KImageBuilder.getKImage("src/test/resources/SmallPNG.png"), 1);
//        Rectangle r = new Rectangle(0, 100, 1200, 1136);
//        assertEquals(r, img1.getBounds());
    }

    @Test
    void resize() {
    }

    @Test
    void copy() {
    }
}