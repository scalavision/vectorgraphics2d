/*
 * VectorGraphics2D: Vector export for Java(R) Graphics2D
 *
 * (C) Copyright 2010-2016 Erich Seifert <dev[at]erichseifert.de>,
 * Michael Seifert <mseifert[at]error-reports.org>
 *
 * This file is part of VectorGraphics2D.
 *
 * VectorGraphics2D is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VectorGraphics2D is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with VectorGraphics2D.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.erichseifert.vectorgraphics2d.pdf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import org.junit.Test;

public class StreamTest {
	@Test
	public void testLengthIsZeroOnInitialization() {
		Stream stream = new Stream();

		int length = stream.getLength();

		assertThat(length, is(0));
	}

	@Test
	public void testLengthEqualsByteCountInWrittenDataWhenNoFiltersAreSet() {
		byte[] garbage = new byte[] {4, 2, 42, -1, 0};
		Stream stream = new Stream();
		stream.write(garbage);

		int length = stream.getLength();

		assertThat(length, is(garbage.length));
	}

	@Test
	public void testWrittenDataIsIdenticalToStreamContentWhenNoFiltersAreUsed() {
		byte[] data = new byte[] {4, 2, 42, -1, 0};
		Stream stream = new Stream();
		stream.write(data);

		byte[] content = stream.getContent();

		assertThat(content, is(data));
	}

	@Test
	public void testContentsAreCompressedWhenFlateFilterIsSet() throws DataFormatException {
		byte[] inputData = new byte[] {4, 2, 42, -1, 0};
		Stream stream = new Stream(Stream.Filter.FLATE);
		stream.write(inputData);

		byte[] compressedContent = stream.getContent();

		Inflater decompressor = new Inflater();
		decompressor.setInput(compressedContent);
		byte[] decompressedOutput = new byte[inputData.length];
		decompressor.inflate(decompressedOutput);
		assertThat(decompressedOutput, is(inputData));
	}
}
