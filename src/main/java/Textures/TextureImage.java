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
package Textures;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import processing.core.PApplet;
import processing.core.PImage;
class TextureImage extends Texture{
	private PImage image;
	private Fader fader;
	TextureImage(String pathName) {
		super(0, 0);
		fader=null;
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(pathName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image=new PImage(img);

	}
	TextureImage(String pathName,float x, float y) {
		super(x, y);
		fader=null;
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(pathName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		image=new PImage(img);
		
	}

	@Override
	public void draw(PApplet p) {
		if(fader!=null) {
			p.pushMatrix();
			fader.draw(p);
			p.image(image, getX(), getY());
			p.popMatrix();
		}else {
		p.image(image, getX(), getY());
		}
	}

	@Override
	public void resize(int w, int h) {
		image.resize(w, h);
		
	}

	@Override
	public float getWidth() {
		return image.width;
	}

	@Override
	public float getHeight() {
		return image.height;
	}
	@Override
	public void fadeOut() {
		fader=new Fader(255,0,0.2f);
		fader.start();
	}
	@Override
	public void fadeIn() {
		fader=new Fader(0,255,0.2f);
		fader.start();
	}
	
}
