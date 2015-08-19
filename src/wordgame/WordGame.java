package wordgame;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;
import java.util.TreeMap;

public class WordGame {

// A game has  a score and a collection of word pairs in a List to challenge the player. 
    public int score;
    public List<Pair> listOfWordPairs; // The challenge questions for the player to solve. 
    public String theme;     // Antonym, Synonym, Homonym, French, Spanish, etc
    public int crystalCount;
    
// Static Strings***************************************************************
    public static final String Q = "Q";
    public static final String A = "A";
// game names
    public static final String ANTONYM = "Antonym";
    public static final String HOMONYM = "Homonym";
    public static final String SYNONYM = "Synonym";
    public static final String CAPITAL_CITY = "Capital City";
    public static final String PERMUTATION_COUNT = "Permutation Count";
    public static final String SPANISH = "Spanish";
    public static final String FRENCH = "French";
    /*public static final String 
     public static final String
     public static final String*/
    //**************************************************************************

    /* The list of word pairs will depend on which item has been selected using jComboBox1. 
     * If a * If Antonym is chosen, a list of antonym pairs is loaded. 
     * If Synonym is chosen, a list of synonym pairs is loaded. 
     * If Homonym is chosen, a list of homonym pairs is loaded. 
     * Additional word lists might teach French, German or Spanish. 
     */
// * * * CONSTRUCTORS * * * 
    public WordGame() {
        score = 0;
        theme = "Antonym";
        listOfWordPairs = loadAntonyms();
        loadGames();
        crystalCount = 10;
    } // Default Constructor loads antonyms. 

//  Can select different lists of listOfWordPairs with this next constructor. 
    public WordGame(List<Pair> useThisWordList) {

        score = 0;
        listOfWordPairs = useThisWordList;

    } //  Can select different lists for listOfWordPairs. 

    public void playGame() {

        boolean gameOver = false; //Game ends when you earn 100 points or achieve -50.
        boolean correctAnswer;   // Used to check if the user answer is correct. 
        Scanner keyboard = new Scanner(System.in);
        int turnNumber = 0;

        while (!gameOver) {
            Pair<String, String> nextPair = this.getRandomWordPair();
            turnNumber++;
            String testWord = nextPair.getFirst();
            String answer = nextPair.getSecond();
            String hint = answer.substring(0, 2).toUpperCase(); // Show the first two letters. 

            System.out.printf("\n%2d. Find an antonym for %s that starts with %s.\n", turnNumber, testWord.toUpperCase(), hint);
            System.out.printf("Antonym: ");
            String userGuess = keyboard.nextLine().toLowerCase();

            correctAnswer = userGuess.equalsIgnoreCase(answer);

            this.score = (correctAnswer ? score + 10 : score - 20); // Get the new score.
            if (correctAnswer) {
                System.out.println("Correct! Your score jumps up to: " + score);
            } else {
                System.out.println("WRONG! Your score has dropped to: " + score);
            }

            // Test to see if the game should end.  
            if (score <= -50) {
                System.out.println("GAME OVER! - That was PATHETIC!\n");
                break;
            }
            if (score >= 100) {
                System.out.println("AWESOME! - You must be a GENIUS!\n");
                break;
            }

        } // end of for loop
    } // end of playGame method

    /* RETIRED - Not used!
     Chooses an antonym at random, from the List named "listOfWordPairs" used by the calling game object.
     public  Pair getRandomAntonym() {
     // Ramdomly chooses a word pair from the list named listOfWordPairs for the current game.  
       
     Random randomGenerator = new Random();
     int numberOfAntonymPairs = this.listOfWordPairs.size();
     
     int number  =randomGenerator.nextInt( numberOfAntonymPairs );  
     
     Pair<String, String> randomAntonym =   (number==0) ? new Pair<>("good", "bad") :  new Pair<>("rich", "poor") ;
     
     randomAntonym = this.listOfWordPairs.get(number);
     
     return randomAntonym;
     
     } // end of method
     */
    // When complete, chooses a  word pair at random, from the currently active word  List. 
    public Pair getRandomWordPair() {
      // Ramdomly chooses a word pair from the list named listOfWordPairs for the current game.  

        Random randomGenerator = new Random();
        int numberOfPairs = this.listOfWordPairs.size();
        int number = randomGenerator.nextInt(numberOfPairs);
        Pair<String, String> randomWordPair = this.listOfWordPairs.get(number);

        return randomWordPair;

    } // end of method

