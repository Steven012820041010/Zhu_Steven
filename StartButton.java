import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Start the game once press the button
 * 
 * @author Steven Zhu
 * @version 2021.1.6
 */
public class StartButton extends Button
{  
    GifImage gifImage = new GifImage("tenor.gif");
   
    /**
     * Constructor for objects of class StartButton.
     */
    public StartButton()
    {
        
    }
    
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
