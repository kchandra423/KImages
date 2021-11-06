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
import org.jetbrains.annotations.Nullable;

/**
 * Defines a set of methods that any Scalable image should have; scaling and resizing, as well as the ability to get
 * their current width and height. Please actually read the {@link Scalr} documentation, it will probably answer most of
 * your questions in regard to how things Scale.
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see Scalr
 */
public interface Scalable {
    /**
     * Returns the width of this Image.
     *
     * @return The width in pixels
     */
    int getWidth();

    /**
     * Returns the height of this Image
     *
     * @return The height in pixels
     */
    int getHeight();

    /**
     * Scales this image along the x and y axis by the specific amounts.
     * Uses {@link Scalr.Method} Automatic and {@link Scalr.Mode} Automatic
     *
     * @param stretchX The amount to stretch the x axis of this image by.
     * @param stretchY The amount to stretch the y axis of this image by.
     */
    default void scale(float stretchX, float stretchY) {
        scale(stretchX, stretchY, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC);
    }

    /**
     * Resizes this image to the given proportions.
     * Uses {@link Scalr.Method} Automatic and {@link Scalr.Mode} Automatic
     *
     * @param w The new target width
     * @param h The new target height
     */
    default void resize(int w, int h) {
        resize(w, h, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC);
    }

    /**
     * Scales this image along the x and y axis by the specific amounts. Using the given scalr methods and modes.
     *
     * @param stretchX The amount to stretch the x axis of this image by.
     * @param stretchY The amount to stretch the y axis of this image by.
     * @param method   The method to scale the image by. If null, using the Automatic Scalr method
     * @param mode     The mode to scale the image with. If null, using the Automatic Scalr mode
     */
    default void scale(float stretchX, float stretchY, @Nullable Scalr.Method method, @Nullable Scalr.Mode mode) {
        resize((int) (getWidth() * stretchX), (int) (getHeight() * stretchY), method, mode);
    }

    /**
     * Resizes this image to the given proportions, using the given Scalr methods and modes.
     *
     * @param w      The new target width
     * @param h      The new target height
     * @param method The method to scale the image by. If null, using the Automatic Scalr method
     * @param mode   The mode to scale the image with. If null, using the Automatic Scalr mode
     */
    void resize(int w, int h, @Nullable Scalr.Method method, @Nullable Scalr.Mode mode);

}