    public static List<Pair> loadAntonyms() {
     // Builds a set of antonym pairs, and returns it as an ArrayList.
        // By using a set, we can avoid duplicates.  
        Set<Pair> antonyms = new TreeSet<>();
// 1a. Start with an example Pair of listOfWordPairs.    
        Pair<String, String> examplePair1 = new Pair<>("good", "bad");
        Pair<String, String> examplePair2 = new Pair<>("rich", "poor");

// 1b. Add antonym pairs to the set "listOfWordPairs" to use in the game.  Here are a few to get you started. 
        antonyms.add(examplePair1);
        antonyms.add(examplePair2);

        // 1c. Here are a few more. 
        antonyms.add(new Pair("beautiful", "ugly"));
        antonyms.add(new Pair("same", "different"));
        antonyms.add(new Pair("summer", "winter"));
        antonyms.add(new Pair("throw", "catch"));
        antonyms.add(new Pair("night", "day"));
        antonyms.add(new Pair("dark", "light"));
        antonyms.add(new Pair("shiny", "dull"));
        antonyms.add(new Pair("smooth", "rough"));

  // If we accidentally try to add the same pair twice, no problem. 
        // A set can only have one copy of each pair!
        antonyms.add(new Pair("throw", "catch")); // Woops, we already added this! No problem!

  // 2. Add at least 40 new  pairs per team!!!
        // Add your code here. 
        List<Pair> result = new ArrayList(antonyms); // Conversion constructor. 
        return result;

    } // end of method

// See for example, thee word lists. 
// http://examples.yourdictionary.com/examples-of-antonyms-synonyms-and-homonyms.html
    public static List<Pair> loadHomonyms() {
     // Builds a set of homonym pairs, and returns it as an ArrayList.
        // By using a set, we can avoid duplicates.  

        Set<Pair> homonyms = new TreeSet<>();
// 1a. Start with an example Pair of listOfWordPairs.    
        Pair<String, String> examplePair1 = new Pair<>("affect", "effect");
        Pair<String, String> examplePair2 = new Pair<>("aisle", "isle");
        Pair<String, String> examplePair3 = new Pair<>("allowed", "aloud");

// 1b. Add  pairs to the set of homonyms to use in the game.  Here are a few to get you started. 
        homonyms.add(examplePair1);
        homonyms.add(examplePair2);
        homonyms.add(examplePair3);
        homonyms.add(new Pair<>("ark", "arc"));
        homonyms.add(new Pair<>("base", "bass"));
        homonyms.add(new Pair<>("beech", "beach"));
        homonyms.add(new Pair<>("cereal", "serial"));

  // 1c. Woops! Here is a duplicate, but that is OK because we are adding it to a set!
        // If we accidentally try to add the same pair twice, no problem. 
        homonyms.add(new Pair<>("allowed", "aloud"));
        homonyms.add(new Pair<>("allowed", "aloud"));
        homonyms.add(new Pair<>("allowed", "aloud"));

  // 2. Add at least 40 new  pairs per team!!!
        // Add your code here. 
        List<Pair> result = new ArrayList(homonyms); // Conversion constructor. 
        return result;

    } // end of method

