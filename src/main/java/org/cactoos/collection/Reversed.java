/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2020 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.cactoos.collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Unchecked;

/**
 * Reversed collection.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @param <X> Type of source item
 * @since 1.16
 * @todo #1242:30min Remove this class and replace it everywhere
 *  it was needed by the appropriate usage of Reversed from iterable
 *  (composed with ListOf or SetOf in case a copy is needed)
 *  or any other relevant concrete collection implementation.
 *  See #1242 for the rationale about this.
 */
public final class Reversed<X> extends CollectionEnvelope<X> {

    /**
     * Ctor.
     * @param src Source collection
     * @since 0.23
     */
    @SafeVarargs
    public Reversed(final X... src) {
        this(new IterableOf<>(src));
    }

    /**
     * Ctor.
     * @param src Source collection
     */
    public Reversed(final Iterable<X> src) {
        super(
            new Unchecked<>(
                new org.cactoos.scalar.Sticky<>(
                    () -> {
                        final List<X> items = new LinkedList<>();
                        src.forEach(items::add);
                        Collections.reverse(items);
                        return items;
                    }
                )
            ).value()
        );
    }

}
