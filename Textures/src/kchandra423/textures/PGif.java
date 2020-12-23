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
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import destiny.gif.GifDecoder;
import destiny.gif.GifDecoder.GifImage;
import processing.core.PApplet;
import processing.core.PImage;
//i should prob give credit to those gif decoder people, 
//but i literally dont even know how the contract works
class PGif extends Texture{
	private Fader fader;
	private Frame[] frames;
	private int curFrame;
	private long lastTime;
	PGif(String pathname) {
		super(0,0);
		fader=null;
		try {
			frames=getFrames(pathname);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastTime=System.currentTimeMillis();
		
	}
	PGif(String pathname, float x,float y) {
		super(x,y);
		fader=null;
		
		try {
			frames=getFrames(pathname);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lastTime=System.currentTimeMillis();
	}
	private Frame[] getFrames(String pathName) throws FileNotFoundException, IOException {
		GifImage gif= GifDecoder.read(new FileInputStream(pathName));
		Frame[] answer= new Frame[gif.getFrameCount()];
		for (int i=0;i<gif.getFrameCount();i++) {
			answer[i]= new Frame(gif.getFrame(i),gif.getDelay(i));
		}
		return answer;
	}
	public void draw(PApplet p) {
		if(fader!=null) {
			p.pushMatrix();
			fader.draw(p);
			p.image(frames[curFrame].getImage(), getX(), getY());
			p.popMatrix();
		}else {
		p.image(frames[curFrame].getImage(), getX(), getY());
		}
		advanceFrame();
		
	}
	private void advanceFrame() {
		long curTime= System.currentTimeMillis();
		//.getDelay return time in 100ths of a second
		if(curTime>=lastTime+frames[curFrame].getDelay()*10) {
			lastTime=curTime;
			curFrame++;
			if(curFrame>=frames.length) {
				curFrame=0;
			}
		}
	}
	@Override
	public void resize(int w, int h) {
		for(int i=0; i< frames.length;i++) {
			frames[i].getImage().resize(w, h);
		}
	}
	@Override
	public float getWidth() {
		return frames[curFrame].getImage().width;
	}
	@Override
	public float getHeight() {
		return frames[curFrame].getImage().height;
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
	private class Frame{
		private final PImage image;
		private final int delay;
		public Frame(BufferedImage image, int delay) {
			this.image= new PImage(image);
			this.delay=delay;
		}
		public PImage getImage() {
			return image;
		}
		public int getDelay() {
			return delay;
		}
	}
}
