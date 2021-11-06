package tests;

import kchandra423.kImages.KImageBuilder;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

class KImageBuilderTest {

    @Test
    void getKImage() throws IOException {
        File f = new File("src/test/resources");
        for (File img :
                f.listFiles()) {
            //can't escape the random ds stores
            if (!img.toString().toLowerCase().contains("ds_store")) {
                KImageBuilder.getKImage(img.getAbsolutePath());
            }
        }
    }
}