  // Add methods for additional word list pairs here.  
    public static List<Pair> loadSynonyms() {
     // Builds a set of synonym pairs, and returns it as an ArrayList.
        // By using a set, we can avoid duplicates.  
        Set<Pair> synonyms = new TreeSet<>();

// 1b. Add  pairs to the set of homonyms to use in the game.  Here are a few to get you started. 
        synonyms.add(new Pair<>("annihilation", "destruction"));
        synonyms.add(new Pair<>("destitute", "impoverished"));
        synonyms.add(new Pair<>("enormous", "gigantic"));
        synonyms.add(new Pair<>("sleepy", "drowsy"));

  // 2. Add at least 40 new  pairs per team!!!
        // Add your code here. 
        List<Pair> result = new ArrayList(synonyms); // Conversion constructor. 
        return result;

    } // end of method

    public static List<Pair> loadCapitalCities() {
     // Builds a map of capital cities and returns it as an ArrayList of Pairs.
        // By using a set, we can avoid duplicates.  
        Map<String, String> capitalCities = new TreeMap<>();
// THIS ONE MUST BE DONE USING A MAP!!

// 1b. Add  country --> capital city data.   Here are a few to get you started. 
        capitalCities.put("USA", "Washington");
        capitalCities.put("Canada", "Ottawa");
        capitalCities.put("England", "London");
        capitalCities.put("France", "Paris");

  // 2. Add at least 40 new capital cities per team!!!
        // Add your code here. 
        List<Pair> result = new ArrayList();
        Set<String> countries = capitalCities.keySet();
        for (String country : countries) {
            result.add(new Pair<String, String>(country, capitalCities.get(country)));

        }
        return result;

    } // end of method

    public static List<Pair> loadPermutationCounts() {
     // Builds a set of words and player must findnthe numebr of distinct pemutations.
        // By using a set, we can avoid duplicates.  
        Map<String, String> permutationCounts = new TreeMap<>();

// 1b. Add  word --> permutation count data.   Here are a few to get you started. 
        permutationCounts.put("Cat", "" + 6);
        permutationCounts.put("Java", "" + 12);
        permutationCounts.put("Banana", "" + 60);
        permutationCounts.put("Dragon", "" + 720);  // We'll convert the integers to strings for consistency. 

  // 2. Add at least 40 new permutation counts here.
        // Also write a method to find those counts, instead of caluclating them manually.
        // Add your code here. 
        List<Pair> result = new ArrayList();
        Set<String> words = permutationCounts.keySet();
        for (String word : words) {
            result.add(new Pair<String, String>(word, permutationCounts.get(word)));

        }
        return result;

    } // end of method

    public static HashMap<String,Game> loadGames() {
        
        HashMap<String, Game> games = new HashMap<>();
        HashMap<String, ArrayList<String>> questionAns = new HashMap<>();
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();

        //**************** ANTONYM GAME****************************************
        List<Pair> loaded = loadAntonyms();
        questionAns = getQuestionAndAnswers(loaded);

        Game game = new Game(ANTONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************

        //**************** HOMOYNM GAME****************************************
        loaded = loadHomonyms();
        questionAns = getQuestionAndAnswers(loaded);
        
        game = new Game(HOMONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        
        //**********************************************************************

        //**************** SYNONYM GAME****************************************
        loaded = loadSynonyms();
        questionAns = getQuestionAndAnswers(loaded);
        
        game = new Game(SYNONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        
        //**********************************************************************
        
        
        //**************** CAPITAL CITIES GAME**********************************
        loaded = loadCapitalCities();
        questionAns = getQuestionAndAnswers(loaded);
        
        game = new Game(SYNONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************
        
        //**************** PERMUTATIONS COUNT GAME******************************
        loaded = loadPermutationCounts();
        questionAns = getQuestionAndAnswers(loaded);
        
        game = new Game(SYNONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************
        
        return games;
    }

    public static HashMap<String, ArrayList<String>> getQuestionAndAnswers(List<Pair> list) {

        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        HashMap map = new HashMap<>();

        for (Pair pair : list) {
            questions.add((String) pair.getFirst());
            answers.add((String) pair.getSecond());
        }

        map.put(Q, questions);
        map.put(A, answers);

        return map;
    }

} // end of class
