package wordgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

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
    private Map<String,Content> contentMap= new HashMap<>();
    
    public WordInitializer(){
        
    }
    
    public WordInitializer(List<Content> contentList){
        for(Content content : contentList){contentMap.put(content.getName(), content);}
    }

    private ArrayList<String> readContent(String name) throws FileNotFoundException{
        
        ArrayList<String> lines = new ArrayList<>();
        File file = contentMap.get(name).getFile();
        scanner = new Scanner(file);
        
        while(scanner.hasNext()){
            lines.add(scanner.nextLine());
        }
        scanner.close();
        
        return lines;
    }
    
    public Map<String,Content> getMap(){return contentMap;}
    
    public void setMap(Map map){contentMap = map;}
    
    public List<Pair<String,String>> parseContent(Content content) throws FileNotFoundException{
        
        String name = content.getName();
        content.setLines(readContent(content.getName()));
        List<Pair<String,String>> pairList = new ArrayList<>();
        
             switch (name) {
            case WordGame.ANTONYM:
                String prevWord = "";
                boolean isQuestion = true;
                /*for (int i = 0 ; i <content.getLines(); i++) {
                    
                     if(pairList.size()>50) {
                        break;
                    }
                    
                     if(isQuestion){
                         
                         if(!word.contains("\t")){
                             prevWord = word.split(",")[0];
                             System.out.println(prevWord);
                             isQuestion = false;
                         }
                     }else{ 
                         word = word.split(",")[0];
                         word = word.replace("\t", "");
                         word = word.replace(",", "");
                         word = word.replace(" ", "");
                         pairList.add(new Pair<String,String>(prevWord,word));
                         isQuestion = true;
                     }
                }*/
                break;

            case WordGame.CAPITAL_CITY:
                break;

            case WordGame.FRENCH:
                break;

            case WordGame.HOMONYM:
                for (String word : content.getLines()) {
                    
                    if(pairList.size()>50) {
                        break;
                    }
                    if(rg.nextInt(10) == 0){
                    String homonynm = word.split(",")[1];
                    //System.out.println("homs: " +  word.split(","));
                    pairList.add(new Pair<String,String>( word.split(",")[0], homonynm));
                    }
                }
                break;

            case WordGame.PERMUTATION_COUNT:
                for (String word : content.getLines()) {
                    
                    /*
                    if(pairList.size()>50) {
                        break;
                    }*/  
                    if(rg.nextInt(70) == 0){
                    BigInteger count = distinctPermuation(word);
                    pairList.add(new Pair<String,String>(word, count.toString()));
                    }
                    
                }
                break;

            case WordGame.SPANISH:
                break;

            case WordGame.SYNONYM:
                break;
            default:
                break;
        }

        return pairList;
    }
    
     public static BigInteger distinctPermuation(String word){
        int size = word.length();
        word = word.toUpperCase();
        Map<Character, Integer> map = new HashMap<>();
        BigInteger result;

        for (int i = 0; i < size; i++) {
            char ch = word.charAt(i);
            if (Character.isLetter(ch)) {
                if(map.containsKey(ch)){
                    
                    int count  = map.get(ch)+1;
                    map.put(ch, count);
                    
                    
                }else{
                     map.put(ch, 1);
                }
                
               
            }
        }
        
        long numer = factorial(size);
        long denom = 1;
        for(Integer num : map.values()){
            denom *= factorial(num);
        }
        
        result = BigInteger.valueOf((Long)(numer/denom));
        System.out.printf("The perm count of %s is %d\n",word, result);
        return result;
        
    }
    
    public static int factorial(int n){
       int result = 1;
       for (int i = 1; i <= n; i++) {
           result *= i;
       }
       //System.out.println("The factorial of "+ n +" is " + result);
       
       return result;
    }
}
