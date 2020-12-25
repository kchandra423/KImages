package Textures;

import org.junit.Test;
import static org.junit.Assert.*;
public class please {
	Texture test1= Texture.TextureBuilder.getTexture("src/test/resources/cat.gif",10,15);
	Texture test2= Texture.TextureBuilder.getTexture("src/test/resources/staticCat.jpeg",20,25);
	@Test public void testLocation() {
        assertTrue(test1.getX()==10);
        assertTrue(test1.getY()==15);
        assertTrue(test2.getX()==20);
        assertTrue(test2.getY()==25);
    }
	@Test public void testMovement() {
       test1.shiftX(10);
       test2.shiftY(-5);
		assertTrue(test1.getX()==20);
        assertTrue(test2.getY()==20);
        test1.setY(100);
        test2.setX(0);
		assertTrue(test1.getY()==100);
        assertTrue(test2.getX()==0);
        
    }
	@Test public void testDimensions() {
			assertTrue(test1.getWidth()==240);
	        assertTrue(test1.getHeight()==152);
	        assertTrue(test2.getWidth()==600);
	        assertTrue(test2.getHeight()==375);
	        
	 }
	@Test public void testResize() {
		test1.resize(100, 100);
		test2.resize(100, 100);
		assertTrue(test1.getWidth()==100);
        assertTrue(test1.getHeight()==100);
        assertTrue(test2.getWidth()==100);
        assertTrue(test2.getHeight()==100);
 }
	
	
}
