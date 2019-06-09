/*
 * Educational software for a basic game development
 * Copyright (C) 2018  Pr. Olivier Gruber
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.ricm3.game.purgatoire;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;

public class GameMain {

	public static void main(String[] args) throws IllegalAccessException {

		// construct the game elements: model, controller, and view.
		Model model = new Model();
		View view = new View(model);
		Controller controller = new Controller(model, view);
		Singleton.setController(controller);

		Dimension d = new Dimension(540 + 2 * Options.UI_PANEL_SIZE, 744 + 40);
		new GameUI(model, view, controller, d);

		return;
	}
}
