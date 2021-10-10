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
public interface KImage extends Cloneable{

    /**
     * Resizes the textures image to the given width and height
     *
     * @param w new width of texture in pixels
     * @param h new height of texture in pixels
     */
    void resize(int w, int h);

    void scale(float stretchX, float stretchY);

    void setScale(float scaleX, float scaleY);

    float getScaleX();
    float getScaleY();

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
