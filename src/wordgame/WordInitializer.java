package wordgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rcunni202
 */
public class WordInitializer {

    private Scanner scanner;
    private Random rg = new Random();
    private Map<String, Content> contentMap = new HashMap<>();

    public WordInitializer() {

    }

    public WordInitializer(List<Content> contentList) {
        for (Content content : contentList) {
            contentMap.put(content.getName(), content);
        }
    }

    private ArrayList<String> readContent(String name) throws FileNotFoundException {

        ArrayList<String> lines = new ArrayList<>();
        File file = contentMap.get(name).getFile();
        scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine().toString());
        }
        scanner.close();

        return lines;
    }

    public Map<String, Content> getMap() {
        return contentMap;
    }

    public void setMap(Map map) {
        contentMap = map;
    }

    public List<Pair<String, String>> parseContent(Content content) throws FileNotFoundException {

        String name = content.getName();
        content.setLines(readContent(content.getName()));
        List<Pair<String, String>> pairList = new ArrayList<>();

        switch (name) {
            case WordGame.ANTONYM:
                String prevWord = "";
                boolean isQuestion = true;
                int countt = 1;
                for (int i = 0; i < (content.getLines().size() - 1); i++) {

                    if (pairList.size() > 50) {
                        break;
                    }

                    if (isQuestion) {
                        String question = content.getLines().get(i);
                        question = question.split(",")[0];
                        question = question.replace("\t", "");
                        System.out.println(countt++ + ". ----------------------");
                        System.out.println(question);
                        String answer = content.getLines().get(i + 1);
                        answer = answer.split(",")[0];
                        answer = answer.replace("\t", "");
                        System.out.println(answer);
                        pairList.add(new Pair<String, String>(question, answer));
                        //System.out.println("--------------------------");
                        isQuestion = false;
                    } else {
                        isQuestion = true;
                    }

                }
                break;

            case WordGame.CAPITAL_CITY:
                  
                 for (String word : content.getLines()) {
                     
                         String question = word.split("\t")[0];
                         String answer = word.split("\t")[1];
                         System.out.println("---------------------");
                         System.out.println(question);
                         System.out.println(answer);
                         pairList.add(new Pair<String, String>(question, answer));
                     
                 }
                break;

            case WordGame.FRENCH:
                break;

            case WordGame.HOMONYM:
                for (String word : content.getLines()) {

                    if (pairList.size() > 50) {
                        break;
                    }
                    if (rg.nextInt(10) == 0) {
                        String homonynm = word.split(",")[1];
                        //System.out.println("homs: " +  word.split(","));
                        pairList.add(new Pair<String, String>(word.split(",")[0], homonynm));
                    }
                }
                break;

            case WordGame.PERMUTATION_COUNT:
                for (String word : content.getLines()) {

                    /*
                     if(pairList.size()>50) {
                     break;
                     }*/
                    if (rg.nextInt(70) == 0) {
                        BigInteger count = distinctPermuation(word);
                        pairList.add(new Pair<String, String>(word, count.toString()));
                    }

                }
                break;

            case WordGame.SPANISH:
                break;

            case WordGame.SYNONYM:
                 boolean isAnswer = false;
                for (int i = 0; i < content.getLines().size(); i++) {
                    
                    if (pairList.size() > 50) {
                        break;
                    }
                    
                     if (isAnswer) {
                         String question = content.getLines().get(i);
                         question = question.split(",")[0];
                         question = question.replaceAll("[0-9]","");
                         question = question.replace("\t","");
                         question = question.replace(" ","");
                         String answer = content.getLines().get(i+1);
                         answer = answer.split(",")[0];
                         answer = answer.replaceAll("[0-9]","");
                         answer = answer.replace("\t","");
                         answer = answer.replace(" ","");
                         
                         pairList.add(new Pair<String, String>(question, answer));
                         isAnswer = false; 
                     }else{
                         isAnswer = true;                                
                     }
                    
                }
                break;
            default:
                break;
        }

        return pairList;
    }

    public static BigInteger distinctPermuation(String word) {
        int size = word.length();
        word = word.toUpperCase();
        Map<Character, Integer> map = new HashMap<>();
        BigInteger result;

        for (int i = 0; i < size; i++) {
            char ch = word.charAt(i);
            if (Character.isLetter(ch)) {
                if (map.containsKey(ch)) {

                    int count = map.get(ch) + 1;
                    map.put(ch, count);

                } else {
                    map.put(ch, 1);
                }

            }
        }

        long numer = factorial(size);
        long denom = 1;
        for (Integer num : map.values()) {
            denom *= factorial(num);
        }

        result = BigInteger.valueOf((Long) (numer / denom));
        System.out.printf("The perm count of %s is %d\n", word, result);
        return result;

    }

    public static int factorial(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
       //System.out.println("The factorial of "+ n +" is " + result);

        return result;
    }
}
