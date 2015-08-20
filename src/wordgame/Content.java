/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author rcunni202
 */
public class Content {
    
    private File file;
    private String name;
    private ArrayList<String> fileLines;
    
    public Content(File file, String name){
        this.file = file;
        this.name = name;
    }
    
    public File getFile(){return file;}
    public String getName(){return name;}
    public void setLines(ArrayList<String> lines){fileLines = lines;}
    public ArrayList<String> getLines(){return fileLines;}
    
}
