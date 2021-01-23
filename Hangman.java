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
	gameLoop();
  }

  public static String word() throws FileNotFoundException {
    int listIndex = 0;
    File file = new File("file.txt");
    Scanner sc = new Scanner(file);
    List<String> wordList = new ArrayList<>();
    while (sc.hasNextLine()) {
      wordList.add(listIndex, sc.nextLine());
      listIndex++;
    }
    System.out.println(wordList); // shows current words List, to be deleted later
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

  public static void gameLoop() throws FileNotFoundException {
    Scanner in = new Scanner(System.in);   
    int maxErrors = 5;
    int failedAttempts = 0;
    String givenWord = word();
    String currentStatus = createUnderline(givenWord);
    System.out.println("Bitte Tippen Sie einen Buchstaben ein");
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
    proceedGame(failedAttempts);
  }

  public static void proceedGame(int failedAttempts) throws FileNotFoundException {
    if (failedAttempts == 5) {
      System.out.println("Wanna try again? Enter 1! \n End Game? Enter 0");
    }
    if (failedAttempts < 5) {
      System.out.println("Wanna continue? Enter 1 \n End Game? Enter 0");
    }
    Scanner in = new Scanner(System.in);

    int playerDecision = in.nextInt();

    if (playerDecision == 1) {
      gameLoop();
    }
    if (playerDecision == 0) {
      System.out.println("See you later my friend");
    } else { 
      System.out.println("unvalid userentry");
      proceedGame(failedAttempts);
    }
    
  }
  

  public static void createHangmanArt(int failedAttempts) {
    if (failedAttempts == 0) {
      System.out.println("---------");
      System.out.println("|/      |");
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
    } 
    if (failedAttempts == 1) {
      System.out.println("--------");
      System.out.println("|/      |");
      System.out.println("|      ()");
      System.out.println("|        ");
      System.out.println("|        ");
      System.out.println("|        ");
    }
    if (failedAttempts == 2) {
      System.out.println("--------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|       |");
      System.out.println("|        ");
      System.out.println("|        ");
    }
    if (failedAttempts == 3) {
      System.out.println("--------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|      /|\\");
      System.out.println("|        ");
      System.out.println("|        ");
    }
    if (failedAttempts == 4) {
      System.out.println("--------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|      /|\\");
      System.out.println("|      / ");
      System.out.println("|        "); 
    }
    if (failedAttempts == 5) {
      System.out.println("--------");
      System.out.println("|/      |");
      System.out.println("|      ()"); 
      System.out.println("|      /|\\");
      System.out.println("|      / \\");
      System.out.println("|        "); 
    }
  }

}
