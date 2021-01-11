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
    //Music soundFigure;
    

    public Introduction()
    {     
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 668, 1); 
        //soundFigure = new Music();
        arrow = new BackArrow();
        
        arrow.setRotation(180);
        addObject(arrow,63,40);
        back = new Label("Back", 35);
        addObject(back,65,70);
        addObject(TitlePage.soundFigure,946,60);
        Music music = new Music();
        music.setSoundImage();
        if (Music.allIndex.get(Music.allIndex.size()-1) == 1)
        {
            TitlePage.soundFigure.halt();
        }
       
        
        
        
    }
   
    public void act()
    {
        
    }
}
