package kMovableImageTests;

import kchandra423.kColliders.KCollider;
import kchandra423.kImages.KImageBuilder;
import kchandra423.kImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CopyingTests {

    @Test
    public void copyGIFMovableKImage() throws IOException {
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
    }

    @Test
    public void copyStaticMovableKImage() throws IOException {
        MovableKImage mk = new MovableKImage(KImageBuilder.getKImage("src/test/resources/PNGExample.png"));
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
    }

    @Test
    public void copyGIFKCollider() throws IOException {
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
    }

    @Test
    public void copyStaticKCollider() throws IOException {
        MovableKImage mk = new KCollider(KImageBuilder.getKImage("src/test/resources/PNGExample.png"), 20);
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
    }

    @Test
    public void copyGifKCollider() throws IOException {
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
    }
}
