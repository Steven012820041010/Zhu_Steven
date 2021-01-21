import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Show the score of each player and reveal the winner
 * 
 * @author Steven Zhu
 * @version 2021.1.7
 */
public class EndGame extends World
{

    /**
     * Constructor for objects of class EndGame.
     * 
     */
    Game world;
    boolean firstOrSecond;
    Continue con = new Continue();
    
    Label scoreLabel1 = new Label (world.score1, 35);
    Label scoreLabel2 = new Label (world.score2, 35);
    
    GreenfootImage first = new GreenfootImage("First Win.png");
    GreenfootImage second = new GreenfootImage("Second Win.png");
    
    
    /**
     * Constructor for objects of class EndGame.
     * 
     */
    public EndGame(boolean firstOrSecond)
    {    
        // Create a new world with 1000x748 cells with a cell size of 1x1 pixels.
        super(1000, 748, 1); 
        world = new Game();
        this.firstOrSecond = firstOrSecond; // Determine which tank wins
        setBackgroundImage(); //Set winning page
        setConImage(); //Add continue button
        
    }
    
    /**
     * Set the continue button 
     */
    public void setConImage()
    {
        if(firstOrSecond)
        {
            addObject(con,495,657);
        }else{
            addObject(con,510,665);
        }
    }
    
    /**
     * Set background image based on the boolean firstOrSecond
     */
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
