/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 Niko Fink
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package de.ncoder.typedmap;

import java.io.Serializable;

public class Key<T> implements Serializable {
    private final Class<T> clazz;
    private final String identifier;

    public Key(Class<T> clazz) {
        this(clazz, clazz.getName());
    }

    public Key(Class<T> clazz, String identifier) {
        if (clazz == null) {
            throw new NullPointerException("class");
        }
        if (identifier == null) {
            throw new NullPointerException("identifier");
        }
        this.clazz = clazz;
        this.identifier = identifier;
    }

    @SuppressWarnings("unchecked")
    public static Key<?> forName(String clazz, String identifier) throws ClassNotFoundException {
        if (identifier == null || identifier.isEmpty()) {
            return new Key(Class.forName(clazz));
        } else {
            return new Key(Class.forName(clazz), identifier);
        }
    }

    public Class<T> getValueClass() {
        return clazz;
    }

    public String getIdentifier() {
        return identifier;
    }

    public boolean isPossibleValue(Object value) {
        return value == null || getValueClass().isInstance(value);
    }

    @Override
    public String toString() {
        String clazz = getValueClass().toString();
        if (getIdentifier().equals(clazz)) {
            return clazz;
        } else {
            return getIdentifier() + " [" + clazz + "]";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Key key = (Key) o;
        return clazz.equals(key.clazz) && identifier.equals(key.identifier);
    }

    @Override
    public int hashCode() {
        return 31 * clazz.hashCode() + identifier.hashCode();
    }
}
