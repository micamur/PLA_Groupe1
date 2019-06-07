package edu.ricm3.game.purgatoire;

import ricm3.interpreter.IEntityType;

public class Player extends Entity {
	private int m_maxTotalHP;
	private int m_karma, m_maxKarma;
	private int m_XP;
	private int m_rank;
	private Model m_model;

	public Player(Model model, Level level, int x, int y, int width, int height) {
		super(level, new HeavenPlayerStunt(null), new HellPlayerStunt(null), x, y, width, height);
		m_model = model;
		m_type = IEntityType.PLAYER;
		m_XP = Options.PLAYER_XP;
		m_HP = Options.PLAYER_HP;
		m_maxTotalHP = Options.PLAYER_MAX_TOTAL_HP;
		m_maxKarma = Options.PLAYER_KARMA_MAX;
	}

	public void addKarma(Entity e) {
		m_karma += e.m_currentStunt.m_karmaToGive;
		Singleton.getController().updateUI();
	}

	public void addKarma(int karma) {
		m_karma += karma;
		if (m_karma > getMaxKarma())
			m_karma = getMaxKarma();
		else if (m_karma < -getMaxKarma())
			m_karma = -getMaxKarma();
		Singleton.getController().updateUI();
	}

	public void updateRank() {
		if (m_XP >= getMaxXP()) {
			m_rank++;
		} else if (m_XP < getMinXP()) {
			m_rank--;
		}
	}

	@Override
	void step(long now) {
		m_currentStunt.step(now);
	}

	public void nextLevel(Level newLevel) {
		m_level = newLevel;
		m_bounds.y = Options.LVL_HEIGHT - 3;
		m_level.addEntity(this);
	}

	public int getKarma() {
		return m_karma;
	}

	public int getMaxKarma() {
		return m_maxKarma;
	}

	public int getXP() {
		return m_XP;
	}

	public int getMinXP() {
		return Options.PLAYER_RANKS[m_rank];
	}

	public int getMaxXP() {
		return Options.PLAYER_RANKS[m_rank + 1];
	}

	public int getRank() {
		return m_rank;
	}

	public String getRankName() {
		return ((PlayerStunt) m_currentStunt).getRankName();
	}

	// TODO if last level is reached, don't gain more XP
	public void addXP(double coef) {
		m_XP += Math.abs(m_karma) * coef;
		m_XP = Math.max(m_XP, 0);
		updateRank();
		Singleton.getController().updateUI();
	}

	@Override
	public void addHP(int HP) {
		super.addHP(HP);
		Singleton.getController().updateUI();
	}

	public int getMaxTotalHP() {
		return m_maxTotalHP;
	}

	public void testKarma() {
		if (m_karma >= 0 && m_model.m_wt == WorldType.HEAVEN || m_karma <= 0 && m_model.m_wt == WorldType.HELL) {
			addXP(Options.COEF_KARMA_POS);
		} else {
			addXP(Options.COEF_KARMA_NEG);
			m_model.transform();
		}
		m_karma = 0;
	}

}
