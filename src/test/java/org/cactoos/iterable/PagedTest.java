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
package org.cactoos.iterable;

import java.util.Iterator;
import org.cactoos.iterator.IteratorOf;
import org.cactoos.scalar.LengthOf;
import org.cactoos.scalar.Ternary;
import org.hamcrest.collection.IsIterableWithSize;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test case for {@link Paged}.
 * @since 0.47
 * @checkstyle ClassDataAbstractionCoupling (2 lines)
 */
final class PagedTest {

    @Test
    @SuppressWarnings({"unchecked", "PMD.AvoidDuplicateLiterals"})
    void containAllPagedContentInOrder() throws Exception {
        final Iterable<String> first = new IterableOf<>("one", "two");
        final Iterable<String> second = new IterableOf<>("three", "four");
        final Iterable<String> third = new IterableOf<>("five");
        final Iterable<Iterable<String>> service = new IterableOf<>(
            first, second, third
        );
        final Iterator<Iterable<String>> pages = service.iterator();
        new Assertion<Iterable<?>>(
            "must have all page values",
            new Paged<>(
                () -> pages.next().iterator(),
                page -> new Ternary<>(
                    () -> pages.hasNext(),
                    () -> pages.next().iterator(),
                    () -> new IteratorOf<String>()
                ).value()
            ),
            new IsEqual<>(new Joined<>(first, second, third))
        ).affirm();
    }

    @Test
    @SuppressWarnings("unchecked")
    void reportTotalPagedLength() throws Exception {
        final Iterable<String> first = new IterableOf<>("A", "five");
        final Iterable<String> second = new IterableOf<>("word", "long");
        final Iterable<String> third = new IterableOf<>("sentence");
        final Iterable<Iterable<String>> service = new IterableOf<>(
            first, second, third
        );
        final Iterator<Iterable<String>> pages = service.iterator();
        new Assertion<Iterable<String>>(
            "length must be equal to total number of elements",
            new Paged<>(
                () -> pages.next().iterator(),
                page -> new Ternary<>(
                    () -> pages.hasNext(),
                    () -> pages.next().iterator(),
                    () -> new IteratorOf<String>()
                ).value()
            ),
            new IsIterableWithSize<>(
                new IsEqual<>(
                    new LengthOf(
                        new Joined<>(first, second, third)
                    ).intValue()
                )
            )
        ).affirm();
    }

}
