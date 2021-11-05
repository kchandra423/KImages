package examples.KColliders;

import kchandra423.kColliders.KCollider;
import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import kchandra423.movableKImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.io.IOException;

public class DrawingSurface extends PApplet {
    private KCollider img1, img2;

    //just check that this doesn't result in an error
    @Test
    public void setup() {
        try {
            KCollider.setDefaultAreaDensity(10);
            KImage image = KImageBuilder.getKImage("src/test/resources/PNGExample.png");
            image.resize(250, 250, null, Scalr.Mode.FIT_EXACT);
            img1 = new KCollider(image,0,0,0,false, MovableKImage.ImageMode.CORNER);
            img2 = img1.copy();
            img2.moveTo(300,300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw() {
        if (img2.intersects(img1)) {
            background(200, 0, 0);
        } else {
            background(200);
        }
        img1.draw(this);
        img2.draw(this);
    }

    public void keyPressed() {
        switch (key) {
            case 'w':
                img1.translate(0,-5);
                break;
            case 's':
                img1.translate(0,5);
                break;
            case 'd':
                img1.translate(5,0);
                break;
            case 'a':
                img1.translate(-5,0);
                break;
            case ' ':
                img1.setReflected(!img1.isReflected());
                break;
            case 'f':
                img1.rotate((float) (Math.PI / 10));
                break;
            case 'r':
                img1.rotate(-(float) (Math.PI / 10));
                break;
            case 'c':
                img1.moveTo(0,0);
                img1.setAngle(0);
        }
    }


}
