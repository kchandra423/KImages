/*
MIT License

Copyright (c) 2021 Kumar Chandra

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
package kchandra423.kColliders;

import java.util.HashMap;
import java.util.Set;

/**
 * A Simple Data class for holding a group of {@link KCollider}s. This class is intended to hold preloaded KColliders
 * since initialization can be extremely slow. KColliders are identified by name, and this class can not be added to
 * after initialization.
 *
 * @author Kumar Chandra
 * @version 1.0
 * @see KCollider
 */
public class KColliderContainer {
    private final HashMap<String, KCollider> images;

    /**
     * Creates a new KCollider Container with each KCollider being associated with a name in this hashmap
     * @param images A Hashmap with names as the key and a KCollider as the value
     */
    public KColliderContainer(HashMap<String, KCollider> images) {
        this.images = images;
    }

    /**
     * Returns a new KCollider identical to the one passed in using the KCollider's copy method.
     * Successive gets of the same name will return different references to different objects, but this
     * is much faster than creating a KCollider from the same image multiple times.
     * @param name The name of the KCollider to get
     * @return A copy of the original KCollider
     */
    public KCollider get(String name) {
        return images.get(name).copy();
    }

    /**
     * Removes a set of KCollider from this class
     * @param names The names associated with each KCollider
     */
    public void removeKImages(Set<String> names) {
        for (String name :
                names) {
            images.remove(name);
        }
    }

    /**
     * Combines this container with another. Any duplicate names will be overridden by the
     * other KCollider's values. The other Container wil remain unchanged.
     * @param other The other KColldier Container to use
     */
    public void merge(KColliderContainer other) {
        images.putAll(other.images);
    }
}
