package kchandra423.kImages;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class ColliderContainer {
    private final HashMap<String, KImage> images;

    public ColliderContainer(HashMap<String, KImage> images) {
        this.images = images;
    }

    public KImage getKImage(String name) {
        return images.get(name);
    }

    public void removeKImages(Set<String> names){
        for (String name :
                names) {
            images.remove(name);
        }
    }

    public void merge(ColliderContainer other){
        images.putAll(other.images);
    }
//    public void addKImages(HashMap<String, KImage> additions){
//        images.putAll(additions);
//    }
//    public Set<String> getNames(){
//        return images.keySet();
//    }


}
