/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.Stack;
import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import static wordgame.WordGame.A;
import static wordgame.WordGame.ANTONYM;
import static wordgame.WordGame.ANTONYM_IMAGE_PATH;
import static wordgame.WordGame.Q;
import static wordgame.WordGame.getQuestionAndAnswers;
import static wordgame.WordGame.wordInit;

/**
 *
 * @author rcunni202
 */
public class Game{

    private String name;
    private Set<String> questions = new HashSet<>();
    private Set<String> answers = new HashSet<>();
    private HashMap<String, String> answerMap = new HashMap<>();
    private Stack<String> questionStack = new Stack<>();
    private ImageIcon icon;
    private static int ICON_WIDTH = 171;
    private static int ICON_HEIGHT = 400;
    

    //Constructors*************************************************************
    //Default
    public Game() {

        this.name = "N/A";

    }

    //Other constructors
    public Game(String name, ArrayList<String> questions, ArrayList<String> answers) {

        this.name = name;
        Set<String> questionSet = new HashSet<>(questions);
        this.questions = questionSet;
        Set<String> answerSet = new HashSet<>(answers);
        this.answers = answerSet;

        for (int i = 0; i < answers.size(); i++) {
            answerMap.put(questions.get(i), answers.get(i));
        }
        
        questionStack.addAll(answerMap.keySet());

    }

    public Game(String name, ArrayList<String> questions, ArrayList<String> answers, String icon) throws IOException {

        this.name = name;
        Set<String> questionSet = new HashSet<>(questions);
        this.questions = questionSet;
        Set<String> answerSet = new HashSet<>(answers);
        this.answers = answerSet;

        for (int i = 0; i < answers.size(); i++) {
            answerMap.put(questions.get(i), answers.get(i));
        }
        
        questionStack.addAll(answerMap.keySet());
        
        ImageIcon temp = new ImageIcon(icon);
        temp = resizeImage(ICON_WIDTH,ICON_HEIGHT,temp);
        this.icon = temp;

    }
    //**************************************************************************

    //Accesors
    public String getName() {
        return name;
    }

    public Set<String> getQuestions() {

        return questions;

    }

    public Set<String> getAnswers() {
        return answers;
    }

    public HashMap<String, String> getAnswerKey() {
        return answerMap;
    }

    public ImageIcon getIcon() {
        return icon;
    }
    
    public Stack<String> getQuestionStack(){return questionStack;}
    
    public void setQuestionStack(Stack<String> questionStack){this.questionStack =questionStack;}

    //end Accessors   
    public String getRand() {

        Random rg = new Random();
        int num = rg.nextInt(answers.size()-1);
        String rand = (String) questions.toArray()[num];
        return rand;

    }
    
     public void refresh() throws FileNotFoundException {
         
        HashMap<String, ArrayList<String>> questionAns = new HashMap<>(); 
         
        questionAns = getQuestionAndAnswers(wordInit.parseContent(wordInit.getMap().get(this.name)));
        
         questions =  new HashSet<>(questionAns.get(Q));
         answers = new HashSet<>(questionAns.get(A));
         for (int i = 0; i < answers.size(); i++) {
            answerMap.put(questionAns.get(Q).get(i), questionAns.get(A).get(i));
          }
        questionStack.addAll(answerMap.keySet());

    }

    public static ImageIcon convertToImage(String urlStr) throws IOException {
        ImageIcon icon = null;
        try {
            URL url = new URL(urlStr);
            BufferedImage img = ImageIO.read(url);
            icon = new ImageIcon(img);
            icon = resizeImage(ICON_WIDTH,ICON_HEIGHT,icon);
        } catch (MalformedURLException e) {
            System.out.println("\nLOADING!!!!!!!!");
        } catch (IOException e) {
            System.out.println("\nLOADING!!!!!!!!");
        }
        
        return icon;
    }
      
      public static ImageIcon resizeImage(int WIDTH, int HEIGHT, ImageIcon icon) {
         // Resizes  the image, if needed, so its width and height do not exceed the set limits.
         // Aspect ratio is preserved. 
       
         // 1. First, load the image file into a Buffered Image. 
        // Create  a Buffered image so we can  resize the image if necessary.   
        BufferedImage img = new BufferedImage(
                icon.getIconWidth(),
                 icon.getIconHeight(),
                  BufferedImage.TYPE_INT_RGB);
          Graphics g = img.createGraphics();
        // paint the Icon to the BufferedImage.
          icon.paintIcon(null, g, 0, 0);
          g.dispose();


         // 2. Handle the scaling. 
         // a. Don't scale if it fits inside the bounding rectangle already. 
         int originalWidth  = img.getWidth();   // System.out.println("\tThe original  width of this image is: " + originalWidth);
         int originalHeight = img.getHeight();  // System.out.println("\tThe original height of this image is: " + originalHeight);
 
         if (originalWidth <= WIDTH && originalHeight <= HEIGHT )  
             {  // ImageIcon theNewIcon = new ImageIcon( img ); 
                // this.imageLabel.setIcon(theNewIcon);
                return new ImageIcon(img); 
             }  // Image did not need scaling. 
           
         // b. Use this else block to scale. Find which dimension needs to be scaled the most. 
         else {
         double scaleRatioWidth = ((double) WIDTH) / originalWidth;
         double scaleRatioHeight = ((double) HEIGHT) / originalHeight;
         double scaleRatio = Math.min(scaleRatioWidth, scaleRatioHeight );
         
         int newWidth = (int) (scaleRatio*originalWidth);
         int newHeight = (int) (scaleRatio*originalHeight);

         
         Image scaledImage =  img.getScaledInstance(-newWidth, newHeight, Image.SCALE_SMOOTH);
         // See more here about getScaled Instance(). The negative argument assures the aspect will be preserved. 
         // http://docs.oracle.com/javase/8/docs/api/java/awt/Image.html
         
         return new ImageIcon(scaledImage);   
         }  // end of scaling block
  }    // end of method

}
