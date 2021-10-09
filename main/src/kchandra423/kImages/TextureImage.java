package kchandra423.kImages;

import kchandra423.kImages.AbstractKImage;
import processing.core.PImage;

class TextureImage extends AbstractKImage {
    private final PImage image;

    TextureImage(PImage image, float x, float y, float angle, boolean reflected, boolean reversed) {
        super(x, y, angle, reflected, reversed);
        this.image = image;
    }

    @Override
    public PImage getImage() {
        return image;
    }


    @Override
    public void resize(int w, int h) {
        image.resize(w, h);
    }
}
