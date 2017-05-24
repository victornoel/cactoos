/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2017 Yegor Bugayenko
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 * <p>
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
import org.cactoos.Scalar;
import org.cactoos.Text;

/**
 * Determines if text is blank (consists of spaces) or no.
 *
 * <p>There is no thread-safety guarantee.
 *
 * @author Andriy Kryvtsun (kontiky@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public final class BlankText implements Scalar<Boolean> {

    /**
     * The text.
     */
    private final Text origin;

    /**
     * Ctor.
     * @param text The text
     */
    public BlankText(final Text text) {
        this.origin = text;
    }

    public Boolean asValue() throws IOException {
        boolean result = true;
        final String str = this.origin.asString();
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                result = false;
                break;
            }
        }
        return result;
    }
}
