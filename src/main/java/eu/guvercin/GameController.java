package eu.guvercin;

public interface GameController {
	public void startGame();
	public void checkAct();
	public void randomEncounter();
	public void continueJourney();
	public void characterInfo();
    public void shop();
    public void takeRest();
    public void randomBattle();
    public void battle(Enemy enemy);
    public void printMenu();
    public void finalBattle();
    public void playerDied();
    public void gameLoop();
}
