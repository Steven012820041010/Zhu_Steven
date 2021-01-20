import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start the game once press the button
 * 
 * @author Steven Zhu
 * @version 2021.1.6
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
            Greenfoot.setWorld(game);//Start the game
        }
    } 
    
}
