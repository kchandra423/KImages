package tests;

import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;

import java.io.File;
import java.io.IOException;

public class Tests {
    public static KImage getRandomImage() {
        File dir = new File("src/test/resources");
        File[] images = dir.listFiles();
        File chosen = images[(int) (Math.random() * images.length)];
        try {
            return KImageBuilder.getKImage(chosen.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
