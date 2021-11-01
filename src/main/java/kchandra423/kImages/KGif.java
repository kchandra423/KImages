package kchandra423.kImages;

import org.imgscalr.Scalr;
import processing.core.PApplet;
import processing.core.PImage;

class KGif extends KImage {
    private final Frame[] frames;
    private int curFrame;
    private long lastTime;

    KGif(Frame[] frames) {
        this.frames = frames;
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void resize(int w, int h, Scalr.Method method, Scalr.Mode mode) {
        if (method == null) {
            method = Scalr.Method.AUTOMATIC;
        }
        if (mode == null) {
            mode = Scalr.Mode.AUTOMATIC;
        }
        for (Frame f :
                frames) {
            f.resize(w, h, method, mode);
        }
    }

    @Override
    public void draw(PApplet p, float x, float y) {
        super.draw(p, x, y);
        advanceFrame();
    }

    @Override
    public PImage getImage() {
        return frames[curFrame].getImage();
    }

    @Override
    public KGif copy() {
        Frame[] copy = new Frame[frames.length];
        for (int i = 0; i < copy.length; i++) {
            Frame cur = frames[i];
            copy[i] = new Frame(cur.getImage(), cur.getDelay());
        }
        return new KGif(copy);
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
}

