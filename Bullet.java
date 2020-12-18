import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Actor
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private boolean remove;
    private GreenfootImage image;
    
    private int MAX_BOUNCES = 4;
    private int bounces = 0;
    
    public Bullet ()
    {        
    }

    public void act() 
    {
        move(20);
        
        bouncingOffWall();
        removeBullet();
    }
    
    public void bouncingOffWall()
    {
        if(isAtEdge())
        {
            turn(Greenfoot.getRandomNumber(90));
            bounces++;
        }
    }
    
    public void removeBullet()
    {
        if(bounces >= MAX_BOUNCES)
        {
            getWorld().removeObject(this);
        }
    }

   
    
    
    
        
}
