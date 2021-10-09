package kchandra423.kImages;

import kchandra423.kImages.AbstractKImage;
import kchandra423.kImages.Frame;
import processing.core.PApplet;
import processing.core.PImage;

class KGif extends AbstractKImage {
    private Frame[] frames;
    private int curFrame;
    private long lastTime;

    KGif(Frame[] frames, float x, float y, float angle, boolean reflected, boolean reversed) {
        super(x, y, angle, reflected, reversed);
        this.frames = frames;
        lastTime = System.currentTimeMillis();

    }
    @Override
    public void draw(PApplet p) {

//        PImage curImage = frames[curFrame].getImage();
//
//        p.image(curImage, x, y);
        super.draw(p);


        advanceFrame();

    }

    @Override
    public PImage getImage() {
        return frames[curFrame].getImage();
    }

    private void advanceFrame() {
        long curTime = System.currentTimeMillis();
        //.getDelay return time in 100ths of a second
        if (curTime >= lastTime + frames[curFrame].getDelay() * 10L) {
            lastTime = curTime;
            curFrame++;
            if (curFrame >= frames.length) {
                curFrame = 0;
            }
        }
    }

    @Override
    public void resize(int w, int h) {
        for (Frame frame : frames) {
            frame.getImage().resize(w, h);
        }
    }


}
