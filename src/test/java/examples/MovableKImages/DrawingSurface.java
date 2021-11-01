package examples.MovableKImages;

import kchandra423.kImages.KImage;
import kchandra423.kImages.KImageBuilder;
import kchandra423.kImages.MovableKImage;
import org.imgscalr.Scalr;
import org.junit.jupiter.api.Test;
import processing.core.PApplet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class DrawingSurface extends PApplet {
    private float angle;
    private boolean reflected;
    private float x, y;
    ArrayList<MovableKImage> examples = new ArrayList<>();

    //just check that this doesn't result in an error
    @Test
    public void setup() {
        File f = new File("src/test/resources");
        for (File img :
                f.listFiles()) {
            try {
                //can't escape the random ds stores
                if (!img.toString().toLowerCase().contains("ds_store")) {
                    KImage image = KImageBuilder.getKImage(img.getAbsolutePath());
                    image.resize(250, 250, null, Scalr.Mode.FIT_EXACT);
                    if (img.toString().contains("gif")) {
                        int x = 0;
                    }
                    examples.add(new MovableKImage(image,0,0,0,false, MovableKImage.ImageMode.CENTER));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void draw() {
        background(200);
        for (int i = 0; i < examples.size(); i++) {
            float x = i % 3 * 250;
            float y = (i / 3) * 250;

            examples.get(i).moveTo(x+this.x, y+this.y);

            examples.get(i).setReflected(reflected);

            examples.get(i).setAngle(angle);
            examples.get(i).draw(this);
        }
    }

    public void keyPressed() {
        switch (key) {
            case 'w':
                y -= 5;
                break;
            case 's':
                y += 5;
                break;
            case 'd':
                x += 5;
                break;
            case 'a':
                x -= 5;
                break;
            case ' ':
                reflected = !reflected;
                break;
            case 'f':
                angle += Math.PI / 10f;
                break;
            case 'r':
                angle-= Math.PI /10f;
                break;
            case 'c':
                x = 0;
                y = 0;
                angle = 0;
                reflected = false;
        }
    }


}
