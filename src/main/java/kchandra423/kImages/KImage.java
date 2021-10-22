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

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Represents an image that can be moved around and resized. Supports Gif, jpeg, and png images.
 * Gifs will be properly animated. Uses processing to draw.
 *
 * @author Kumar Chandra
 * @see PImage
 */
public interface KImage extends Cloneable {

    /**
     * Resizes the textures image to the given width and height
     *
     * @param w new width of texture in pixels
     * @param h new height of texture in pixels
     */
    void resize(int w, int h);

    void scale(float stretchX, float stretchY);

    /**
     * Returns the current width of the image
     *
     * @return Current width of the image
     */
    int getWidth();

    /**
     * Returns the current height of the image
     *
     * @return Current height of the image
     */
    int getHeight();

    void translate(float delx, float dely);

    float getX();

    float getY();

    void moveTo(float x, float y);

    void setAngle(float theta);

    float getAngle();

    boolean isReflected();

    boolean isReversed();

    void rotate(float theta);

    void reflect(boolean flag);

    void reverse(boolean flag);

    /**
     * Draws the texture onto the given PApplet
     *
     * @param p The given PApplet to be drawn to
     */
    void draw(PApplet p);

    PImage getImage();

    Object clone();


}
