/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordgame;

import java.io.File;

/**
 *
 * @author rcunni202
 */
public class Song {
    
   private File file;
   private String name;
    
    public Song(File file, String name){
        
        this.file = file;
        this.name = name;
        
    }
    
    public  File getFile(){ return file;}
    
    public String getName(){return name;}
    
}
