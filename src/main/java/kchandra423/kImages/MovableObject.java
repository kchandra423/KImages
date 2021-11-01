package kchandra423.kImages;

class MovableObject implements Movable {
    protected float x, y, angle;
    protected boolean reflected;

    public MovableObject() {
        this(0, 0, 0, false);
    }

    public MovableObject(float x, float y, float angle, boolean reflected) {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.reflected = reflected;
    }

    /*
     *
     * Getters
     *
     */

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getAngle() {
        return angle;
    }

    public boolean isReflected() {
        return reflected;
    }

    /*
     *
     * Transformations
     *
     */

    public void translate(float delx, float dely) {
        x += delx;
        y += dely;
    }

    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void rotate(float angle) {
        this.angle += angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setReflected(boolean isReflected) {
        this.reflected = isReflected;
    }
}
