/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author rcunni202
 */
public class Filedhit {
    
    /**
     *
     * @param args
     */
    public static void main(String args[]) throws FileNotFoundException            
    {
        Scanner in = new Scanner(new File(WordGame.CAPITAL_CITY_PATH));
        PrintWriter out = new PrintWriter(new File("temp.txt"));
        while(in.hasNext())
        {
            String x = in.nextLine();
            System.out.println(x); 
            System.out.println(0);
            out.println(x);
            
        }
        in.close();
        out.close();
        in = new Scanner(new File("temp.txt"));
        out = new PrintWriter(new File(WordGame.CAPITAL_CITY_PATH));
        while(in.hasNext())
        {
            String x = in.nextLine();
            System.out.println(x);
            System.out.println(1);
            out.println(x);
            
        }
        in.close();
        out.close();
    }
    
}
