package eu.guvercin;

public abstract class Character {
	
	// Variables / Attributes all characters have
	public String name;
	public int maxHP, hp, xp;
	
	// Constructor for character
	public Character(String name, int maxHP, int xp) {
		this.name = name;
		this.maxHP = maxHP;
		this.xp = xp;
		this.hp = maxHP;
	}
	
	// Methods every character has
	public abstract int attack();
	public abstract int defend();
}
