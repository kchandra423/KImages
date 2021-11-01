package kchandra423.kImages;

import org.imgscalr.Scalr;
import processing.core.PImage;

import java.awt.image.BufferedImage;

class StaticImage extends KImage {
    private final PImage image;
    private PImage scaledCopy;

    StaticImage(PImage image) {
        this.image = image;
        this.scaledCopy = image;
    }

    @Override
    public void resize(int w, int h, Scalr.Method method, Scalr.Mode mode) {
        if (method == null) {
            method = Scalr.Method.AUTOMATIC;
        }
        if (mode == null) {
            mode = Scalr.Mode.AUTOMATIC;
        }
        BufferedImage resized = Scalr.resize((BufferedImage) image.getImage(), method, mode, w, h);
        scaledCopy = new PImage(resized);
    }

    @Override
    public PImage getImage() {
        return scaledCopy;
    }

    @Override
    public KImage copy() {
        return new StaticImage(getImage());
    }

}
