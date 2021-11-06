package examples.MovableKImages;

import kchandra423.kImages.KImageBuilder;
import kchandra423.movableKImages.MovableKImage;
import processing.core.PApplet;

import java.io.IOException;

public class DrawingSurface extends PApplet {
    private MovableKImage k;

    public DrawingSurface() throws IOException {
    }

    //just check that this doesn't result in an error

    public void setup() {
        try {
            k = new MovableKImage(KImageBuilder.getKImage("src/test/resources/DNGExample.DNG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        k.resize(250,250);
    }

    public void draw() {
        background(200);
        k.draw(this);
    }

    public void keyPressed() {
        switch (key) {
            case 'w':
                k.translate(0,-5);
                break;
            case 's':
                k.translate(0,5);
                break;
            case 'd':
                k.translate(5,0);
                break;
            case 'a':
                k.translate(-5,0);
                break;
            case ' ':
                k.setReflected(!k.isReflected());
                break;
            case 'f':
                k.rotate((float) (Math.PI/10));
                break;
            case 'r':
                k.rotate(-(float) (Math.PI/10));
                break;
            case 'c':
                k.moveTo(0,0);
                k.setReflected(false);
                k.setAngle(0);
        }
    }


}
