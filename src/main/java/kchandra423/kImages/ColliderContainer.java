/*
MIT License

Copyright (c) 2020 Kumar.s.Chandra

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

import java.util.HashMap;
import java.util.Set;

public class ColliderContainer {
    private final HashMap<String, KImage> images;

    public ColliderContainer(HashMap<String, KImage> images) {
        this.images = images;
    }

    public KImage getKImage(String name) {
        return images.get(name);
    }

    public void removeKImages(Set<String> names) {
        for (String name :
                names) {
            images.remove(name);
        }
    }

    public void merge(ColliderContainer other) {
        images.putAll(other.images);
    }
//    public void addKImages(HashMap<String, KImage> additions){
//        images.putAll(additions);
//    }
//    public Set<String> getNames(){
//        return images.keySet();
//    }


}
