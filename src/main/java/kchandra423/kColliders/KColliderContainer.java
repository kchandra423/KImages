package kchandra423.kColliders;

import java.util.HashMap;
import java.util.Set;

public class KColliderContainer {
    private final HashMap<String, KCollider> images;

    public KColliderContainer(HashMap<String, KCollider> images) {
        this.images = images;
    }

    public KCollider get(String name) {
        return images.get(name).copy();
    }

    public void removeKImages(Set<String> names) {
        for (String name :
                names) {
            images.remove(name);
        }
    }

    public void merge(KColliderContainer other) {
        images.putAll(other.images);
    }
}
