import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Button
{
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GifImage gifImage = new GifImage("tenor.gif");
    
    public void act() 
    {
        setImage(gifImage.getCurrentImage());
        if (Greenfoot.mouseClicked(this)) 
        {
            Game game = new Game();
            Greenfoot.setWorld(game);
        }
    } 
    
}
