import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Switch to the Introduction world after press the key button
 * 
 * @author Steven Zhu
 * @version 2021.1.7
 */
public class InfoButton extends Button
{
    /**
     * Act - do whatever the InfoButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public InfoButton()
    {
        
    }
    
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) 
        {
            Introduction in = new Introduction();
            Greenfoot.setWorld(in);
            
        }
    }    
}
