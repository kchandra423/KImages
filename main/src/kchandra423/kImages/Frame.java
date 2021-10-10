package kchandra423.kImages;

import processing.core.PImage;

class Frame implements Cloneable{
    private final PImage image;
    private final int delay;

    public Frame(PImage image, int delay) {
        this.image = image;
        this.delay = delay;
    }

    public PImage getImage() {
        return image;
    }

    public int getDelay() {
        return delay;
    }

    @Override
    public Frame clone() {
            return new Frame(image.copy(), delay);
    }
}