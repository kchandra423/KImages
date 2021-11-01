package kchandra423.kImages;

import processing.core.PApplet;
import processing.core.PImage;

public abstract class KImage implements Scalable {
    public int getWidth() {
        return getImage().width;
    }

    public int getHeight() {
        return getImage().height;
    }

    public void draw(PApplet p, float x, float y) {
        p.image(getImage(), x, y);
    }

    public abstract PImage getImage();

    public abstract KImage copy();

}
