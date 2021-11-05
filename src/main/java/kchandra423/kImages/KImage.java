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
 * A basic class for representing an Image. Supports all files types
 * supported by java advanced imaging, and animates gifs
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see PApplet
 */
public interface KImage extends Scalable {
    default int getWidth() {
        return getImage().width;
    }

    default int getHeight() {
        return getImage().height;
    }

    /**
     * Draws this image at the given location on the given PApplet.
     *
     * @param p The PApplet to draw on
     * @param x The x location to draw at
     * @param y The y location to draw at
     */
    default void draw(PApplet p, float x, float y) {
        p.image(getImage(), x, y);
    }

    /**
     * Gets the base Image used by this object
     *
     * @return The base image
     */
    PImage getImage();

    /**
     * Returns a copy of this image
     *
     * @return A copy of this KImage
     */
    KImage copy();

}
