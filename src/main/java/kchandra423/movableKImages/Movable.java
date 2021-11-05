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
 * Defines all the methods a movable object should have.
 *
 * @author Kumar Chandra
 * @version 1.0
 */
public interface Movable {
    /**
     * Returns this object current x coordinate.
     *
     * @return This object's current x coordinate.
     */
    float getX();

    /**
     * Return this objects current y coordinate
     *
     * @return This object's current y coordinate.
     */
    float getY();

    /**
     * Returns this object current angle in radians.
     *
     * @return This object's angle in radians
     */
    float getAngle();

    /**
     * Returns whether this object has been reflected.
     *
     * @return Whether this object is reflected
     */
    boolean isReflected();

    /**
     * Shifts this object's x and y coordinates by the given amount
     *
     * @param delx The x coord shift
     * @param dely THe y coord shift
     */
    default void translate(float delx, float dely) {
        moveTo(getX() + delx, getY() + dely);
    }

    /**
     * Moves this object to the given x and y coords
     *
     * @param x The new x coord
     * @param y The new y coord
     */
    void moveTo(float x, float y);

    /**
     * Rotates this object by the given amount in radians. Rotates CCW if negative.
     *
     * @param angle The amount to rotate this object by in radians
     */
    default void rotate(float angle) {
        setAngle(getAngle() + angle);
    }

    /**
     * Sets the angle of this object in radians
     *
     * @param angle The angle of this object in radians.
     */
    void setAngle(float angle);

    /**
     * Sets whether this object is reflected. False for normal orientation,
     * true for reflected.
     *
     * @param isReflected Whether this object is reflected
     */
    void setReflected(boolean isReflected);
}
