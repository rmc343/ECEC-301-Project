/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import javax.swing.Icon;

/**
 *
 * @author rcunni202
 */
public class Game <T> {
    
    private String name;
    private Set<T> questions = new HashSet<>();
    private Set<String> answers = new HashSet<>();
    private HashMap<T,String> answerMap = new HashMap<>();
    private Icon icon;
       
    
    //Constructors*************************************************************
    
    //Default
    public Game(){
        
        this.name = "N/A";
        
    }
    
    //Other constructors
    public Game(String name, ArrayList<T> questions,ArrayList<String> answers){
        
        this.name = name;
         Set<T> questionSet = new HashSet<>(questions);
        this.questions = questionSet;
        Set<String> answerSet = new HashSet<>(answers);
        this.answers = answerSet;
                        
        for(int i = 0; i < answers.size(); i++){
            answerMap.put(questions.get(i), answers.get(i));
        }
               
        
    }
    public Game(String name, ArrayList<T> questions,ArrayList<String> answers,Icon icon){
        
        this.name = name;
         Set<T> questionSet = new HashSet<>(questions);
        this.questions = questionSet;
        Set<String> answerSet = new HashSet<>(answers);
        this.answers = answerSet;
                        
        for(int i = 0; i < answers.size(); i++){
            answerMap.put(questions.get(i), answers.get(i));
        }
        this.icon = icon;
        
        
    }
    //**************************************************************************
    
    //Accesors
    
    public String getName(){
        return name;
    }
    
    public Set<T> getQuestions(){
        
        return questions;
        
    }
    
    public Set<String> getAnswers(){
        return answers;
    }
    
    public HashMap<T,String> getAnswerKey(){
        return answerMap;
    }
    
       public Icon getIcon(){
        return icon;
    }
       
    //end Accessors   
   
    public T getRand(){
        
        Random rg = new Random();
        int num = rg.nextInt(answers.size());
        T rand = (T) questions.toArray()[num];
        return rand;
        
    }
     
    
}
