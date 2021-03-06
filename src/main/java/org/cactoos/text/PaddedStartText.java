/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2017-2018 Yegor Bugayenko
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
package org.cactoos.text;

import java.io.IOException;
import org.cactoos.Text;

/**
 * Text padded at start to reach the given length.
 *
 * <p>There is thread safe.
 *
 * @author Vivek Poddar (vivekimsit@gmail.com)
 * @version $Id$
 * @since 0.32
 */
public final class PaddedStartText implements Text {

    /**
     * The text.
     */
    private final Text origin;

    /**
     * The minimum length of the resulting string.
     */
    private final int length;

    /**
     * The character to be padded at the begining.
     */
    private final char symbol;

    /**
     * Ctor.
     * @param text The text
     * @param length The minimum length of the resulting string
     * @param symbol The padding symbol
     */
    public PaddedStartText(
        final Text text, final int length, final char symbol) {
        this.origin = text;
        this.symbol = symbol;
        this.length = length;
    }

    @Override
    public String asString() throws IOException {
        final String original = this.origin.asString();
        final int diff = this.length - original.length();
        final StringBuilder builder = new StringBuilder();
        for (int len = diff; len > 0; --len) {
            builder.append(this.symbol);
        }
        builder.append(original);
        return builder.toString();
    }
}

