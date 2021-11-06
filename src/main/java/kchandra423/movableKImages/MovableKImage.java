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

import kchandra423.kImages.KImage;
import kchandra423.kImages.Scalable;
import org.imgscalr.Scalr;
import processing.core.PApplet;
import processing.core.PConstants;

/**
 * Adds basic physics and transformation to KImages.
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see kchandra423.movableKImages.Movable
 */
public class MovableKImage extends MovableObject implements Scalable {
    /**
     * Determines how Images are drawn and rotated.
     * @author Kumar Chandra
     * @version 1.0
     */
    public enum ImageMode {
        /**
         * Center images are drawn from the center of the image and are rotated about the center
         */
        CENTER(PConstants.CENTER),
        /**
         * Corner images are drawn from the top left corner of the image and are also rotated about this point
         */
        CORNER(PConstants.CORNER);
        private final int val;

        ImageMode(int val) {
            this.val = val;
        }

        /**
         * Returns the {@link PConstants} of this Image mode. (Center for center and Corner for Corner)
         * @return An int for the corresponding PConstant value
         * @see PConstants
         */
        public int getVal() {
            return val;
        }
    }

    private final ImageMode mode;
    private final KImage image;

    /**
     * Creates a new Movable KImage at 0,0 with angle 0, not reflected, and an ImageMode of corner
     * @param kImage The base KImage
     */
    public MovableKImage(KImage kImage) {
        this(kImage, 0, 0, 0, false, ImageMode.CORNER);
    }

    /**
     * Creates a new Movable Kimage at x,y, with angle angle, the given reflection and Image mode.
     * @param kImage The base KImage
     * @param x The initial x location
     * @param y The initial y location
     * @param angle The initial angle in radians
     * @param reflected Whether this image is reflected
     * @param mode The image mode of this object
     */
    public MovableKImage(KImage kImage, float x, float y, float angle, boolean reflected, ImageMode mode) {
        super(x, y, angle, reflected);
        image = kImage;
        this.mode = mode;
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public void resize(int w, int h, Scalr.Method method, Scalr.Mode mode) {
        image.resize(w, h, method, mode);
    }

    /**
     * Draws this image onto the given PApplet with specified transformations
     * @param p The PApplet to draw onto
     */
    public void draw(PApplet p) {
        p.pushMatrix();
        if (!isReflected()) {
            p.translate(getX(), getY());
            p.rotate(getAngle());
        } else {
            p.scale(-1, 1);
            p.translate(-getX(), getY());
            p.rotate(-getAngle());

            if (mode == ImageMode.CORNER)
                p.translate(-getWidth(), 0);
        }
        p.pushStyle();
        p.imageMode(mode.getVal());
        image.draw(p, 0, 0);
        p.popStyle();
        p.popMatrix();
    }

    /**
     * Returns the base KImage used by this object
     * @return The base KImage
     */
    public KImage getImage() {
        return image;
    }

    /**
     * Returns the ImageMode of this object
     * @return The base ImageMode
     */
    public ImageMode getMode(){
        return mode;
    }

    /**
     * Copies the attributes of this MovableKImage onto a new MovableKImage
     * @return A copy of this Movable KImage
     */
    public MovableKImage copy() {
        return new MovableKImage(image.copy(), getX(), getY(), getAngle(), isReflected(), mode);
    }
}
