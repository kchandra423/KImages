import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import processing.core.PApplet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawingSurface extends PApplet {

    ArrayList<KImage> examples = new ArrayList<>();

    public void setup() {
        File f = new File("res");
        for (File img :
                f.listFiles()) {
            try {
                KImage image = KImageBuilder.getKImage(img.getAbsolutePath());
                image.resize(150, 150);
                examples.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw() {
        for (int i = 0; i < examples.size(); i++) {
            examples.get(i).moveTo(i % 4 * 150, (i / 4) * 150);
            examples.get(i).draw(this);
        }
    }


}
