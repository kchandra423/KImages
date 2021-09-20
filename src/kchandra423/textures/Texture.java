/*
MIT License

Copyright (c) 2020 Kumar.s.Chandra

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
package kchandra423.textures;
import processing.core.PApplet;
// import processing.core.PApplet;
/**
 * Represents an image that can be moved around and resized. Supports Gif, jpeg, and png images.
 * Gifs will be properly animated. Uses processing to draw.
 * @author Kumar Chandra
 *
 */
public abstract class Texture{
	private float x,y;
	/**
	 * Used by subclasses to create a new Texture
	 * @param x the x coordinate of the texture
	 * @param y the y coordinate of the texture
	 */
	protected Texture(float x, float y) {
		this.x=x;
		this.y=y;
	}
	/**
	 * Resizes the textures image to the given width and height
	 * @param w new width of texture
	 * @param h new height of texture
	 */
	public abstract void resize(int w, int h);
	/**
	 * Returns the current width of the image
	 * @return
	 */
	public abstract float getWidth();
	/**
	 * Returns the current height of the image
	 * @return
	 */
	public abstract float getHeight();
	/**
	 * Draws the texture onto the given PApplet
	 * @param p The given PApplet to be drawn to
	 */
	public abstract void draw(PApplet p);
	/**
	 * Returns the current x coordinate of the texture
	 * @return current x coordinate
	 */
	public float getX() {
		return x;
	}
	/**
	 * Sets the current x coordinate to the given x coordinate
	 * @param x The new x coordinate
	 */
	public void setX(float x) {
		this.x=x;
	}
	/**
	 * Returns the current y coordinate of the texture
	 * @return current y coordinate
	 */
	public float getY() {
		return y;
	}
	/**
	 * Sets the current y coordinate to the given y coordinate
	 * @param y The new y coordinate
	 */
	public void setY(float y) {
		this.y = y;
	}
	/**
	 * Shifts the current texture a given amount. Positive values shift to the right,
	 * negative values shift to the left 
	 * @param shiftX the amount that the shape will be shifted in the x direction
	 */
	public void shiftX(float shiftX) {
		x+=shiftX;
	}
	/**
	 * Shifts the current texture a given amount. Positive values shift to the right,
	 * negative values shift to the left 
	 * @param shiftY the amount that the shape will be shifted in the x direction
	 */
	public void shiftY(float shiftY) {
		y+=shiftY;
	}
	/**
	 * Fades the image until it is transparent
	 */
	public abstract void fadeOut();
	/**
	 * Fades the image from transparent to opaque
	 */
	public abstract void fadeIn();
	/**
	 * Used to create a Texture given its pathname
	 * Supports gifs, jpegs, and pngs
	 * @author Kumar Chandra
	 *
	 */
	public static class TextureBuilder{
		/**
		 * Creates a Texture from the image at the given path name
		 * @param pathName The path to the Image
		 * @return A texture at 0,0 with the image at the pathname
		 */
		public static Texture getTexture(String pathName) {
			if(pathName.substring(pathName.length()-4, pathName.length()).equals(".gif")){
				return new PGif(pathName);
			}else {
				return new TextureImage(pathName);
			}
		}
		/**
		 * Creates a Texture from the image at the given path name
		 * @param pathName The path to the Image
		 * @param x The starting x coordinate of the texture
		 * @param y The starting y coordinate of the texture
		 * @return A texture at x,y with the image at the pathname
		 */
		public static Texture getTexture(String pathName,float x, float y) {
			
			if(pathName.substring(pathName.length()-4, pathName.length()).equals(".gif")){
				return new PGif(pathName,x,y);
			}else {
				return new TextureImage(pathName,x,y);
			}
		}
	}
	
}
