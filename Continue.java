import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Return to the TitlePage after pressing the Continue button
 * 
 * @author Steven Zhu
 * @version 2021.1.7
 */
public class Continue extends Button
{
    /**
     * 
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
