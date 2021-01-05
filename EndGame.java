import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EndGame extends World
{

    /**
     * Constructor for objects of class EndGame.
     * 
     */
    GreenfootImage first = new GreenfootImage("First Win.png");
    GreenfootImage second = new GreenfootImage("Second Win.png");
    public MyWorld world;
    Label scoreLabel1 = new Label (world.score1, 35);
    Label scoreLabel2 = new Label (world.score2, 35);
    
    Continue con = new Continue();
    public boolean firstOrSecond;
    public EndGame(boolean firstOrSecond)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 748, 1); 
        world = new MyWorld();
        this.firstOrSecond = firstOrSecond; 
        setBackgroundImage();
        addObject(con,494,657);
        
        
        
    }
    public void act()
    {
       
    }
    
    public void setBackgroundImage()
    {
        if (firstOrSecond)
        {
            setBackground(first);
            addObject(scoreLabel1,628,519);
            addObject(scoreLabel2,450,519);
        }
        else
        {
            setBackground(second);
            addObject(scoreLabel1,460,527);
            addObject(scoreLabel2,640,527);
        }
    }
        
 
}
