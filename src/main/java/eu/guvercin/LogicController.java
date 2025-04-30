package eu.guvercin;
import java.util.Scanner;

public class LogicController implements GameController {
	// static Scanner scanner = new Scanner(System.in);
	
	private static Scanner scanner = new Scanner(System.in);
	private static boolean running;
	static Player player;
	
	// Constants -> Random ENCOUNTERS, Enemy names and Story elements
	private static final String[] ENCOUNTERS = {
			"Battle", "Battle", "Battle", "Rest", "Rest"
	};

	public static final String[] ENEMIES = {
			"Ogre", "Ogre", "Goblin", "Goblin", "Stone Elemental"
	};

	public static final String[] PLACES = {
			"Eternal Mountains",
			"Haunted Lands",
			"Castle of the Evil Emperor",
			"Throne Room"
	};

	public static int place = 0, act = 1;


	// Method to get user input from console
	public static int readInt(String prompt, int userChoices) {
		int input;
		
		do {
			System.out.println(prompt);
			try {
				input = Integer.parseInt(scanner.next());
			} catch(Exception err) {
				input = -1;
				System.out.println("Please enter a valid number!");
			}
		} while(input < 1 || input > userChoices);
		
		return input;
	}
	
	// Method to simulate clearing out the console
	public static void clearConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			else
				new ProcessBuilder("clear").inheritIO().start().waitFor();
		} catch (Exception e) {
			System.out.println("Error clearing console");
		}
	}


	// Method to print a separator with length n
	public static void printSeparator(int n) {
		for(int i = 0; i < n; i++) {
			System.out.print("_");
		}
		System.out.println();
	}
	
	// Method to print a heading
	public static void printHeading(String title) {
		printSeparator(30);
		System.out.println(title);
		printSeparator(30);
	}
	
	
	// Method to stop the game until user enters anything
	public static void anythingToContinue() {
		System.out.println("\nPress any key to continue...");
		scanner.next();
	}
	
	// Method to start the game
	@Override
	public void startGame() {
		boolean nameSet = false;
		String name;

		// Print title screen
		clearConsole();
		printSeparator(40);
		System.out.println(" ███████╗███╗   ███╗██████╗ ███████╗██████╗  ██████╗ ██████╗ ");
		System.out.println(" ██╔════╝████╗ ████║██╔══██╗██╔════╝██╔══██╗██╔═══██╗██╔══██╗");
		System.out.println(" █████╗  ██╔████╔██║██████╔╝█████╗  ██████╔╝██║   ██║██████╔╝");
		System.out.println(" ██╔══╝  ██║╚██╔╝██║██╔═══╝ ██╔══╝  ████╔═╝ ██║   ██║████╔═╝ ");
		System.out.println(" ███████╗██║ ╚═╝ ██║██║     ███████╗██║ ██║ ╚██████╔╝██║ ██║ ");
		System.out.println(" ╚══════╝╚═╝     ╚═╝╚═╝     ╚══════╝╚═╝ ╚═╝  ╚═════╝ ╚═╝ ╚═╝ ");
		System.out.println();
		System.out.println("           REVENGE FANTASY RPG  •  PREPARE TO DIE LIKE A RAT      ");
		printSeparator(40);
		System.out.println("         PRESS ANY KEY TO BEGIN YOUR VENGEANCE              ");
		anythingToContinue();

		// Getting the player name
		do {
			clearConsole();
			printHeading("What is your name dear poor victim?");
			name = scanner.next();

			// Asking the player if he wants to confirm his choice
			clearConsole();
			printHeading("Your name is " + name + ".\nIs that correct?");
			System.out.println("(1) Yes!");
			System.out.println("(2) No, I want to change my name.");

			int input = readInt("-> ", 2);
			if (input == 1) {
				nameSet = true;
			}
		} while (!nameSet);

		// Print story intro
		Story.printIntro();

		// Create new player object with the name
		player = new Player(name);

		// Print first story act intro
		Story.printFirstActIntro();

		// Setting `running` to true, so the game loop can continue
		running = true;

		// Start main game loop
		gameLoop();
	}


	// Method that changes the game's values based on player xp
	// Progression control
	@Override
	public void checkAct() {
		// Change acts based on xp
		if (player.xp >= 10 && act == 1) {
			// Increment act and place
			act = 2;
			place = 1;
			// Story
			Story.printFirstActOutro();
			// Let the player "level up"
			player.chooseTrait();
			// Story
			Story.printSecondActIntro();
			// Assign new values to ENEMIES
			ENEMIES[0] = "Evil Mercenary";
			ENEMIES[1] = "Goblin";
			ENEMIES[2] = "Wolf Pack";
			ENEMIES[3] = "Evil Emperor's Henchman";
			ENEMIES[4] = "Creepy Stranger";
			// Assign new values to ENCOUNTERS
			ENCOUNTERS[0] = "Battle";
			ENCOUNTERS[1] = "Battle";
			ENCOUNTERS[2] = "Battle";
			ENCOUNTERS[3] = "Rest";
			ENCOUNTERS[4] = "Shop";
		} else if (player.xp >= 50 && act == 2) {
			act = 3;
			place = 2;
			// Story
			Story.printSecondActOutro();
			// "level up"
			player.chooseTrait();
			// Story
			Story.printThirdActIntro();
			// Assign new values to ENEMIES
			ENEMIES[0] = "Goblin";
			ENEMIES[1] = "Evil Mercenary";
			ENEMIES[2] = "Evil Emperor's Henchman";
			ENEMIES[3] = "Evil Emperor's Henchman";
			ENEMIES[4] = "Evil Emperor's Henchman";
			// Assign new values to ENCOUNTERS
			ENCOUNTERS[0] = "Battle";
			ENCOUNTERS[1] = "Battle";
			ENCOUNTERS[2] = "Battle";
			ENCOUNTERS[3] = "Battle";
			ENCOUNTERS[4] = "Shop";
			// Fully heal the player
			player.hp = player.maxHP;
		} else if (player.xp >= 100 && act == 3) {
			act = 4;
			place = 3;
			// Story
			Story.printThirdActOutro();
			// "level up"
			player.chooseTrait();
			// Story
			Story.printFourthActIntro();
			// Fully heal the player
			player.hp = player.maxHP;
			// Calling final battle
			finalBattle();
		}
	}
	
	// Method to calculate a random encounter
	@Override
	public void randomEncounter() {
		// Random number between 0 and the length of the ENCOUNTERS array
		int encounter = (int) (Math.random() * ENCOUNTERS.length);
		
		// Calling the respective methods
		if (ENCOUNTERS[encounter].equals("Battle")) {
			randomBattle();
		} else if (ENCOUNTERS[encounter].equals("Rest")) {
			takeRest();
		} else {
			shop();
		}
	}
	
	// Method to continue the journey (more next part)
	@Override
	public void continueJourney() {
		// Check if act must be increased
		checkAct();
		
		// Check if game isn't in last act
		if (act != 4) {
			randomEncounter();
		}
	}
	
	// Printing out the most important information about the player character
	@Override
	public void characterInfo() {
		clearConsole();
		printHeading("Character Information");
		System.out.println(player.name + "\tHP: " + player.hp + "/" + player.maxHP);
		printSeparator(20);
		
		// Player xp and gold
		System.out.println("XP: " + player.xp + "\tGold: " + player.getGold());
		printSeparator(20);
		
		// # Of pots
		System.out.println("#Potions: " + player.getPots());
		printSeparator(20);
		
		// Printing the chosen traits
		if (player.getNumAtkUpgrades() > 0) {
			System.out.println("Attack Trait: " + player.atkUpgrades[player.getNumAtkUpgrades() - 1]);
		}
		
		// Printing the chosen traits
		if (player.getNumDefUpgrades() > 0) {
			System.out.println("Defense Trait: " + player.defUpgrades[player.getNumDefUpgrades() - 1]);
		}
		
		anythingToContinue();
	}
	
	// Shopping / Encountering a travelling trader
	@Override
	public void shop() {
		clearConsole();
		printHeading("You encounter a mysterious stranger.\nHe offers you something:");

		int price = (int) (Math.random() * (10 + player.getPots() * 3) + 10 + player.getPots());
		System.out.println("- Magic Potion: " + price + " gold.");
		printSeparator(20);

		// Ask the player to buy one
		System.out.println("Do you want to buy one? Nobody wants to regret in future hah! \n(1) Yes!\n(2) No, thanks.");
		int input = readInt("-> ", 2);

		// Check if player wants to buy
		if (input == 1) {
			clearConsole();
			// Check if player has enough gold
			if (player.getGold() >= price) {
				player.setPots(player.getPots() + 1);
				player.setGold(player.getGold() - price);
				printHeading("You bought a magic potion for " + price + " gold.\nRemaining gold: " + player.getGold());
			} else {
				printHeading("You don't have enough gold to buy this, you poor bastard.");
			}

			anythingToContinue();
		}
	}

	
	// Taking a rest
	@Override
	public void takeRest() {
		clearConsole();
		if(player.getRestsLeft() >= 1) {
			printHeading("You want to rest? (" + player.getRestsLeft() + " rest remainder.");
			System.out.println("(1) Yes\n(2) No, not now.");
			
			int input = readInt("-> ", 2);
			if (input == 1) {
				// Player actually takes rest
				clearConsole();
				if (player.hp < player.maxHP) {
					int hpRestored = (int) (Math.random() * (player.xp / 4 + 1) + 10); 
					player.hp += hpRestored;
					
					if (player.hp > player.maxHP) {
						player.hp = player.maxHP;
					}
					System.out.println("You recovered " + hpRestored + " hp.");
					System.out.println("Now you are with " + player.hp + "/" + player.maxHP + " hp.");
					player.setRestsLeft(player.getRestsLeft() - 1);
				}
				
			} else {
				System.out.println("You are in perfect health. There is no need to rest now!");
			}
			
			anythingToContinue();
		}
	}
	
	// Creating a random battle
	@Override
	public void randomBattle() {
		clearConsole();

		// Random enemy creation
		Enemy enemy = new Enemy(
				ENEMIES[(int)(Math.random() * ENEMIES.length)],
				player.xp
		);

		printHeading("You have encountered a hostile creature: " + enemy.name + "!\n Prepare to die you bastard!");
		anythingToContinue();
		
		// Creating new random enemy
		battle(enemy);
	}
	
	// The main BATTLE method
	@Override
	public void battle(Enemy enemy) {
		// Main battle loop
		while (true) {
			clearConsole();
			printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHP);
			printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHP);
			System.out.println("Choose an action:");
			printSeparator(20);
			System.out.println("(1) Fight\n(2) Use Potion\n(3) Run Away");

			int input = readInt("-> ", 3);

			if (input == 1) {
				// FIGHT
				int dmg = player.attack() - enemy.defend();
				int dmgTook = enemy.attack() - player.defend();

				if (dmgTook < 0) {
					dmg -= dmgTook / 2;
					dmgTook = 0;
				}

				if (dmg < 0) dmg = 0;

				player.hp -= dmgTook;
				enemy.hp -= dmg;

				clearConsole();
				printHeading("BATTLE");
				System.out.println("You dealt " + dmg + " damage to " + enemy.name + ".");
				printSeparator(15);
				System.out.println(enemy.name + " dealt " + dmgTook + " damage to you.");
				anythingToContinue();

				if (player.hp <= 0) {
					playerDied();
					break;
				} else if (enemy.hp <= 0) {
					clearConsole();
					printHeading("You defeated " + enemy.name + "!");
					player.xp += enemy.xp;
					int goldEarned = (int) (Math.random() * (enemy.xp / 2.0 + 1));
					player.setGold(player.getGold() + goldEarned);
					System.out.println("You gained " + enemy.xp + " XP and looted " + goldEarned + " gold!");
					anythingToContinue();
					break;
				}
			} else if (input == 2) {
				// USE POTION
				clearConsole();
				if (player.getPots() > 0 && player.hp < player.maxHP) {
					System.out.println("Do you want to drink a potion? (" + player.getPots() + " left)");
					System.out.println("(1) Yes\n(2) No, maybe later");

					input = readInt("-> ", 2);
					if (input == 1) {
						player.hp = player.maxHP;
						clearConsole();
						printHeading("You drank a magic potion. It restored your health to " + player.maxHP);

						boolean addRest = (Math.random() * 5 + 1 <= 2.25);
						int goldEarned = (int) (Math.random() * enemy.xp);

						if (addRest) {
							player.setRestsLeft(player.getRestsLeft() + 1);
							System.out.println("You gained an extra rest opportunity!");
						}

						if (goldEarned > 0) {
							player.setGold(player.getGold() + goldEarned);
							System.out.println("You looted " + goldEarned + " gold from the corpse of " + enemy.name);
						}

						anythingToContinue();
						break;
					}
				} else {
					printHeading("You have no potions or your health is already full.");
					anythingToContinue();
				}
			} else {
				// RUN AWAY
				clearConsole();
				if (act != 4) {
					// 35% chance to escape
					if (Math.random() * 10 + 1 <= 3.5) {
						printHeading("You successfully escaped from " + enemy.name + "!");
						anythingToContinue();
						break;
					} else {
						printHeading("You failed to escape.");
						int dmgTook = enemy.attack();
						System.out.println("While rushing away, you took " + dmgTook + " damage!");
						player.hp -= dmgTook;
						anythingToContinue();
						if (player.hp <= 0) {
							playerDied();
							break;
						}
					}
				} else {
					printHeading("YOU CANNOT RUN FROM THE EVIL EMPEROR!");
					anythingToContinue();
				}
			}
		}
	}


	// Printing the main menu
	@Override
	public void printMenu() {
		clearConsole();
		printHeading(PLACES[place]); // Current location name
		System.out.println("Choose an action:");
		printSeparator(20);
		System.out.println("(1) Continue your journey");
		System.out.println("(2) View character info");
		System.out.println("(3) Exit the game");
	}


	// The final (last) battle of the entire game
	@Override
	public void finalBattle() {
		// Creating the Evil Emperor and letting the player fight against him
		battle(new Enemy("SHADOW EMPEROR", 200));
		
		// Printing the proper ending
		if (player.hp > 0) {
			Story.printEndWin(player);
		} else {
			Story.prinEndLose();
		}
		
		running = false;
	}
	
	// Method that gets called when the player is dead
	@Override
	public void playerDied() {
		clearConsole();
		printHeading("You died like a rat...");
		printHeading("You earned " + player.xp + " as nothing. XP during your poor journey. Try to be a man on your score next time!");
		printHeading("EMPEROR ALWAYS WINS you poor rat!");
		running = false;
	}

	// Main game loop
	@Override
	public void gameLoop() {
		while (running) {
			printMenu();
			
			int input = readInt("-> ", 3);
			if (input == 1) {
				continueJourney();
			} else if (input == 2) {
				characterInfo();
			} else {
				running = false;
			}
		}
	}
}
