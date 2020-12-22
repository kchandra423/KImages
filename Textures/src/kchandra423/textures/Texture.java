package kchandra423.textures;
import processing.core.PApplet;
public abstract class Texture{
	private float x,y;
	
	protected Texture(float x, float y) {
		this.x=x;
		this.y=y;
	}
	public abstract void resize(int w, int h);
	public abstract float getWidth();
	public abstract float getHeight();
	public abstract void draw(PApplet p);
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x=x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public void shiftX(float shiftX) {
		x+=shiftX;
	}
	public void shiftY(float shiftY) {
		y+=shiftY;
	}
	public abstract void fadeOut();
	
	public abstract void fadeIn();
	public static class TextureBuilder{
		public static Texture getTexture(String pathName) {
			if(pathName.substring(pathName.length()-4, pathName.length()).equals(".gif")){
				return new PGif(pathName);
			}else {
				return new TextureImage(pathName);
			}
		}
		public static Texture getTexture(String pathName,float x, float y) {
			
			if(pathName.substring(pathName.length()-4, pathName.length()).equals(".gif")){
				return new PGif(pathName,x,y);
			}else {
				return new TextureImage(pathName,x,y);
			}
		}
	}
	
}
