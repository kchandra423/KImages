package kchandra423.kImages;

import processing.core.PImage;

class Frame extends StaticImage{
    private final int delay;
    private int loops;
    Frame(PImage image, int delay) {
        super(image);
        this.delay = delay;
    }

    int getDelay() {
        return delay;
    }


}