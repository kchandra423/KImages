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

class Fader {
	//the concept for this class was basically created by nathaniel
//	private float curR,endingR;
//	private float curG,endingG;
//	private float curB,endingB;
//	private long timeToFade;//in milliseconds
//	private long timeOfLastCall;
	private float curAlpha;
	private float endingAlpha;
//	private float tintFadeSpeed;
//	private float alphaFadeSpeed;
	private float fadeSpeed;
	private boolean fading;
	Fader(float start, float end,
			float fadeSpeed) {
//		curR=start.getRed();
//		curG=start.getGreen();
//		curB=start.getBlue();
//		endingR=end.getRed();
//		endingG=end.getGreen();
//		endingB=end.getBlue();
//		this.tintFadeSpeed=tintFadeSpeed;
//		this.alphaFadeSpeed=alphaFadeSpeed;
		this.fadeSpeed=fadeSpeed;
		curAlpha=start;
		endingAlpha=end;
//		this.timeToFade=timeToFade;
		fading=false;
		
	}
	
	public void start() {
		fading=true;
		
	}
	public void stop() {
		fading=false;
	}

	public void draw(PApplet p) {
		if(fading) {
			p.tint(255,curAlpha);
			advance();
		}
//		timeOfLastCall=System.currentTimeMillis();
		
	}
	private void advance() {
		if(!fading) {
			return;
		}
		if(curAlpha==endingAlpha) {
			stop();
			return;
		}
//		if (curR < endingR) {
//			
//			curR += tintFadeSpeed;
//			
//			if (curR > endingR) {
//				
//				curR = endingR;
//				
//			}
//			
//		} else if(curR>endingR) {
//			
//			curR -= tintFadeSpeed;
//			
//			if (curR < endingR) {
//				
//				curR = endingR;
//				
//			}
//			
//		}
//		if (curG < endingG) {
//			
//			curG += tintFadeSpeed;
//			
//			if (curG > endingG) {
//				
//				curG = endingG;
//				
//			}
//			
//		} else if(curG>endingG){
//			
//			curG -= tintFadeSpeed;
//			
//			if (curG < endingG) {
//				
//				curG = endingG;
//				
//			}
//			
//		}
//		if (curB < endingB) {
//			
//			curB += tintFadeSpeed;
//			
//			if (curB > endingB) {
//				
//				curB = endingB;
//				
//			}
//			
//		} else if(curB>endingB) {
//			
//			curB -= tintFadeSpeed;
//			
//			if (curB < endingB) {
//				
//				curB = endingB;
//				
//			}
//			
//		}
		if (curAlpha < endingAlpha) {
			
			curAlpha += fadeSpeed;
			
			if (curAlpha > endingAlpha) {
				
				curAlpha = endingAlpha;
				
			}
			
		} else if(curAlpha>endingAlpha) {
			
			curAlpha -= fadeSpeed;
			
			if (curAlpha < endingAlpha) {
				
				curAlpha = endingAlpha;
				
			}
			
		}
		
	}
	
	
}
