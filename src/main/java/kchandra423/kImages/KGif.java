/*
MIT License

Copyright (c) 2021 Kumar Chandra

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package kchandra423.kImages;

import org.imgscalr.Scalr;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * The specific class that animates gifs using {@link at.dhyan.open_imaging.GifDecoder}.
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see at.dhyan.open_imaging.GifDecoder
 */
class KGif implements KImage {
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
        KImage.super.draw(p, x, y);
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
        //.getDelay return time in 100ths of a second, convert to milliseconds
        if (curTime >= lastTime + frames[curFrame].getDelay() * 10L) {
            lastTime = curTime;
            curFrame++;
            if (curFrame >= frames.length) {
                curFrame = 0;
            }
        }
    }
}

