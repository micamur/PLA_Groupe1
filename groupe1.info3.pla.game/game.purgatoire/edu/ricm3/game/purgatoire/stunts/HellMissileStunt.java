package edu.ricm3.game.purgatoire.stunts;

import edu.ricm3.game.purgatoire.Animation.AnimType;
import edu.ricm3.game.purgatoire.AnimationPlayer;
import edu.ricm3.game.purgatoire.Options;
import edu.ricm3.game.purgatoire.Singleton;
import ricm3.interpreter.IDirection;

public class HellMissileStunt extends Stunt {

//	HellMissileStunt(IAutomaton automaton, Color c) {
//		super(automaton, c);
//		setDMG(Options.HELL_MISSILE_DMG);
//	}

	HellMissileStunt() {
		super(Singleton.getNewMissileHeavenAut(),
				new AnimationPlayer(Singleton.getMissileHellAnim(), AnimType.IDLE, 2));
		setDMG(Options.HELL_MISSILE_DMG);
	}

	@Override
	public void pop(IDirection d) {
		m_entity.die();
	}

	@Override
	public void wizz(IDirection d) {
		System.out.println("wizz hell soul");
	}

	@Override
	public void hit(IDirection d) {
		System.out.println("hit hell soul");
	}

	@Override
	public void egg() {
		System.out.println("egg hell soul");
	}

	void goingOut(IDirection d) {
		m_entity.die();
	}

	@Override
	public void step(long now) {
		m_automaton.step(m_entity);
	}

}
