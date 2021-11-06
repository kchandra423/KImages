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
package kchandra423.kColliders;

import kchandra423.kImages.KImage;
import kchandra423.movableKImages.MovableKImage;
import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

/**
 * This class adds collision detection to {@link MovableKImage}.
 * It has similar functionality to MovableKImages except it can
 * determine collisions with other KColliders and get it's bounding rectangle.
 *
 * <br><br>
 * <h3>Slow operations</h3>
 * <p>
 * Initializing a KCollider can be extremely slow especially depending on image size and area density.
 * The resizing/scaling operations also rescans the base image, making these operations equally slow,
 * and should be avoided as much as possible.
 *
 * <br><br>
 * <h3>Suggestions for faster runtimes</h3>
 * <p>
 * Due to the slow nature of this class, it is worth changing the area density to a higher value,
 * to give up a slight amount of accuracy for much faster load times. Preloading these images is also a good idea,
 * as making copys is a relatively quick operations. You can preload the image once initially and then store them in
 * the {@link KColliderContainer} to quickly retrieve images you know you will lose later.
 *
 * <br><br>
 * <h3>Explanation of Area Density</h3>
 * <p>
 * This class does collisions by creating {@link Area} objects from the KImages' pixel data. However, this is an O(n^2)
 * operation and with image's being commonly 3000 px wide and high, this can create very long load times.
 * To alleviate this, you can set the Area Density, which is essentially how many pixels you want to use as a square to
 * approximate the shape of the Image, causing it to load faster at the cost of precision. For example an Area Density of 1
 * just uses 1 by 1 squares. However, an area density of 4 would use 4 by 4 squares to approximate the shape of the Image.
 * This is much faster, and as long as you use a reasonably small area density in comparison to the size of the Image, there
 * should be negligible differences in functionality.
 * <br>
 * <b>Example</b>
 * <br>
 * Real shape:<br>
 * **__<br>
 * *_*_<br>
 * _**_<br>
 * ____<br>
 * Area Density of 1:<br>
 * **__<br>
 * *_*_<br>
 * _**_<br>
 * ____<br>
 * Area Density of 2:<br>
 * **__<br>
 * **__<br>
 * __**<br>
 * __**<br>
 *
 * <br><br>
 * <h3>Quirks</h3>
 * <p>
 * When creating collisions, transparent values are assumed to be not part of the image's shape. This means that
 * since JPEGS do not support transparent values, only gifs and pngs will actually have accurate collision detection.
 * This will be addressed in the future when I have more time to work on my open source projects that no one uses or reads.
 * <br><br>
 * When generating area's with an area density more than 1, the top left pixel of "area density x area density" square
 * is used to determine whether that square is part of the collision or not
 * <br><br>
 * "Hitboxes" are not just one shape, an Image's area can have multiple enclosed shapes.
 * Each will trigger a collision when touched.
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see MovableKImage
 * @see kchandra423.kImages.KImage
 * @see java.awt.geom.Area
 * @see kchandra423.kColliders.KColliderContainer
 */
public class KCollider extends MovableKImage {
    private static int defaultAreaDensity = 1;
    private KArea area;
    private Area mostRecentArea;
    private boolean upToDate;

    private static KArea loadArea(KImage image, int areaDensity) {
        //shouldn't happen but just double check
        if (areaDensity < 1) {
            throw new IllegalArgumentException("Density given is less than 1");
        }
        KArea area = new KArea();
        //prevent's images with areas bigger than themselves
        if (areaDensity > image.getWidth()) {
            areaDensity = image.getWidth();
        }
        if (areaDensity > image.getHeight()) {
            areaDensity = image.getHeight();
        }
        //basically just nearest neighbor, uses top left corner
        for (int x = 0; x < image.getWidth(); x += areaDensity) {
            for (int y = 0; y < image.getHeight(); y += areaDensity) {
                if (image.getImage().pixels[y * image.getWidth() + x] != 0) {
                    area.add(new Area(new Rectangle(x, y, areaDensity * image.getImage().pixelDensity, areaDensity * image.getImage().pixelDensity)));
                }
            }

        }
        //store density info
        area.setDensity(areaDensity);
        return area;
    }


    /**
     * Sets the default area density for all new KColliders created. See {@link KCollider} header comment for more information.
     * If the density specified is wider or taller than the width of the inage, the top left corner is still just used.
     *
     * @param density The area density of any KCollider created
     * @throws IllegalArgumentException Thrown if density is less than 1
     */
    public static void setDefaultAreaDensity(int density) {
        if (density < 1) {
            throw new IllegalArgumentException("Density given is less than 1");
        }
        defaultAreaDensity = density;
    }

