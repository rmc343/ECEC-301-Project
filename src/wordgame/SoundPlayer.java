package wordgame;

/*
 * 
*/
import java.io.File;
import java.io.IOException;
 
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
 
/**
 * This is an example program that demonstrates how to play back an audio file
 * using the Clip in Java Sound API.
 * @author www.codejava.net
 *
 */



public class SoundPlayer implements LineListener, Runnable{
  
    public final static String SOUND_PATH = "src/sound/";
    public final static String THEME_PATH =  SOUND_PATH + "theme.wav";
    public final static String WRONG_PATH =  SOUND_PATH + "wrong.wav";
    public final static String CORRECT_PATH =  SOUND_PATH + "correct.wav";
    public final static String WIN_PATH =  SOUND_PATH + "win.wav";
    
    
/**
     * this flag indicates whether the playback completes or not.
     */
    boolean playCompleted;
    String path;
     
    /**
     * Play a given audio file.
     * @param audioFilePath Path of the audio file.
     */
    
// Constructor which will play a specified wav file. 

public SoundPlayer(String audioFilePath){
    File audioFile = new File(audioFilePath);
    path = audioFilePath;
    try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.addLineListener(this);
            audioClip.open(audioStream);
    }
    
    catch(Exception e) {}
}
  
    public void play(){
    }
    
    public void run()
{ 
        String audioFilePath = path;
        SoundPlayer player = new SoundPlayer(audioFilePath);
        player.play(audioFilePath);
      
} // end of run method
    
    
    void play(String audioFilePath) {
        File audioFile = new File(audioFilePath);
 
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
 
            AudioFormat format = audioStream.getFormat();
 
            DataLine.Info info = new DataLine.Info(Clip.class, format);
 
            Clip audioClip = (Clip) AudioSystem.getLine(info);
 
            audioClip.addLineListener(this);
            
            audioClip.open(audioStream);
            String pathCompare = path;

            if (pathCompare.toLowerCase().contains("theme.wav")){
                audioClip.loop(Clip.LOOP_CONTINUOUSLY);  // This will cause it to play forever!
            }
          
            audioClip.start();
             
            while (!playCompleted) {
                // Wait for the playback to complete.
                try {
                    Thread.sleep(1000);
                } 
                 catch (InterruptedException ex) {
                    audioClip.stop();
                    // ex.printStackTrace();
                }
            }
             
            audioClip.close();
             
        } catch (UnsupportedAudioFileException ex) {
            System.out.println("The specified audio file is not supported.");
            ex.printStackTrace();
        } catch (LineUnavailableException ex) {
            System.out.println("Audio line for playing back is unavailable.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Error playing the audio file.");
            ex.printStackTrace();
        }
         
    }
     
    /**
     * Listens to the START and STOP events of the audio line.
     */
    @Override
    public void update(LineEvent event) {
        LineEvent.Type type = event.getType();
         
        if (type == LineEvent.Type.START) {
            System.out.println("Playback started.");
             
        } else if (type == LineEvent.Type.STOP) {
            playCompleted = true;
            System.out.println("Playback completed.");
         
        }
 
    }
 
    public static void main(String[] args) {
        String audioFilePath = THEME_PATH;
        Runnable r = new SoundPlayer(audioFilePath);
        Thread t = new Thread(r);
        t.start() ;
        // String audioFilePath = "src/sound/theme.wav";
        // SoundPlayer player = new SoundPlayer();
        // player.play(audioFilePath);
    }
 
}

