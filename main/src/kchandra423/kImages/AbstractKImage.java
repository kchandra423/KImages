package kchandra423.kImages;

import processing.core.PApplet;

abstract class AbstractKImage implements KImage {
    private float x;
    private float y;
    private float angle;
    private boolean reflected;
    private boolean reversed;

    protected AbstractKImage(float x, float y, float angle, boolean reflected, boolean reversed) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.reflected = reflected;
        this.reversed = reversed;
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
        if (!reflected) {
            p.pushMatrix();
            p.translate(x, y);
            p.rotate(angle);
            p.image(getImage(), 0, 0);
            p.popMatrix();
        } else {
            p.pushMatrix();
            p.scale(-1, 1);

            if (reversed) {
                p.translate(-x, y);
                p.rotate((float) (Math.PI - angle));
            } else {
                p.translate(-(x + getWidth()), y);
                p.rotate(angle);
            }
            p.image(getImage(), 0, 0);
            p.popMatrix();
        }
    }

}