    /**
     * Constructs a new KCollider with the given KImage. Default area density will be used.
     * Starts at 0,0 with an angle of 0, unreflected and a corner {@link MovableKImage.ImageMode}
     *
     * @param k The KImage to base the Collider's collision on
     */
    public KCollider(KImage k) {
        this(k, defaultAreaDensity);
    }

    /**
     * Constructs a new KCollider with the given KImage at the given density.
     * Starts at 0,0 with an angle of 0, unreflected and a corner {@link MovableKImage.ImageMode}
     *
     * @param k       The KImage to base the Collider's collision on
     * @param density The density to use for this collider.
     */
    public KCollider(KImage k, int density) {
        super(k);
        area = loadArea(k, density);
        update();
    }

    /**
     * Constructs a new KCollider with the given KImage at the default density.
     * Starts at x,y with an angle of angle, the given reflection and image modes
     *
     * @param k         The KImage to base collisions off of
     * @param x         The initial x location of the Collider
     * @param y         The initial y location of the Collider
     * @param angle     The angle of the Collider
     * @param reflected The initial reflection orientation of the Collider
     * @param mode      The image mode of the Collider
     */
    public KCollider(KImage k, float x, float y, float angle, boolean reflected, ImageMode mode) {
        this(k, x, y, angle, reflected, mode, defaultAreaDensity);
    }

    /**
     * Constructs a new KCollider with the given KImage at the given density.
     * Starts at x,y with an angle of angle, the given reflection and image modes
     *
     * @param k         The KImage to base collisions off of
     * @param x         The initial x location of the Collider
     * @param y         The initial y location of the Collider
     * @param angle     The angle of the Collider
     * @param reflected The initial reflection orientation of the Collider
     * @param mode      The image mode of the Collider
     * @param density   The density to use for this collider.
     */
    public KCollider(KImage k, float x, float y, float angle, boolean reflected, ImageMode mode, int density) {
        super(k, x, y, angle, reflected, mode);
        area = loadArea(k, density);
        update();
    }

    //to make copying faster
    private KCollider(KImage k, float x, float y, float angle, boolean reflected, ImageMode mode, KArea area) {
        super(k, x, y, angle, reflected, mode);
        this.area = area;
        update();
    }

    /*
     *
     * Transformations
     *
     */
    //need to make sure useless operations are not done so only changed states if necessary
    @Override
    public void translate(float delx, float dely) {
        if (delx != 0 || dely != 0) {
            super.translate(delx, dely);
            upToDate = false;
        }
    }

    @Override
    public void moveTo(float x, float y) {
        if (x != getX() || y != getY()) {
            super.moveTo(x, y);
            upToDate = false;
        }
    }

    @Override
    public void rotate(float angle) {
        if (angle != 0) {
            super.rotate(angle);
            upToDate = false;
        }
    }

    @Override
    public void setAngle(float angle) {
        if (angle != getAngle()) {
            super.setAngle(angle);
            upToDate = false;
        }
    }

    @Override
    public void setReflected(boolean isReflected) {
        if (isReflected() != isReflected) {
            super.setReflected(isReflected);
            upToDate = false;
        }
    }

    /*
     *
     * Collision Detection
     *
     */

    /**
     * Returns whether this Collider intersects with the other.
     *
     * @param other The other KCollider
     * @return True if the shapes intersect, false otherwise.
     */
    public boolean intersects(KCollider other) {
        if (!upToDate) {
            update();
        }
        Area myArea = (Area) mostRecentArea.clone();
        if (!other.upToDate) {
            other.update();
        }
        Area otherArea = other.mostRecentArea;
        myArea.intersect(otherArea);
        return !myArea.isEmpty();
    }

    /**
     * Returns a rectangle representing the boundaries of this area as the most
     * tight rectangle possible
     *
     * @return The most tightly bounding rectangle possible
     */
    public Rectangle getBounds() {
        if (!upToDate) {
            update();
        }
        return mostRecentArea.getBounds();
    }

    private void update() {
        upToDate = true;
        AffineTransform transform = new AffineTransform();
        if (!isReflected()) {
            transform.translate(getX(), getY());
            transform.rotate(getAngle());
        } else {
            transform.scale(-1, 1);
            transform.translate(-getX(), getY());
            transform.rotate(-getAngle());
            if (getMode() == ImageMode.CORNER)
                transform.translate(-getWidth(), 0);
        }
        if (getMode() == ImageMode.CENTER)
            transform.translate(-getWidth() / 2f, -getHeight() / 2f);

        mostRecentArea = area.createTransformedArea(transform);
    }

    @Override
    public void resize(int w, int h, Scalr.Method method, Scalr.Mode mode) {
        super.resize(w, h, method, mode);
        area = loadArea(getImage(), area.getDensity());
    }

    @Override
    public KCollider copy() {
        return new KCollider(getImage().copy(), getX(), getY(), getAngle(), isReflected(), getMode(), area);
    }

}
