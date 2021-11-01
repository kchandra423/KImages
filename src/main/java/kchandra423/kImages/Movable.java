package kchandra423.kImages;

interface Movable {
    float getX();

    float getY();

    float getAngle();

    boolean isReflected();

    void translate(float delx, float dely);

    void moveTo(float x, float y);

    void rotate(float angle);

    void setAngle(float angle);

    void setReflected(boolean isReflected);
}
