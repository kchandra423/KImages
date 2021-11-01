package kchandra423.kImages;

import at.dhyan.open_imaging.GifDecoder;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class KImageBuilder {
    public static KImage getKImage(String pathName) throws IOException {
        String extension = getExtension(pathName);
        //wbmp
        //bmp
        //pcx
        //pnm
        //tiff
        //gif
        //jpg
        //jpeg
        //png
        //nef
        //dng
        if (!Files.exists(Paths.get(pathName))) {
            throw new FileNotFoundException("File path does not exist!");
        }
        if ("gif".equals(extension)) {
            Frame[] frames = getFrames(pathName);
            return new KGif(frames);
        }
        try {
            BufferedImage img = ImageIO.read(new File(pathName));
            return new StaticImage(new PImage(img));
        } catch (NullPointerException e) {
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
