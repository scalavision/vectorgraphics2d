/*
 * VectorGraphics2D: Vector export for Java(R) Graphics2D
 *
 * (C) Copyright 2010-2019 Erich Seifert <dev[at]erichseifert.de>,
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
package de.erichseifert.vectorgraphics2d.intermediate;

import static org.hamcrest.CoreMatchers.hasItem;

import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.erichseifert.vectorgraphics2d.intermediate.commands.Command;

public class MutableCommandSequenceTest {
	@Test
	public void testIteratorContainsAddedCommands() {
		MutableCommandSequence commands = new MutableCommandSequence();
		Command<?> command = new Command<Object>(null) {};

		commands.add(command);

		assertThat(commands, hasItem(command));
	}
}

