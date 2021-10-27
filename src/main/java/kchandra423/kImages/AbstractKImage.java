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

abstract class AbstractKImage implements KImage {
    private float x, y;
    private float angle;
    private boolean reflected, reversed;

    protected AbstractKImage(float x, float y, float angle, boolean reflected, boolean reversed) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.reflected = reflected;
        this.reversed = reversed;
    }

    @Override
    public void resize(int w, int h) {
        resize(w, h, Scalr.Mode.AUTOMATIC, Scalr.Method.AUTOMATIC);
    }

    @Override
    public void scale(float stretchX, float stretchY) {
        scale(stretchX, stretchY, Scalr.Mode.AUTOMATIC, Scalr.Method.AUTOMATIC);
    }

    @Override
    public void resize(int w, int h, Scalr.Mode mode) {
        resize(w, h, mode, Scalr.Method.AUTOMATIC);
    }

    @Override
    public void scale(float stretchX, float stretchY, Scalr.Mode mode) {
        scale(stretchX, stretchY, mode, Scalr.Method.AUTOMATIC);
    }

    @Override
    public void resize(int w, int h, Scalr.Method method) {
        resize(w, h, Scalr.Mode.AUTOMATIC, method);
    }

    @Override
    public void scale(float stretchX, float stretchY, Scalr.Method method) {
        scale(stretchX, stretchY, Scalr.Mode.AUTOMATIC, method);
    }

    @Override
    public void scale(float stretchX, float stretchY, Scalr.Mode mode, Scalr.Method method) {
        resize((int) (getWidth() * stretchX), (int) (getHeight() * stretchX), mode, method);
    }

    @Override
    public int getWidth() {
        return getImage().width;
    }

    @Override
    public int getHeight() {
        return getImage().height;
    }

    @Override
    public void translate(float delx, float dely) {
        x += delx;
        y += dely;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void setAngle(float theta) {
        theta %= Math.PI * 2;
        angle = theta;
    }

    @Override
    public float getAngle() {
        return angle;
    }

    @Override
    public boolean isReflected() {
        return reflected;
    }

    @Override
    public boolean isReversed() {
        return reversed;
    }

    @Override
    public void rotate(float theta) {
        angle += theta;
        angle %= Math.PI * 2;
    }

    @Override
    public void reflect(boolean flag) {
        this.reflected = flag;
    }

    @Override
    public void reverse(boolean flag) {
        this.reversed = flag;
    }

    @Override
    public void draw(PApplet p) {
        p.pushMatrix();
        if (!reflected) {
            p.translate(x, y);
            p.rotate(angle);
        } else {

            if (reversed) {
                p.translate(-x, y);
                p.rotate((float) (Math.PI - angle));
            } else {
                p.scale(-1, 1);
                p.translate(-x, y);


                p.rotate(-angle);
                p.translate(-getWidth(),0);

            }

        }
        p.image(getImage(), 0, 0);
        p.popMatrix();
    }

    @Override
    public abstract Object clone();

    @Override
    public abstract void resize(int w, int h, Scalr.Mode mode, Scalr.Method method);


}
