import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import processing.core.PApplet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawingSurface extends PApplet {

    ArrayList<KImage> examples = new ArrayList<>();

    //    KImage k;
    public void setup() {
        File f = new File("res");
        for (File img :
                f.listFiles()) {
            try {
                KImage image = KImageBuilder.getKImage(img.getAbsolutePath());
                image.resize(250, 250);
                examples.add(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw() {
//        k.draw(this);
        background(200);
        for (int i = 0; i < examples.size(); i++) {
            float x = i % 3 * 250;
            float y = (i / 3) * 250;
            examples.get(i).moveTo(x, y);
            examples.get(i).draw(this);
        }
    }


}
