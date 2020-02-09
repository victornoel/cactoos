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

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.cactoos.iterable.IterableOf;
import org.cactoos.scalar.Unchecked;

/**
 * A {@link Collection} that is both synchronized and sticky.
 *
 * <p>Objects of this class are thread-safe.</p>
 *
 * @param <T> List type
 * @see Sticky
 * @since 0.24
 * @todo #1242:30min Remove this class and replace it everywhere
 *  it was needed by the appropriate usage of Synced (since Solid
 *  is based on Synced and Sticky, which was removed) from list,
 *  set, iterable, iterator or any other relevant concrete
 *  collection implementation. See #1242 for the rationale about this.
 */
public final class Solid<T> extends CollectionEnvelope<T> {

    /**
     * Ctor.
     * @param array An array of some elements
     */
    @SafeVarargs
    public Solid(final T... array) {
        this(new IterableOf<>(array));
    }

    /**
     * Ctor.
     * @param src An {@link Iterable}
     */
    public Solid(final Iterable<T> src) {
        super(
            new Unchecked<>(
                new org.cactoos.scalar.Solid<>(
                    new org.cactoos.scalar.Sticky<>(
                        () -> {
                            final List<T> items = new LinkedList<>();
                            src.forEach(items::add);
                            Collections.shuffle(items);
                            return items;
                        }
                    )
                )
            ).value()
        );
    }

}
