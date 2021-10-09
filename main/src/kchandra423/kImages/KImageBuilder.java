package kchandra423.kImages;

import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
import destiny.gif.GifDecoder;
import processing.core.PImage;

import javax.imageio.ImageIO;
import javax.media.jai.PlanarImage;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class KImageBuilder {
    //[jpg, bmp, gif, png, wbmp, jpeg, tiff]



    public static KImage getKImage(String pathName) throws IOException {
        return getKImage(pathName, 0, 0, 0, false, false);
    }

    public static KImage getKImage(String pathName, int x, int y, float angle) throws IOException {
        return getKImage(pathName, x, y, angle, false, false);
    }
    /**
     * Creates a KImage from the image at the given path name
     *
     * @param pathName The path to the Image
     * @return A texture at 0,0 with the image at the pathname
     */
    public static KImage getKImage(String pathName, float x, float y, float angle, boolean reflected, boolean reversed) throws IOException {
        String extension = getExtension(pathName);
        switch (extension) {
            case "gif":
                Frame[] frames = getFrames(pathName);
                return new KGif(frames, x, y, angle, reflected, reversed);
            case "jpg":
            case "jpeg":
            case "png":
            case "wbmp":
            case "bmp": {
                BufferedImage img = ImageIO.read(new File(pathName));
                return new TextureImage(new PImage(img), x, y, angle, reflected, reversed);
            }
            case "tiff": {
                File file = new File(pathName);

                SeekableStream stream = new FileSeekableStream(file);
                String[] names = ImageCodec.getDecoderNames(stream);
                ImageDecoder decoder = ImageCodec.createImageDecoder(names[0], stream, null);

                RenderedImage im = decoder.decodeAsRenderedImage(0);
                BufferedImage img = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
                return new TextureImage(new PImage(img), x, y, angle, reflected, reversed);
            }
        }
        return null;
    }

    private static String getExtension(String path) throws IOException {
        int index = path.lastIndexOf(".");
        if (index == -1) {
            throw new IOException();
        }
        return path.substring(index + 1).toLowerCase();
    }

    private static Frame[] getFrames(String pathName) throws IOException {
        GifDecoder.GifImage gif = GifDecoder.read(new FileInputStream(pathName));
        Frame[] answer = new Frame[gif.getFrameCount()];
        for (int i = 0; i < gif.getFrameCount(); i++) {
            answer[i] = new Frame(gif.getFrame(i), gif.getDelay(i));
        }
        return answer;
    }
}