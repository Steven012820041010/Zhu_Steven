
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
    GreenfootSound shootingSound  = new GreenfootSound("GunShotSound0.mp3");
    
    
    private int MAX_BOUNCE = 6;
    private int bounce;
    private int soundIndex = 0;
    
    public Bullet ()
    {
        shootingSound.setVolume(40);
        //shootingSound.play();
        bounce = 0;
    }
    
  
    public void act() 
    {
        move(30);
        bounce();
        removeBullet();
        
    }
    public void bounce()
    {
        if(isAtEdge() || isTouching(Wall.class))
        {
            
            turn(Greenfoot.getRandomNumber(10)+90);
            
            bounce++;
        }
       
          
    }
    
    public void removeBullet()
    {
        if (bounce>MAX_BOUNCE)
        {
            getWorld().removeObject(this);
        }
    }
    
    
        
}
