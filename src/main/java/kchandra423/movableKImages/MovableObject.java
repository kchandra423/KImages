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
package kchandra423.movableKImages;

/**
 * A basic implementation of the {@link Movable} interface
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see Movable
 */
class MovableObject implements Movable {
    private float x, y, angle;
    private boolean reflected;

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



    public void moveTo(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setReflected(boolean isReflected) {
        this.reflected = isReflected;
    }
}
