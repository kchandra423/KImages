package kchandra423.kImages;

import processing.core.PApplet;
import processing.core.PImage;

abstract class AbstractKImage implements KImage {
    private float x, y;
    private float scaleX, scaleY;
    private float angle;
    private boolean reflected, reversed;

    protected AbstractKImage(float x, float y, float angle, float scaleX, float scaleY, boolean reflected, boolean reversed) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        this.reflected = reflected;
        this.reversed = reversed;
    }

    @Override
    public void resize(int w, int h) {
        scaleX = (float) w / getImage().width;
        scaleY = (float) h / getImage().height;
    }

    @Override
    public void scale(float stretchX, float stretchY) {
        this.scaleX *= stretchX;
        this.scaleY *= stretchY;
    }

    @Override
    public void setScale(float scaleX, float scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    @Override
    public float getScaleX() {
        return scaleX;
    }

    @Override
    public float getScaleY() {
        return scaleY;
    }

    @Override
    public int getWidth() {
        return (int) (getImage().width * scaleX);
    }

    @Override
    public int getHeight() {
        return (int) (getImage().height * scaleY);
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
        if (!reflected) {
            p.pushMatrix();
            p.translate(x, y);
            p.scale(scaleX, scaleY);
            p.rotate(angle);
            p.image(getImage(), 0, 0);
            p.popMatrix();
        } else {
            p.pushMatrix();
            p.scale(-1,1);


            if (reversed) {
                p.translate(-x, y);
                p.scale(scaleX, scaleY);
                p.rotate((float) (Math.PI - angle));
            } else {
                p.translate(-(x + getWidth()), y);
                p.scale(scaleX, scaleY);
                p.rotate(angle);
            }
            p.image(getImage(), 0, 0);
            p.popMatrix();
        }
    }

    @Override
    public abstract PImage getImage();

    @Override
    public abstract Object clone();


}
