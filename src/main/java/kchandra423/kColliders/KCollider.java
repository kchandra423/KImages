package kchandra423.kColliders;

import kchandra423.kImages.KImage;
import kchandra423.kImages.MovableKImage;
import org.imgscalr.Scalr;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public class KCollider extends MovableKImage {
    private static int defaultAreaDensity = 2;
    private KArea area;
    private Area mostRecentArea;
    private boolean upToDate;

    private static KArea loadArea(KImage image, int areaDensity) {
        if (areaDensity < 1) {
            throw new IllegalArgumentException("Density given is less than 1");
        }
        KArea area = new KArea();
        for (int x = 0; x < image.getWidth(); x += areaDensity) {
            for (int y = 0; y < image.getHeight(); y += areaDensity) {
                if (image.getImage().pixels[y * image.getWidth() + x] != 0) {
                    area.add(new Area(new Rectangle(x, y, areaDensity * image.getImage().pixelDensity, areaDensity * image.getImage().pixelDensity)));
                }
            }

        }
        area.setDensity(areaDensity);
        return area;
    }


    /**
     * Alters the precision of areas created by this class. An area density of 1 indicates that areas will be precise to the pixel.
     * An area density will indicate that areas could be up to 3 pixels off, as the area will be 4 pixels.
     * Ex)
     * * *
     * **
     * (Pixel density of 1 approximates as)
     * * *
     * **
     * (Pixel density of 2 approximates as)
     * **
     * ****
     * **
     * While increasing this value decreases the precision of the KCollider at a squared rate,
     * doing so also reduces time to load images at a squard rate.
     *
     * @param density The new density of any KCollider created
     */
    public static void setDefaultAreaDensity(int density) {
        if (density < 1) {
            throw new IllegalArgumentException("Density given is less than 1");
        }
        defaultAreaDensity = density;
    }


    public KCollider(KImage k) {
        this(k, defaultAreaDensity);
    }

    public KCollider(KImage k, int density) {
        super(k);
        area = loadArea(k, density);
        update();
    }

    public KCollider(KImage k, float x, float y, float angle, boolean reflected, ImageMode mode) {
        this(k, x, y, angle, reflected, mode, defaultAreaDensity);
    }

    public KCollider(KImage k, float x, float y, float angle, boolean reflected, ImageMode mode, int density) {
        super(k, x, y, angle, reflected, mode);
        area = loadArea(k, density);
        update();
    }

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
    @Override
    public void translate(float delx, float dely) {
        if (delx != 0 || dely != 0) {
            super.translate(delx, dely);
            upToDate = false;
        }
    }

    @Override
    public void moveTo(float x, float y) {
        if (x != this.x || y != this.y) {
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
        if (angle != this.angle) {
            super.setAngle(angle);
            upToDate = false;
        }
    }

    @Override
    public void setReflected(boolean isReflected) {
        if (reflected != isReflected) {
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
     * Returns whether this shape's area intersects the other shapes area.
     *
     * @param other The other KCollider
     * @return Whether the shapes intersect
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
        if (!reflected) {
            transform.translate(x, y);
            transform.rotate(angle);
        } else {
            transform.scale(-1, 1);
            transform.translate(-x, y);
            transform.rotate(-angle);
            if (mode == ImageMode.CORNER)
                transform.translate(-getWidth(), 0);
        }
        if (mode == ImageMode.CENTER)
            transform.translate(-getWidth() / 2f, -getHeight() / 2f);

        mostRecentArea = area.createTransformedArea(transform);
    }

    @Override
    public void resize(int w, int h, Scalr.Method method, Scalr.Mode mode) {
        super.resize(w, h, method, mode);
        area = loadArea(image, area.getDensity());
    }


    public KCollider copy() {
        return new KCollider(image.copy(), x, y, angle, reflected, mode, area);
    }

}
