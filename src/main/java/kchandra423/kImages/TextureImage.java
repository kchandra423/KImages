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
import processing.core.PImage;

import java.awt.image.BufferedImage;

class TextureImage extends AbstractKImage {
    private final PImage image;
    private PImage scaledCopy;

    TextureImage(PImage image, float x, float y, float angle, boolean reflected, boolean reversed) {
        super(x, y, angle, reflected, reversed);
        this.image = image;
        this.scaledCopy = image;
    }

    @Override
    public void resize(int w, int h, Scalr.Mode mode, Scalr.Method method) {
        BufferedImage resized = Scalr.resize((BufferedImage) image.getImage(), method, mode, w, h);
        scaledCopy = new PImage(resized);
    }

    @Override
    public PImage getImage() {
        return scaledCopy;
    }

    @Override
    public Object clone() {
        return new TextureImage(image.copy(), getX(), getY(), getAngle(), isReflected(), isReversed());
    }
}
