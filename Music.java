import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Play background music.
 * Stop the music by pressing the button and turn it on by pressing it one more time
 * 
 * @author Steven Zhu
 * @version 2020.12.23
 */
public class Music extends Button
{
    /**
     * Act - do whatever the Music wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static GreenfootSound backgroundMusic = new GreenfootSound("All Decked Out Tonight.mp3"); //Keep the old song play whenever the world is changing and won't create a new song
    GreenfootImage[] soundImage = new GreenfootImage[2]; //Stores "on" and "off" of sound figures
    static int currIndex = 1; //Record the index of the sound figure after changing the world in case the sound image is matched after return to the TitlePage world
    public Music()
    {
        initMusicFigure();
        setSoundImage();
        backgroundMusic.setVolume(15);
    }
    public void act() 
    {
        clickMusicButton(); //Keep detecting whether the button is pressed or not
    }  
    
    public void initMusicFigure()
    {
        soundImage[0] = new GreenfootImage("music on.png"); 
        soundImage[1] = new GreenfootImage("music off.png"); 
    }
    
    /**
     * Set the current sound image using variable currIndex
     */
    public void setSoundImage()
    {
        setImage(soundImage[currIndex]);
    }
    
    /**
     * Stop the music
     */
    public void halt()
    {
        backgroundMusic.stop();
    }
   
    /**
     * Turn on the music if it is not playing; otherwise, turn it on.
     */
    public void clickMusicButton()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(this))
        {
            
            if (currIndex==0)
            {
                setImage(soundImage[1]);
                backgroundMusic.stop();
                currIndex++;
            }
            else if (currIndex==1)
            {
                setImage(soundImage[0]);
                backgroundMusic.playLoop();
                currIndex--;
            }
            
        }
        
    }
}
