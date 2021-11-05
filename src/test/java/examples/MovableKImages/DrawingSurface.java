package examples.MovableKImages;

import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import kchandra423.movableKImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawingSurface extends PApplet {
    private float angle;
    private boolean reflected;
    private float x, y;
    ArrayList<MovableKImage> examples = new ArrayList<>();

    //just check that this doesn't result in an error

    public void setup() {

    }

    public void draw() {
        background(200);
        for (int i = 0; i < examples.size(); i++) {
            float x = i % 3 * 250;
            float y = (i / 3) * 250;

            examples.get(i).moveTo(x+this.x, y+this.y);

            examples.get(i).setReflected(reflected);

            examples.get(i).setAngle(angle);
            examples.get(i).draw(this);
        }
    }

    public void keyPressed() {
        switch (key) {
            case 'w':
                y -= 5;
                break;
            case 's':
                y += 5;
                break;
            case 'd':
                x += 5;
                break;
            case 'a':
                x -= 5;
                break;
            case ' ':
                reflected = !reflected;
                break;
            case 'f':
                angle += Math.PI / 10f;
                break;
            case 'r':
                angle-= Math.PI /10f;
                break;
            case 'c':
                x = 0;
                y = 0;
                angle = 0;
                reflected = false;
        }
    }


}
