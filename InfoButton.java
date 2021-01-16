import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class InfoButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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
            
            TitlePage.soundFigure.halt();
            TitlePage tP = new TitlePage();
            tP.removeObject(tP.soundFigure);
            Introduction in = new Introduction();
            Greenfoot.setWorld(in);
            
        }
    }    
}
