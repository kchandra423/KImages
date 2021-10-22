package kchandra423.kImages;


import at.dhyan.open_imaging.GifDecoder;
import com.sun.media.jai.codec.FileSeekableStream;
import com.sun.media.jai.codec.ImageCodec;
import com.sun.media.jai.codec.ImageDecoder;
import com.sun.media.jai.codec.SeekableStream;
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

    public static KImage getKImage(String pathName, float x, float y, float angle) throws IOException {
        return getKImage(pathName, x, y, angle, false, false);
    }

//    public static KImage getKImage(String pathName, float x, float y, float angle, float scaleX, float scaleY) throws IOException {
//        return getKImage(pathName, x, y, angle, scaleX, scaleY, false, false);
//    }

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
                // Stolen from this guy's blog
                // https://pinnau.blogspot.com/2016/06/java-load-tiff-images-into-bufferedimage.html
                File file = new File(pathName);

                SeekableStream stream = new FileSeekableStream(file);
                String[] names = ImageCodec.getDecoderNames(stream);
                ImageDecoder decoder = ImageCodec.createImageDecoder(names[0], stream, null);

                RenderedImage im = decoder.decodeAsRenderedImage(0);

                BufferedImage img = PlanarImage.wrapRenderedImage(im).getAsBufferedImage();
                return new TextureImage(new PImage(img), x, y, angle, reflected, reversed);
            }
            default:
                throw new IOException("File type \"" + extension + "\" not supported");
        }
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
            answer[i] = new Frame(new PImage(gif.getFrame(i)), gif.getDelay(i));
        }
        return answer;
    }
}