package eu.guvercin;

// Class that does nothing but storing methods to print out every part of the story
public class Story {

	public static void printIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("Historia");
        LogicController.printSeparator(30);
        System.out.println("Voce e o ultimo homem em pe depois que sua vila foi atacada pelos capangas do imperador malvado.");
        System.out.println("Cada um de seus amigos, familiares e vizinhos foi assassinado. Voce esta em pe nas ruinas queimando.");
        System.out.println("Tudo o que voce quer agora e vinganca, entao voce comeca a planejar sua jornada para derrotar o imperador malvado e libertar as terras!");
        LogicController.anythingToContinue();
    }

    public static void printFirstActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ATO I - INTRODUCAO");
        LogicController.printSeparator(30);
        System.out.println("A medida que voce comeca sua jornada, comeca a viajar pelas florestas proximas para alcancar as montanhas eternas.");
        System.out.println("As montanhas eternas sao um lugar muito perigoso. Diz-se que ninguem voltou vivo de la.");
        System.out.println("\nDepois de um longo dia caminhando pelas florestas, voce finalmente chega as montanhas eternas.");
        System.out.println("Voce nao se importa com as coisas que ouviu sobre esse lugar perigoso e comeca sua jornada para derrotar o mal.");
        LogicController.anythingToContinue();
    }

    public static void printFirstActOutro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ATO I - CONCLUSAO");
        LogicController.printSeparator(30);
        System.out.println("Voce conseguiu! Voce atravessou as montanhas eternas e ainda esta vivo!");
        System.out.println("Ao descer a ultima colina, voce esta mais do que feliz em sentir o chao duro sob seus pes novamente.");
        System.out.println("\nVoce se sente fortalecido e a experiencia que ganhou permite que voce aprenda mais uma habilidade!");
        LogicController.anythingToContinue();
    }

    public static void printSecondActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ATO II - INTRODUCAO");
        LogicController.printSeparator(30);
        System.out.println("Voce comeca a viajar pelas terras deste campo uma vez bem povoado.");
        System.out.println("Voce coletou algum ouro dos monstros que matou ao longo do caminho.");
        System.out.println("Felizmente, voce sabe que de vez em quando um comerciante viajante passa por essas terras.");
        System.out.println("Voce sabe exatamente onde fica o castelo do imperador malvado, mas precisa atravessar essas terras assombradas primeiro...");
        LogicController.anythingToContinue();
    }

    public static void printSecondActOutro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ATO II - CONCLUSAO");
        LogicController.printSeparator(30);
        System.out.println("Voce conseguiu atravessar essas terras assombradas e ainda esta vivo!");
        System.out.println("Voce esta a apenas algumas horas de seu destino final; o castelo do Imperador Malvado!");
        System.out.println("Voce sabe que provavelmente nao podera descansar la, entao faz uma ultima pausa para restaurar a saude.");
        System.out.println("\nDepois de tudo o que voce viu, voce se sente mais confiante em aprender mais uma habilidade.");
        LogicController.anythingToContinue();
    }
        
    public static void printThirdActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ATO III - INTRODUCAO");
        LogicController.printSeparator(30);
        System.out.println("Voce ve o enorme castelo negro a sua frente.");
        System.out.println("Enquanto voce fica em frente aos portoes, sabe que nao ha como voltar atras.");
        System.out.println("Voce esta disfarcado como um mercenario e entra no castelo. Voce nao sabe quanto tempo resta antes que alguem desconfie.");
        System.out.println("Tudo o que voce pode fazer agora e lutar por sua vida e rezar para sair vencedor...");
        LogicController.anythingToContinue();
    }
        
    public static void printThirdActOutro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ATO III - CONCLUSAO");
        LogicController.printSeparator(30);
        System.out.println("Voce chegou tao longe. Voce derrotou todos os lacaios do Imperador Malvado.");
        System.out.println("Enquanto fica em frente a porta da sala do trono, sabe que nao ha como voltar atras.");
        System.out.println("Voce se lembra do seu poder perdido e restaura a saude.");
        System.out.println("Voce tem a chance de aprender uma ultima habilidade antes de entrar na sala do trono.");
        LogicController.anythingToContinue();
    }
        
    public static void printFourthActIntro() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("ATO IV - INTRODUCAO");
        LogicController.printSeparator(30);
        System.out.println("Voce entra na sala do trono do Imperador Malvado.");
        System.out.println("Ele olha diretamente nos seus olhos. Voce sente a escuridao ao seu redor.");
        System.out.println("Ele puxa a espada sagrada da escuridao, a arma mais poderosa conhecida pelo homem.");
        System.out.println("Tudo o que voce pode fazer agora e lutar por sua vida e rezar para sair vencedor...");
        LogicController.anythingToContinue();
    }
        
    public static void printEndWin(Player player) {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("Parabens, " + player.name + "! Voce derrotou o Imperador Malvado e salvou o mundo!");
        LogicController.printSeparator(30);
        System.out.println("Este jogo foi desenvolvido por Kaue Eduardo, APS - UNIP");
        System.out.println("Espero que voce tenha gostado!");
    }
    
    public static void prinEndLose() {
        LogicController.clearConsole();
        LogicController.printSeparator(30);
        System.out.println("Infelizmente, voce morreu. O Imperador Malvado te derrotou e voce nao conseguiu salvar o mundo!");
        LogicController.printSeparator(30);
        System.out.println("Este jogo foi desenvolvido por Kaue Eduardo, APS - UNIP");
        System.out.println("Espero que voce tenha gostado!");
    }
}
