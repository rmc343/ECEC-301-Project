package wordgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
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
    public static WordInitializer wordInit = new WordInitializer();
  
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
    public static final String PRESIDENTS = "Presidents";
    public static final String STATES = "States";
    /*public static final String 
     public static final String
     public static final String*/
    
    // file paths
    public static final String RESOURCE_PATH = "resources/" ;
    public static final String ANTONYM_PATH = RESOURCE_PATH + "antonyms.txt";
    public static final String HOMONYM_PATH = RESOURCE_PATH + "homonyms.txt";
    public static final String SYNONYM_PATH = RESOURCE_PATH + "synotm.txt";
    public static final String CAPITAL_CITY_PATH = RESOURCE_PATH + "capital.txt";
    public static final String PERMUTATION_COUNT_PATH = RESOURCE_PATH + "wordlist.txt";
    public static final String SPANISH_PATH = RESOURCE_PATH + "antonyms.txt";
    public static final String FRENCH_PATH = RESOURCE_PATH + "antonyms.txt";
    public static final String PRESIDENTS_PATH = RESOURCE_PATH + "presidents.txt";
    public static final String STATES_PATH = RESOURCE_PATH + "states.txt";
    
    //**************************************************************************

    /* The list of word pairs will depend on which item has been selected using jComboBox1. 
     * If a * If Antonym is chosen, a list of antonym pairs is loaded. 
     * If Synonym is chosen, a list of synonym pairs is loaded. 
     * If Homonym is chosen, a list of homonym pairs is loaded. 
     * Additional word lists might teach French, German or Spanish. 
     */
// * * * CONSTRUCTORS * * * 
    public WordGame() throws FileNotFoundException {
        score = 0;
        theme = "Antonym";
        //listOfWordPairs = loadAntonyms();
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

    // When complete, chooses a  word pair at random, from the currently active word  List. 
    public Pair getRandomWordPair() {
      // Ramdomly chooses a word pair from the list named listOfWordPairs for the current game.  

        Random randomGenerator = new Random();
        int numberOfPairs = this.listOfWordPairs.size();
        int number = randomGenerator.nextInt(numberOfPairs);
        Pair<String, String> randomWordPair = this.listOfWordPairs.get(number);

        return randomWordPair;

    } // end of method
    
       

    public static HashMap<String,Game> loadGames() throws FileNotFoundException {
        
        HashMap<String, Game> games = new HashMap<>();
        HashMap<String, ArrayList<String>> questionAns = new HashMap<>();
        ArrayList<String> questions = new ArrayList<>();
        ArrayList<String> answers = new ArrayList<>();
        Map<String,Content> map = new HashMap<>();
       
        map.put(ANTONYM,new Content(new File(ANTONYM_PATH),ANTONYM));
        map.put(SYNONYM,new Content(new File(SYNONYM_PATH),SYNONYM));
        map.put(PERMUTATION_COUNT,new Content(new File(PERMUTATION_COUNT_PATH),PERMUTATION_COUNT));
        map.put(CAPITAL_CITY,new Content(new File(CAPITAL_CITY_PATH),CAPITAL_CITY));
        map.put(HOMONYM,new Content(new File(HOMONYM_PATH),HOMONYM));
        map.put(PRESIDENTS,new Content(new File(PRESIDENTS_PATH),PRESIDENTS));
        map.put(STATES,new Content(new File(STATES_PATH),STATES));
        
        
        wordInit.setMap(map);
               
        //**************** ANTONYM GAME****************************************
        
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(ANTONYM)));

        Game game = new Game(ANTONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************

        //**************** HOMOYNM GAME****************************************
        
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(HOMONYM)));
        
        game = new Game(HOMONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        
        //**********************************************************************

        //**************** SYNONYM GAME****************************************
        
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(SYNONYM)));
        
        game = new Game(SYNONYM, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        
        //**********************************************************************
        
        
        //**************** CAPITAL CITIES GAME**********************************
        
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(CAPITAL_CITY)));
        
        game = new Game(CAPITAL_CITY, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************
        
        //**************** PERMUTATIONS COUNT GAME******************************
        
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(PERMUTATION_COUNT)));
        
        game = new Game(PERMUTATION_COUNT, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************
        
        //**************** PRESIDENTS GAME******************************
        
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(PRESIDENTS)));
        
        game = new Game(PRESIDENTS, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************
        
           //**************** STATES GAME******************************
        
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(STATES)));
        
        game = new Game(STATES, questionAns.get(Q), questionAns.get(A));
        games.put(game.getName(), game);
        //**********************************************************************
        
        return games;
    }

    public static HashMap<String, ArrayList<String>> getQuestionAndAnswers(List<Pair<String,String>> list) {

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
