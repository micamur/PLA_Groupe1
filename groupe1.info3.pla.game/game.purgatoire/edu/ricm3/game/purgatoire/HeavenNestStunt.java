package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import ricm3.interpreter.IAutomaton;

public class HeavenNestStunt extends Stunt {

	HeavenNestStunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		super(automaton, entity, sprite);
		m_entity.m_maxHP = Options.HEAVEN_NEST_HP_MAX;
		m_entity.m_DMG = Options.HEAVEN_NEST_DMG;
	}

	HeavenNestStunt() {
		super(Singleton.getNewNestHellAut(), null, Color.GRAY);
	}

	@Override
	void egg() {
		int x, y, width, height, randX, randY;
		width = m_entity.m_bounds.width;
		height = m_entity.m_bounds.height;
		x = ((2 * m_entity.m_bounds.x) + width ) / 2;
		y = ((2 * m_entity.m_bounds.y) + height) / 2;
		Random r = new Random();
		double tmpX = Math.random();
		double tmpY = Math.random();

		if (tmpX >= 0.5)
			randX = x - r.nextInt(width + 2);

		else
			randX = x + r.nextInt(width + 2);

		if (tmpY >= 0.5)
			randY = y - r.nextInt(height + 2);
		else
			randY = y + r.nextInt(height + 2);

		if (m_entity.m_level.m_collisionGrid.isOk(new Soul(m_entity.m_level, randX, randY, 2, 2)))
			m_entity.m_level.addEntity(new Soul(m_entity.m_level, randX-2, randY-2, 2, 2));
		System.out.println("egg de Nid ");
	}
}
