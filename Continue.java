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
     * Act - do whatever the Continue wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
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
