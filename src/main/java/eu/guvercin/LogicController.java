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
				System.out.println("Por favor, insira um numero inteiro!");
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
		// clearConsole();
		printSeparator(40);
		printSeparator(30);
		System.out.println("ERA DO IMPERADOR MALVADO");
		System.out.println("RPG DE DIALOGO POR COLEGAS DA UNIP PARA APS");
		printSeparator(30);
		printSeparator(40);
		anythingToContinue();
		
		// Getting the player name
		do {
			clearConsole();
			printHeading("Qual e o seu nome?");
			name = scanner.next();
			// Asking the player if he wants to correct his choice
			clearConsole();
			printHeading("Seu nome e " + name + ".\nEsta correto?");
			System.out.println("(1) Sim!");
			System.out.println("(2) Nao, eu quero trocar meu nome.");
			
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
			ENEMIES[0] = "Mercenario Malvado";
			ENEMIES[1] = "Goblin";
			ENEMIES[2] = "Matilha de Lobos";
			ENEMIES[3] = "Capanga do Imperador Malvado";
			ENEMIES[4] = "Estranho Assustador";
			// Assign new values to ENCOUNTERS
			ENCOUNTERS[0] = "Batalha";
			ENCOUNTERS[1] = "Batalha";
			ENCOUNTERS[2] = "Batalha";
			ENCOUNTERS[3] = "Descanso";
			ENCOUNTERS[4] = "Loja";
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
			ENEMIES[1] = "Mercenario Malvado";
			ENEMIES[2] = "Capanga do Imperador Malvado";
			ENEMIES[3] = "Capanga do Imperador Malvado";
			ENEMIES[4] = "Capanga do Imperador Malvado";
			// Assign new values to ENCOUNTERS
			ENCOUNTERS[0] = "Batalha";
			ENCOUNTERS[1] = "Batalha";
			ENCOUNTERS[2] = "Batalha";
			ENCOUNTERS[3] = "Batalha";
			ENCOUNTERS[4] = "Loja";
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
		if (ENCOUNTERS[encounter].equals("Batalha")) {
			randomBattle();
		} else if (ENCOUNTERS[encounter].equals("Descanso")) {
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
		printHeading("Informacao do personagem");
		System.out.println(player.name + "\tHP: " + player.hp + "/" + player.maxHP);
		printSeparator(20);
		
		// Player xp and gold
		System.out.println("XP: " + player.xp + "\tOuro: " + player.getGold());
		printSeparator(20);
		
		// # Of pots
		System.out.println("# de pocoes: " + player.getPots());
		printSeparator(20);
		
		// Printing the chosen traits
		if (player.getNumAtkUpgrades() > 0) {
			System.out.println("Caracteristica Ofensiva: " + player.atkUpgrades[player.getNumAtkUpgrades() - 1]);
		}
		
		// Printing the chosen traits
		if (player.getNumDefUpgrades() > 0) {
			System.out.println("Caracteristica Defensiva: " + player.defUpgrades[player.getNumDefUpgrades() - 1]);
		}
		
		anythingToContinue();
	}
	
	// Shopping / Encountering a travelling trader
	@Override
	public void shop() {
		clearConsole();
		printHeading("Voce encontra um estranho misterioso. \nEle oferece algo a voce:");
		int price = (int) (Math.random() * (10 + player.getPots() * 3) + 10 + player.getPots());
		System.out.println("- Pocao Magica: " + price + " ouro.");
		printSeparator(20);
		
		// Ask the player to buy one
		System.out.println("Voce quer comprar um?\n(1) Sim!\n(2) Nao, obrigado(a).");
		int input = readInt("-> ", 2);
		
		// Check if player wants to buy
		if (input == 1) {
			clearConsole();
			// Check if player has enough gold
			if (player.getGold() >= price) {
				printHeading("Voce comprou uma pocao magica por " + price + "ouro.");
				player.setPots(player.getPots() + 1);
				player.setGold(player.getGold() - price);
			} else {
				printHeading("Voce nao possui ouro o suficiente para comprar isso...");
			}
			
			anythingToContinue();
		}
	}
	
	// Taking a rest
	@Override
	public void takeRest() {
		clearConsole();
		if(player.getRestsLeft() >= 1) {
			printHeading("Voce deseja descansar? (" + player.getRestsLeft() + " descanso(s) restante(s)).");
			System.out.println("(1) Sim\n(2) Nao, agora nao.");
			
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
					System.out.println("Voce descansou e recuperou ate " + hpRestored + " de vida.");
					System.out.println("Agora voce esta com " + player.hp + "/" + player.maxHP + " de vida.");
					player.setRestsLeft(player.getRestsLeft() - 1);
				}
				
			} else {
				System.out.println("Voce esta com a saude completa. Nao precisa descansar agora!");
			}
			
			anythingToContinue();
		}
	}
	
	// Creating a random battle
	@Override
	public void randomBattle() {
		clearConsole();
		printHeading("Voce encontrou uma criatura mal-intencionada. Voce tera que lutar contra ela!");
		anythingToContinue();
		
		// Creating new random enemy
		battle(new Enemy(ENEMIES[(int)(Math.random() * ENEMIES.length )], player.xp));
	}
	
	// The main BATTLE method
	@Override
	public void battle(Enemy enemy) {
		// Main battle loop
		while (true) {
			clearConsole();
			printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHP);
			printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHP);
			System.out.println("Escolha uma acao:");
			printSeparator(20);
			System.out.println("(1) Lutar\n(2) Usar Pocao\n(3) Fugir");
			int input = readInt("-> ", 3);
			
			// React accordingly to player input
			if (input == 1) {
				// FIGHT
				// Calculate the dmg and dmgTook (dmg enemy deals to player)
				int dmg = player.attack() - enemy.defend();
				int dmgTook = enemy.attack() - player.defend();
				// Check that dmg and dmgTook isn't negative
				if (dmgTook < 0) {
					// Add some dmg if the player defends very well
					dmg -= dmgTook / 2;
					dmgTook = 0;
				}
				if (dmg < 0) {
					dmg = 0;
				}
				// Deal dmg to both parties
				player.hp -= dmgTook;
				enemy.hp -= dmg;
				
				// Print the info of this battle round
				clearConsole();
				printHeading("BATALHA");
				System.out.println("Voce causou " + dmg + " de dano ao " + enemy.name + ".");
				printSeparator(15);
				System.out.println("O " + enemy.name + " causou " + dmgTook + " de dano a voce.");
				anythingToContinue();
				// Check if the player is still alive or dead
				if (player.hp <= 0) {
					playerDied(); // Method to end the game
					break;
				} else if (enemy.hp <= 0) {
					// Tell the player won
					clearConsole();
					printHeading("Voce derrotou o " + enemy.name + "!");
					// Increase	player xp
					player.xp += enemy.xp;
					System.out.println("Voce ganhou " + enemy.xp + " XP!");
					anythingToContinue();
					break;
				}
			} else if (input == 2) {
				// Use Potion
				clearConsole();
				if (player.getPots() > 0 && player.hp < player.maxHP) {
					// Player CAN take a potion
					// Make sure player wants to drink the potion
					System.out.println("Voce quer beber a pocao? (" + player.getPots() + " restante).");
					System.out.println("(1) Sim\n(2) Nao, talvez mais tarde");
					
					input = readInt("-> ", 2);
					if (input == 1) {
						// Player actually took it
						player.hp = player.maxHP;
						clearConsole();
						printHeading("Voce bebeu uma pocao magica. Ela restaurou sua saude para " + player.maxHP);
						// Random drops
						boolean addRest = (Math.random() * 5 + 1 <= 2.25);
						int goldEarned = (int) (Math.random() * enemy.xp);
						
						if (addRest) {
							player.setRestsLeft(player.getRestsLeft() + 1);
							System.out.println("Voce ganhou a chance de descansar mais uma vez!");
						}
						
						if (goldEarned > 0) {
							player.setGold(player.getGold() + goldEarned);
							System.out.println("Voce coletou " + goldEarned + " ouro do cadaver do " + enemy.name);
						}
						anythingToContinue();
						break;
					}
				} else {
					// Player CANNOT take a potion
					printHeading("Voce nao tem pocoes ou ja esta com a saude completa.");
					anythingToContinue();
				}
			} else {
				// RUN AWAY
				clearConsole();
				// Check that player isn't in last act (final boss battle)
				if (act != 4) {
					// Chance of 35% to escape
					if (Math.random() * 10 + 1 <= 3.5) {
						printHeading("Voce fugiu do " + enemy.name + "!");
						anythingToContinue();
						break;
					} else {
						printHeading("Voce nao conseguiu escapar.");
						// Calculate damage the player take
						int dmgTook = enemy.attack();
						System.out.println("Com pressa, voce sofreu 0 " + dmgTook + " de dano!");
						anythingToContinue();
						// Check if player's still alive
						if (player.hp <= 0) {
							playerDied();
						}
					}
				} else {
					printHeading("VOCE NAO PODE FUGIR DO IMPERADOR MALVADO!");
					anythingToContinue();
				}
			}
		}
	}
	
	// Printing the main menu
	@Override
	public void printMenu() {
		clearConsole();
		printHeading(PLACES[place]);
		System.out.println("Escolha uma acao:");
		printSeparator(20);
		System.out.println("(1) Continue em sua jornada");
		System.out.println("(2) Informacao do personagem");
		System.out.println("(3) Sair do jogo");
	}
	
	// The final (last) battle of the entire game
	@Override
	public void finalBattle() {
		// Creating the Imperado Malvado and letting the player fight against him
		battle(new Enemy("IMPERADO MALVADO", 200));
		
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
		printHeading("Voce morreu...");
		printHeading("Voce ganhou " + player.xp + " XP em sua jornada. Tente ganhar mais da proxima vez!");
		printHeading("Obrigado por jogar o meu jogo. Espero que tenha gostado :)");
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
