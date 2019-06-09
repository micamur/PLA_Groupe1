package edu.ricm3.game.purgatoire;

import java.awt.Color;
import java.awt.image.BufferedImage;

import ricm3.interpreter.IAutomaton;
import ricm3.interpreter.IDirection;
import ricm3.interpreter.IEntityType;

public class Stunt {

	IAutomaton m_automaton;
	Color m_c;
	BufferedImage m_sprite;
	Entity m_entity;
	int m_rangeDash = Options.DASH_SIZE;
	int m_cooldownDash = Options.DASH_CD;
	int m_maxHP, m_DMG;
	int m_karmaToGive;

	Stunt(IAutomaton automaton, Color c) {
		m_automaton = automaton;
		m_c = c;
	}

	Stunt(IAutomaton automaton, Entity entity, Color c) {
		m_automaton = automaton;
		m_entity = entity;
		m_c = c;
	}

	Stunt(IAutomaton automaton, Entity entity, BufferedImage sprite) {
		m_automaton = automaton;
		m_entity = entity;
		m_sprite = sprite;
	}

	public void tryMove(IDirection d) {
		switch (d) {
		case NORTH:
			if (m_entity.m_bounds.y == 1) {
				m_entity.m_level.m_model.nextLevel();
			} else if (m_entity.wontCollide(d)) {
				move(0, -1);
			}
			m_entity.m_direction = IDirection.NORTH;
			break;
		case SOUTH:
			if (m_entity.m_bounds.y < Options.LVL_HEIGHT - m_entity.m_bounds.height) {
				if (m_entity.wontCollide(d)) {
					move(0, 1);
				}
			}
			m_entity.m_direction = IDirection.SOUTH;
			break;
		case EAST:
			if (m_entity.m_bounds.x < Options.LVL_WIDTH - m_entity.m_bounds.height) {
				if (m_entity.wontCollide(d)) {
					move(1, 0);
				}
			}
			m_entity.m_direction = IDirection.EAST;
			break;
		case WEST:
			if (m_entity.m_bounds.x > 0) {
				if (m_entity.wontCollide(d)) {
					move(-1, 0);
				}
			}
			m_entity.m_direction = IDirection.WEST;
			break;
		default:
			break;
		}
	}

	public void step(Entity e) {
		m_automaton.step(m_entity);
	}

	void dash(IDirection d) {
		for (int i = 0; i < m_rangeDash; i++) {
			tryMove(d);
		}
	}

	void pop(IDirection d) {
		System.out.println("pop de base");
	}

	void wizz(IDirection d) {
		System.out.println("wizz de base");
	}

	void hit(IDirection d) {
		System.out.println("hit de base");
	}

	private void move(int x, int y) {
		m_entity.m_level.updateEntity(m_entity, x, y);
		m_entity.m_bounds.translate(x, y);
		m_entity.m_level.m_model.m_totalDistance -= y;
		Singleton.getController().updateUI();
	}

	void egg() {
		// TODO egg de base
		System.out.println("egg de base : NYI");
	}

	void getDamage(int DMG) {
		m_entity.addHP(-DMG);
		if (m_entity.m_HP <= 0) {
			m_entity.die();
		}
	}

	public void setAttachedEntity(Entity entity) {
		m_entity = entity;
	}

	public boolean isEntityAt(IEntityType type, IDirection direction) {
		return m_entity.superposedWith(type) != null;
	}

	public void step(long now) {
		m_automaton.step(m_entity);
	}

	public void setKarmaToGive(int karmaToGive) {
		m_karmaToGive = karmaToGive;
	}

}