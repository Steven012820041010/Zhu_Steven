import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Write a description of class Music here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Music extends Button
{
    /**
     * Act - do whatever the Music wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootSound backgroundMusic = new GreenfootSound("Patakas World.wav");
    GreenfootImage[] soundImage = new GreenfootImage[2];
    static ArrayList<Integer> allIndex = new ArrayList<Integer>();
    int currIndex = 1;
    public Music()
    {
        initMusicFigure();
        setSoundImage();
        backgroundMusic.setVolume(30);
        //backgroundMusic.playLoop();
         
    }
    public void act() 
    {
        clickMusicButton();
    }  
    
    public void initMusicFigure()
    {
        soundImage[0] = new GreenfootImage("music on.png"); 
        soundImage[1] = new GreenfootImage("music off.png"); 
    }
    
    public void setSoundImage()
    {
        setImage(soundImage[currIndex]);
    }
    public int getIndex()
    {
        return currIndex;
    }
    
    public void halt()
    {
        backgroundMusic.stop();
    }
   
    
    public void clickMusicButton()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        allIndex.add(currIndex);
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
                //allIndex.add(currIndex);
                currIndex--;
            }
            
        }
        
        
    }
}
