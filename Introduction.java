import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Introduction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Introduction extends World
{

    /**
     * Constructor for objects of class Introduction.
     * 
     */
    GreenfootImage player1 = new GreenfootImage("TankModel.png");
    GreenfootImage player2 = new GreenfootImage("Tank2Model.png");
    
    BackArrow arrow;
    
    Label back;
    
    public Introduction()
    {     
        // Create a new world with 1000x668 cells with a cell size of 1x1 pixels.
        super(1000, 668, 1); 
        
        //Initialize 
        arrow = new BackArrow();
        arrow.setRotation(180);
        back = new Label("Back", 35);
        
        //Scale two tank
        player1.scale(65,45);
        player2.scale(65,45);
        
        //Display object
        addObject(arrow,63,40);
        addObject(back,65,70);
        getBackground().drawImage(player1,120,200);
        getBackground().drawImage(player2,845,200);
    }
    
}
