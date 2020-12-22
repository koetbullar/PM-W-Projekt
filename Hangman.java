import java.util.Scanner ;
import java.lang.Math;
public class Hangman {
   public static void main(String[] args) {
       
       


      gameLoop();
     
   }

   public static String word(){
     String wordList[] =  { "Hallo", "Biologie", "Informatik", "Mertcan", "Weihnachten"};
     int rand = (int) (Math.random() * wordList.length - 1  );
     return wordList[rand];
     
   }

   public static String createUnderline(String givenWord) {
     String underline = "";
     for ( int i = 0; i < givenWord.length(); i++) {
       underline += "*";
     }
     return underline;
   }
   public static String createNewStatus (char userentry, String givenWord, String underline){
    char [] newUnderline = underline.toCharArray();
    String newStatus ;
    for ( int i = 0; i < givenWord.length(); i++ ) {
         if ( givenWord.charAt(i) == userentry) {
             newUnderline[i] = userentry;
           
              
         }
       }
        newStatus = new String(newUnderline);
       return newStatus;

   }

   public static void gameLoop() {
    Scanner in = new Scanner(System.in); 
    
   
    int MaxErrors = 5;
    int failedAttempts = 0;
       


    String givenWord = word();
    String currentStatus = createUnderline(givenWord);
    
    System.out.println(createUnderline(givenWord));
    while (givenWord.equals(currentStatus) == false && failedAttempts <= MaxErrors ) {
      char userEntry = in.next().charAt(0);
      currentStatus = createNewStatus(userEntry, givenWord,currentStatus);
      System.out.println(currentStatus);
      //Hier kommt der Hangman hin
      if (givenWord.contains("" + userEntry) == false ) {
          failedAttempts ++;
      }
    }
    if(givenWord.equals(currentStatus)) {
        System.out.println("Congratulations");
        
    }else System.out.println("Looser");
    
       
         
       }
       
   
}
