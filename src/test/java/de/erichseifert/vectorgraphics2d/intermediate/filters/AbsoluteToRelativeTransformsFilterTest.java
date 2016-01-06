/*
 * VectorGraphics2D: Vector export for Java(R) Graphics2D
 *
 * (C) Copyright 2010-2016 Erich Seifert <dev[at]erichseifert.de>
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
package de.erichseifert.vectorgraphics2d.intermediate.filters;

import static org.hamcrest.CoreMatchers.any;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;

import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;
import de.erichseifert.vectorgraphics2d.intermediate.commands.SetTransformCommand;
import de.erichseifert.vectorgraphics2d.intermediate.commands.TransformCommand;

public class AbsoluteToRelativeTransformsFilterTest {
	@Test
	public void testSetTransformCommandReplaced() {
		AffineTransform absoluteTransform = new AffineTransform();
		absoluteTransform.rotate(42.0);
		absoluteTransform.translate(4.0, 2.0);
		List<Command<?>> commands = new LinkedList<Command<?>>();
		commands.add(new SetTransformCommand(absoluteTransform));

		AbsoluteToRelativeTransformsFilter filter = new AbsoluteToRelativeTransformsFilter(commands);

		assertThat(filter, not(hasItem(any(SetTransformCommand.class))));
		assertThat(filter, hasItem(any(TransformCommand.class)));
		AffineTransform relativeTransform = ((TransformCommand) filter.next()).getValue();
		assertThat(relativeTransform, is(absoluteTransform));
	}
}
