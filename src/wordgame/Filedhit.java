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
        Scanner in = new Scanner(new File(WordGame.ANTONYM_PATH));
        PrintWriter out = new PrintWriter(new File("temp.txt"));
        while(in.hasNext())
        {
            String x = in.nextLine();
            x = x.replaceAll("#a[0-9][0-9][0-9][0-9]", "");
            out.println(x);
            
        }
        in.close();
        out.close();
        in = new Scanner(new File("temp.txt"));
        out = new PrintWriter(new File(WordGame.ANTONYM_PATH));
        while(in.hasNext())
        {
            String x = in.nextLine();
            out.println(x);
            
        }
        in.close();
        out.close();
    }
    
}
