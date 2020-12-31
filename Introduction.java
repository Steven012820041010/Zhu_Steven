import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Introduction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Introduction extends World
{

    /**
     * Constructor for objects of class Introduction.
     * 
     */
    BackArrow arrow;
    Label back;
    
    GreenfootSound backgroundMusic = new GreenfootSound("Patakas World.wav");
    GreenfootImage[] sound = new GreenfootImage [2];
    GreenfootImage currSoundImage;
    public Introduction()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 668, 1); 
        arrow = new BackArrow();
        arrow.setRotation(180);
        addObject(arrow,63,40);
        back = new Label("Back", 35);
        addObject(back,65,70);
        
        initMusicFigure();
        currSoundImage = sound[0];
        getBackground().drawImage(currSoundImage,910,23);
        backgroundMusic.setVolume(40);
        //backgroundMusic.playLoop();
        
        
    }
    public void clickMusicButton()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if (Greenfoot.mouseClicked(this))
        {
            
            if (currSoundImage == sound[0])
            {
                currSoundImage = sound[1];
                backgroundMusic.stop();
            }
            else if (currSoundImage == sound[1])
            {
                currSoundImage= sound[0];
                backgroundMusic.playLoop();
            }
            
        }
        getBackground().drawImage(currSoundImage,900,30);
    }
    
    public void initMusicFigure()
    {
        sound[0] = new GreenfootImage("music on.png");
        sound[1] = new GreenfootImage("music off.png");
    }
    
    public void act()
    {
        
    }
}
