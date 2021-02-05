import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hangman {
  public static void main(String[] args) throws FileNotFoundException {
    System.out.println("Willkommen bei Hangman");
    System.out.println("Sie haben 5 Versuche, das Richtige Wort zu erraten.");
    System.out.println("Schaffen Sie es nicht, haben Sie verloren!");
    gameLoop(0);
  }

  public static String word() throws FileNotFoundException {
    int listIndex = 0;
    int topic = 0;
    String filePath = "";
    System.out.println("Waehlen Sie jetzt das Themengebiet in dem Sie spielen wollen.\n");
    while (topic < 1 || topic > 3) {
      System.out.println("Geben Sie eine der folgenden Zahlen an:");
      System.out.println(" 1. Fussballer \n 2. Historische Personen \n 3. Skispringer");
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
    File file = new File(filePath);
    Scanner sc = new Scanner(file);
    List<String> wordList = new ArrayList<>();
    while (sc.hasNextLine()) {
      wordList.add(listIndex, sc.nextLine());
      listIndex++;
    }
    //System.out.println(wordList); // shows current words List, to be deleted later
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
    Scanner in = new Scanner(System.in);   
    int maxErrors = 8;
    int failedAttempts = 8;
    String givenWord = word();
    String currentStatus = createUnderline(givenWord);
    System.out.print("Ihr aktueller Score beträgt: ");
    System.out.println(showhighscore(failedAttempts, highscore));
    System.out.println("Bitte Tippen Sie einen Buchstaben ein");
    failedAttempts = 0;
    //System.out.println(createUnderline(givenWord));
    while (givenWord.equals(currentStatus) == false && failedAttempts < maxErrors) {
      createHangmanArt(failedAttempts);
      System.out.println(currentStatus);
      char userEntry = in.next().charAt(0);
      currentStatus = createNewStatus(userEntry, givenWord, currentStatus);
      if (givenWord.contains("" + userEntry) == false) {
        failedAttempts++;
      }
    }
    if (givenWord.equals(currentStatus) && failedAttempts < maxErrors) {
      System.out.println(currentStatus);
      System.out.println("Congratulations");
    } else {
      createHangmanArt(failedAttempts);
      System.out.println("Looser");
    }
    proceedGame(failedAttempts, highscore);
  }

  public static void proceedGame(int failedAttempts, int highscore) throws FileNotFoundException {
    if (failedAttempts == 8) {
      System.out.println("Wanna try again? Enter y! \nEnd Game? Enter n");
    }
    if (failedAttempts < 8) {
      System.out.println("Wanna continue? Enter y \nEnd Game? Enter n");
    }
    Scanner in = new Scanner(System.in);
    String playerDecision = in.nextLine();
    if (playerDecision.equals("y")) {
      gameLoop(showhighscore(failedAttempts, highscore));
    }
    else if (playerDecision.equals("n") ) {
      System.out.println("See you later my friend");
    } else {
      System.out.println("unvalid userentry");
      proceedGame(failedAttempts, highscore);
    }    
  }

  public static int showhighscore(int failedAttempts, int highscore) {
    for (int i = 8; i > failedAttempts; i--) {
      highscore += 10;
    }
    return highscore;
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

}
