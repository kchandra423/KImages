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
	private int width,height;
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
	private Frame[] getFrames(String pathName) throws FileNotFoundException, IOException {
		GifImage gif= GifDecoder.read(new FileInputStream(pathName));
		width=gif.getWidth();
		height=gif.getHeight();
		Frame[] answer= new Frame[gif.getFrameCount()];
		for (int i=0;i<gif.getFrameCount();i++) {
			answer[i]= new Frame(gif.getFrame(i),gif.getDelay(i));
		}
		return answer;
	}
	public void draw(PApplet p) {
		PImage curImage=frames[curFrame].getImage();
		curImage.resize(width, height);
		if(fader!=null) {
			p.pushMatrix();
			fader.draw(p);
			p.image(curImage, getX(), getY());
			p.popMatrix();
		}else {
		p.image(curImage, getX(), getY());
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
		// TODO Auto-generated method stub
		width=w;
		height=h;
	}
	@Override
	public float getWidth() {
		// TODO Auto-generated method stub
		return width;
	}
	@Override
	public float getHeight() {
		// TODO Auto-generated method stub
		return height;
	}
	@Override
	public void fadeOut() {
		// TODO Auto-generated method stub
		fader=new Fader(255,0,0.2f);
		fader.start();
	}
	@Override
	public void fadeIn() {
		// TODO Auto-generated method stub
		fader=new Fader(0,255,0.2f);
		fader.start();
	}
}
