package kchandra423.kImages;

import org.imgscalr.Scalr;
import processing.core.PApplet;
import processing.core.PConstants;

public class MovableKImage extends MovableObject implements Scalable {
    public enum ImageMode {
        CENTER(PConstants.CENTER), CORNER(PConstants.CORNER);
        private final int val;

        ImageMode(int val) {
            this.val = val;
        }

        public int getVal() {
            return val;
        }
    }

    protected final ImageMode mode;
    protected final KImage image;

    public MovableKImage(KImage kImage) {
        this(kImage, 0, 0, 0, false, ImageMode.CENTER);
    }

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

    public void draw(PApplet p) {
        p.pushMatrix();
        if (!reflected) {
            p.translate(x, y);
            p.rotate(angle);
        } else {
            p.scale(-1, 1);
            p.translate(-x, y);
            p.rotate(-angle);

            if (mode == ImageMode.CORNER)
                p.translate(-getWidth(), 0);
        }
        p.pushStyle();
        p.imageMode(mode.getVal());
        image.draw(p, 0, 0);
        p.popStyle();
        p.popMatrix();
    }

    public KImage getImage() {
        return image;
    }

    public MovableKImage copy() {
        return new MovableKImage(image.copy(), x, y, angle, reflected, mode);
    }
}
