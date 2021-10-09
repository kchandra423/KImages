package kchandra423.kImages;

import processing.core.PImage;

import java.awt.image.BufferedImage;

class Frame {
    private final PImage image;
    private final int delay;

    public Frame(BufferedImage image, int delay) {
        this.image = new PImage(image);
        this.delay = delay;
    }

    public PImage getImage() {
        return image;
    }

    public int getDelay() {
        return delay;
    }
}