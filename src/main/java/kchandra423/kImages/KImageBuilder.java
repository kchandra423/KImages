/*
MIT License

Copyright (c) 2021 Kumar Chandra

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

/**
 * Defines a way to create KImages based on their path name, as they cannot be directly instantiated. This is done to deal
 * with the many file types supported. Supports all files types supported by Java Advanced Images, and animates Gifs.
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see GifDecoder
 * @see ImageIO
 */
public class KImageBuilder {
    /**
     * Creates a KImage from its pathname. This library supports all Images supported by Java Advanced Imaging. While
     * the specific file types supported vary drastically based on OS and OS version, but on my m1 macbook air I've found it
     * works with these file types so far (Although you should probably just look through the repo's documentation for more details)
     * <br>
     * <ul>
     *   <li>wbmp</li>
     *   <li>bmp</li>
     *   <li>pcx</li>
     *   <li>pnm</li>
     *   <li>tiff</li>
     *   <li>gif</li>
     *   <li>jpg</li>
     *   <li>jpeg</li>
     *   <li>png</li>
     *   <li>nef</li>
     *   <li>dng</li>
     * </ul>
     * Some of these files are more useful to support than others, but it's good to have a large amount to choose from.
     *
     * @param pathName The pathname to this image
     * @return A KImage created from the Image file
     * @throws IOException           If the file is not supported by Java Advanced imaging
     * @throws FileNotFoundException If the file path does not exist
     */
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
