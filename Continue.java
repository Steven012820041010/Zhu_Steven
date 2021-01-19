import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Continue here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Continue extends Button
{
    /**
     * Return to the TitlePage after pressing the Continue button
     */
    public Continue()
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
