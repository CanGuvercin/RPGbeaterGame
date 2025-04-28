package eu.guvercin;

public class Player extends Character {
	
	// Integers to store number of upgrades/skills in each path
	private int numAtkUpgrades, numDefUpgrades;
	
	// Additional player stats
	private int gold, restsLeft, pots;

	// Arrays to store skill names
	public static String[] atkUpgrades = {
			"Strength",
			"Sharp Sword",
			"Quick Attack",
			"Critical Damage",
			"Devastating Impact"
	};

	public static String[] defUpgrades = {
			"Armor",
			"Quick Reflexes",
			"Defensive Stance",
			"Natural Resistance",
			"Magic Barrier"
	};

	// Player specific constructor
	public Player(String name) {
		// Calling constructor of superclass
		// super(name, 100, 0, 100);
		super(name, 100, 0);
		
		// Set additional stats
		this.gold = 5;
		this.restsLeft = 1;
		this.pots = 0;
		
		// Setting # of upgrades to 0
		this.numAtkUpgrades = 0;
		this.numDefUpgrades = 0;
		// Let the player choose a trait when creating a new character
		chooseTrait();
	}
	
	// Player specific methods (more in the next part)
	@Override
	public int attack() {
		return (int)
				(Math.random() *
						(xp / 4 + getNumAtkUpgrades() * 3 + 3) +
						xp / 10 + getNumAtkUpgrades() * 2 +
						getNumDefUpgrades() + 1);
	}

	@Override
	public int defend() {
		return (int) (Math.random() * (xp / 4 + getNumDefUpgrades() * 3 + 3) + xp / 10 + getNumDefUpgrades() * 2 + getNumAtkUpgrades() + 1);
	}
	
	// Let the player choose a trait of either skill path
	public void chooseTrait() {
		LogicController.clearConsole();
		LogicController.printHeading("Escolha uma caracteristica:");
		System.out.println("(1) " + atkUpgrades[getNumAtkUpgrades()]);
		System.out.println("(2) " + defUpgrades[getNumDefUpgrades()]);
		
		// Get the players choice:
		int input = LogicController.readInt("-> ", 2);
		LogicController.clearConsole();
		
		// Deal with both cases
		if (input == 1) {
			LogicController.printHeading("Voce escolheu " + atkUpgrades[getNumAtkUpgrades()] + "!");
			setNumAtkUpgrades(getNumAtkUpgrades() + 1);
		} else {
			LogicController.printHeading("Voce escolheu " + defUpgrades[getNumDefUpgrades()] + "!");
			setNumDefUpgrades(getNumDefUpgrades() + 1);
		}
		LogicController.anythingToContinue();
	}

	public int getNumAtkUpgrades() {
		return numAtkUpgrades;
	}

	public void setNumAtkUpgrades(int numAtkUpgrades) {
		this.numAtkUpgrades = numAtkUpgrades;
	}

	public int getNumDefUpgrades() {
		return numDefUpgrades;
	}

	public void setNumDefUpgrades(int numDefUpgrades) {
		this.numDefUpgrades = numDefUpgrades;
	}

	public int getGold() {
		return gold;
	}

	public void setGold(int gold) {
		this.gold = gold;
	}

	public int getRestsLeft() {
		return restsLeft;
	}

	public void setRestsLeft(int restsLeft) {
		this.restsLeft = restsLeft;
	}

	public int getPots() {
		return pots;
	}

	public void setPots(int pots) {
		this.pots = pots;
	}
}
