package eu.guvercin;

// Class that stores methods to print out every part of the story
public class Story {

    public static void printIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("Story");
        LogicController.printSeparator(30);
        System.out.println("You are the last man standing after your village was raided by the Evil Emperor's cavalries.");
        System.out.println("Every one of your friends, family, and neighbors was slaughtered. Your wife has taken, raped and killed.");
        System.out.println("Your son's head is hanging over the tree. You stand alone in the burning ruins.");
        System.out.println("Now, all you seek is revenge. You begin your journey without any planning to defeat the Evil Emperor and free the land!");
        LogicController.anythingToContinue();
    }

    public static void printFirstActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ACT I - Intro the FACE THE FEAR");
        LogicController.printSeparator(30);
        System.out.println("As your vengeance begins, you pass through nearby forests toward the Eternal Mountains.");
        System.out.println("The Eternal Mountains are a dangerous place. It is said that no one has returned alive. But, you are already a dead man.");
        System.out.println("\nAfter a long day of walking, you finally reach the mountain.");
        System.out.println("You don’t care about the rumors. You don't have anyting to lose on this point. You are determined to defeat evil or die on this holy effort, and begin your climb.");
        LogicController.anythingToContinue();
    }

    public static void printFirstActOutro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ACT I - FACE THE FEAR Completed");
        LogicController.printSeparator(30);
        System.out.println("You made it! You crossed the Eternal Mountains and lived to die another day");
        System.out.println("As you descend the last hill, you feel relieved to stand on solid ground again.");
        System.out.println("\nYou feel anger, rage, power, and the experience you gained allows you to learn a new skill!");
        LogicController.anythingToContinue();
    }

    public static void printSecondActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ACT II - Intro to SLAUGHTERED MONSTERS");
        LogicController.printSeparator(30);
        System.out.println("You travel through once-populated lands, now barren and cursed.");
        System.out.println("You collected some gold from the monsters you defeated, burned, slaughtered along the way.");
        System.out.println("Luckily, traveling slave miners are known to pass through these lands.");
        System.out.println("You know exactly where the Shadow Emperor's castle is—but first, you must cross the cursed wastelands...");
        LogicController.anythingToContinue();
    }

    public static void printSecondActOutro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ACT II - SLAUGHTERED MONSTERS completed");
        LogicController.printSeparator(30);
        System.out.println("You successfully crossed the cursed wastelands and survived!");
        System.out.println("You’re just a few hours away from your final destination: the Shadow Emperor’s Castle!");
        System.out.println("Knowing you won’t get another chance to rest, you take a final breath to restore your strength.");
        System.out.println("\nAfter everything you’ve seen, you feel confident enough to gain another skill.");
        LogicController.anythingToContinue();
    }

    public static void printThirdActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ACT III - Intro to STUBBORN TO CASTLE");
        LogicController.printSeparator(30);
        System.out.println("You see the massive dark castle in the distance.");
        System.out.println("As you stand before the gates, you know there is no turning back.");
        System.out.println("Disguised as slave miners, you enter the castle. You don’t know how long you have before someone suspects you.");
        System.out.println("All you can do now is fight for your life and hope to die like a man...");
        LogicController.anythingToContinue();
    }

    public static void printThirdActOutro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ACT III - STUBBORN TO CASTLE completed");
        LogicController.printSeparator(30);
        System.out.println("You've come so far. You've defeated all of the Shadow Emperor’s cavalry officer who is responsible for the nighmare raid on your village..");
        System.out.println("You take a break and start to eat some parts of the officers body to push your vengeance feelings.");
        System.out.println("Now you stand before the throne room gate with blood gold colours. There is no turning back.");
        System.out.println("You remember your strength and restore your health.");
        System.out.println("You now have the chance to gain one final skill before stepping inside...");
        LogicController.anythingToContinue();
    }

    public static void printFourthActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ACT IV - RESOLUTION");
        LogicController.printSeparator(30);
        System.out.println("You enter the Shadow Emperor’s throne room.");
        System.out.println("He stares directly into your eyes. You feel the darkness surrounding you.");
        System.out.println("He draws the Sacred Sword of Darkness—said to be the most powerful weapon known to man.");
        System.out.println("All you can do now is fight with all your might and pray to show a respectable fight...");
        LogicController.anythingToContinue();
    }

    public static void printEndWin(Player player) {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("Congratulations, " + player.name + "! You have defeated the Shadow Emperor and took your vengeance!");
        LogicController.printSeparator(30);
        System.out.println("This game was originally developed by Kaue Eduardo, APS - UNIP");
        System.out.println("English version & adaptation & story modifications by Mustafa Can Güvercin");
        System.out.println("Hope you enjoyed it!");
    }

    public static void prinEndLose() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("Sadly, you have fallen. The Shadow Emperor has defeated you.");
        System.out.println("As you bleed out, he smirks. 'I was there,' he says, and begins to describe your wife's last moments for his own amusement.");
        LogicController.printSeparator(30);
        System.out.println("This game was originally developed by Kaue Eduardo, APS - UNIP");
        System.out.println("English version & adaptation & story modifications by Mustafa Can Güvercin ");
        System.out.println("Hope you still had a good time!");
    }
}
