package kchandra423.kColliders;

import java.awt.geom.Area;

class KArea extends Area {
    int getDensity() {
        return density;
    }

    void setDensity(int density) {
        this.density = density;
    }

    private int density;

}
