import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {
  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Willkommen bei Hangman! \n");
    //printLogo();
    System.out.println("Die Regeln:");
    System.out.println("[1] Sie haben 8 Versuche, das Richtige Wort zu erraten.");
    System.out.println("[2] Schaffen Sie es nicht, haben Sie verloren!");
    System.out.println("[3] Pro Fehler werden 10 Punkte abgezogen von den maximalen 80.\n");
    gameLoop(0);
  }

  public static String word() throws FileNotFoundException {
    int listIndex = 0;
    int topic = 0;
    String filePath = "";
    System.out.println("Waehlen Sie jetzt das Themengebiet in dem Sie spielen wollen.\n");
    while (topic < 1 || topic > 5) {
      System.out.println("Geben Sie eine der folgenden Zahlen an:");
      System.out.println(" 1. Fussballer \n 2. Historische Personen \n 3. Skispringer");
      System.out.println(" 4. Laender \n 5. Staedte");
      Scanner in = new Scanner(System.in);
      topic = in.nextInt();
    }
    if (topic == 1) {
      filePath = "words/fussballer.txt";
    }
    if (topic == 2) {
      filePath = "words/historische_personen.txt";
    }
    if (topic == 3) {
      filePath = "words/skipringer.txt";
    }
    if (topic == 4) {
      filePath = "words/land.txt";
    }
    if (topic == 5) {
      filePath = "words/stadt.txt";
    }
    File file = new File(filePath);
    Scanner sc = new Scanner(file);
    List<String> wordList = new ArrayList<>();
    while (sc.hasNextLine()) {
      wordList.add(listIndex, sc.nextLine());
      listIndex++;
    }
    int rand = (int) (Math.random() * wordList.size());
    return wordList.get(rand);
  }

  public static String createUnderline(String givenWord) {
    String underline = "";
    for (int i = 0; i < givenWord.length(); i++) {
      underline += "*";
    }
    return underline;
  }
  
  public static String createNewStatus(char userentry, String givenWord, String underline) {
    char [] newUnderline = underline.toCharArray();
    String newStatus;
    for (int i = 0; i < givenWord.length(); i++) {
      if (givenWord.charAt(i) == userentry) {
        newUnderline[i] = userentry; 
      }
    }
    newStatus = new String(newUnderline);
    return newStatus;
  }

  public static void gameLoop(int highscore) throws FileNotFoundException {  
    final int maxErrors = 8;
    int failedAttempts = 8;
    String givenWord = word();
    System.out.println(showhighscore(failedAttempts, highscore));
    String currentStatus = createUnderline(givenWord);
    failedAttempts = 0;
    while (givenWord.equals(currentStatus) == false && failedAttempts < maxErrors) {
      createHangmanArt(failedAttempts);
      System.out.println(currentStatus);
      char userEntry = checkUserEntry(currentStatus);
      currentStatus = createNewStatus(userEntry, givenWord, currentStatus);
      if (givenWord.contains("" + userEntry) == false) {
        failedAttempts++;
      }
    }
    if (givenWord.equals(currentStatus) && failedAttempts < maxErrors) {
      System.out.println(currentStatus);
      System.out.println("Sie haben das Wort: " + givenWord  + " erraten \n");
    } else {
      createHangmanArt(failedAttempts);
      System.out.println("Sie haben das Wort: "  + givenWord + " nicht erraten koennen \n");
    }
    proceedGame(failedAttempts, highscore);
  }

  public static void proceedGame(int failedAttempts, int highscore) throws FileNotFoundException {
    if (failedAttempts == 8) {
      System.out.println("Noch ein Versuch? \ny eingeben! \nSpiel beenden? \nn eingeben");
    }
    if (failedAttempts < 8) {
      System.out.println("Weiterspielen? \ny eingeben! \nSpiel beenden? \nn eingeben");
    }
    Scanner in = new Scanner(System.in);
    String playerDecision = in.nextLine();
    if (playerDecision.equals("y")) {
      gameLoop(showhighscore(failedAttempts, highscore));
    } else if (playerDecision.equals("n")) {
      System.out.println("Schon aufgegeben? Schwach!");
    } else {
      System.out.println("falsche Eingabe");
      proceedGame(failedAttempts, highscore);
    }    
  }

  public static int showhighscore(int failedAttempts, int highscore) {
    for (int i = 8; i > failedAttempts; i--) {
      highscore += 10;
    }
    System.out.print("Ihr aktueller Score betraegt: ");
    return highscore;
  }
  
  public static char checkUserEntry(String cStatus) {
    Scanner in = new Scanner(System.in);
    char userEntry = in.next().charAt(0);
    System.out.println("Tippen Sie einen Buchstaben ein\n");
    while (cStatus.indexOf(userEntry) > -1) {
        System.out.println("Den Buchstaben: " + userEntry + " haben Sie bereits erraten");
        userEntry = in.next().charAt(0);
    }
    return userEntry;
  }
      

  public static void createHangmanArt(int failedAttempts) {
    if (failedAttempts == 0) {
      System.out.println("");
      System.out.println("|/      ");
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
    } 
    if (failedAttempts == 1) {
      System.out.println("---------");
      System.out.println("|/       ");
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
    }
    if (failedAttempts == 2) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|        "); 
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
    }
    if (failedAttempts == 3) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
    }
    if (failedAttempts == 4) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|       |");
      System.out.println("|        ");
      System.out.println("|        "); 
    }
    if (failedAttempts == 5) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|      /|");
      System.out.println("|        ");
      System.out.println("|        "); 
    }
    if (failedAttempts == 6) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|      /|\\");
      System.out.println("|       ");
      System.out.println("|        "); 
    }
    if (failedAttempts == 7) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|      /|\\");
      System.out.println("|      / ");
      System.out.println("|        "); 
    }
    if (failedAttempts == 8) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|      /|\\");
      System.out.println("|      / \\");
      System.out.println("|        "); 
    }
  }

  public static void printLogo() {
      
  }
}
