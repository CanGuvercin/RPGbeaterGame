package eu.guvercin;

public class Enemy extends Character {

	// Variable to store the players current xp
	int playerXp;
	
	// Enemy specific constructor
	public Enemy(String name, int playerXp) {
		super(
				name,
				(int) (Math.random() * playerXp + playerXp / 3 + 5),
				(int) (Math.random() * (playerXp / 4 + 2) + 1));
		this.playerXp = playerXp;
	}

	// Enemy specific attack and defense calculations
	@Override
	public int attack() {
		return (int) (Math.random() * (playerXp / 4 + 1) + xp / 4 + 3);
	}

	//
	@Override
	public int defend() {
		return (int) (Math.random() * (playerXp / 4 + 1) + xp / 4 + 3);
	}
}
