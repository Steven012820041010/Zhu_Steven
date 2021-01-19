import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BackArrow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BackArrow extends Button
{
    /**
     * Return to the TitlePage after pressing the BackArrow button
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
