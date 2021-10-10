package kchandra423.kImages;

import processing.core.PApplet;
import processing.core.PImage;

class KGif extends AbstractKImage {
    private final Frame[] frames;
    private int curFrame;
    private long lastTime;

    KGif(Frame[] frames, float x, float y, float angle, float scaleX, float scaleY, boolean reflected, boolean reversed) {
        super(x, y, angle, scaleX, scaleY, reflected, reversed);
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

    @Override
    public Object clone() {
        return new KGif(cloneFrames(), getX(), getY(), getAngle(), getScaleX(), getScaleY(), isReflected(), isReversed());
    }

    private Frame[] cloneFrames() {
        Frame[] copy = new Frame[frames.length];
        for (int i = 0; i < copy.length; i++) {
            copy[i] = frames[i].clone();
        }
        return copy;
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

//    @Override
//    public void resize(int w, int h) {
//        for (Frame frame : frames) {
//            frame.getImage().resize(w, h);
//        }
//    }


}
