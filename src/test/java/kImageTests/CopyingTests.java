package kImageTests;

import kchandra423.kColliders.KCollider;
import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import kchandra423.kImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CopyingTests {
    @Test
    public void copyGIFKImage() throws IOException {
        KImage k = KImageBuilder.getKImage("src/test/resources/GIFExample.gif");
        KImage copy = k.copy();
        copy.resize(40, 80, null, Scalr.Mode.FIT_EXACT);
        k.resize(50, 30, null, Scalr.Mode.FIT_EXACT);
        assertEquals(50, k.getWidth());
        assertEquals(30, k.getHeight());
        assertEquals(40, copy.getWidth());
        assertEquals(80, copy.getHeight());
    }

    @Test
    public void copyStaticKImage() throws IOException {
        KImage k = KImageBuilder.getKImage("src/test/resources/PNGExample.png");
        KImage copy = k.copy();
        copy.resize(40, 80, null, Scalr.Mode.FIT_EXACT);
        k.resize(50, 30, null, Scalr.Mode.FIT_EXACT);
        assertEquals(50, k.getWidth());
        assertEquals(30, k.getHeight());
        assertEquals(40, copy.getWidth());
        assertEquals(80, copy.getHeight());
    }

}
