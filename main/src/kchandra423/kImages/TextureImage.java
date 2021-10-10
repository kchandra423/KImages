package kchandra423.kImages;

import processing.core.PImage;

class TextureImage extends AbstractKImage {
    private final PImage image;

    TextureImage(PImage image, float x, float y, float angle, float scaleX, float scaleY, boolean reflected, boolean reversed) {
        super(x, y, angle, scaleX, scaleY, reflected, reversed);
        this.image = image;
    }

    @Override
    public PImage getImage() {
        return image;
    }

    @Override
    public Object clone() {
        return new TextureImage(image.copy(), getX(), getY(), getAngle(), getScaleX(), getScaleY(), isReflected(), isReversed());
    }
}
