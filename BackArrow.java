import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Return to the TitlePage after press the BackArrow button
 * 
 * @author Steven Zhu
 * @version 2020.12.20
 */
public class BackArrow extends Button
{
    /**
     * Constructor for objects of class BackArrow.
     * 
     */
    public BackArrow()
    {
        
    }
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) 
        {
            TitlePage tP = new TitlePage();
            Greenfoot.setWorld(tP);
        }
    }    
}
