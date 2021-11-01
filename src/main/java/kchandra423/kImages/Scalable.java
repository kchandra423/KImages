package kchandra423.kImages;

import org.jetbrains.annotations.Nullable;
import org.imgscalr.Scalr;

interface Scalable {
    int getWidth();

    int getHeight();

    default void scale(float stretchX, float stretchY) {
        scale(stretchX, stretchY, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC);
    }

    default void resize(int w, int h) {
        resize(w, h, Scalr.Method.AUTOMATIC, Scalr.Mode.AUTOMATIC);
    }

    default void scale(float stretchX, float stretchY, @Nullable Scalr.Method method, @Nullable Scalr.Mode mode) {
        resize((int) (getWidth() * stretchX), (int) (getHeight() * stretchY), method, mode);
    }

    void resize(int w, int h, @Nullable Scalr.Method method, @Nullable Scalr.Mode mode);

}
