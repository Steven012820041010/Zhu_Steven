import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Shoot down the tank to increase score
 * 
 * @author Steven Zhu
 * @version 2020.11.24
 */
public class Bullet extends Actor
{
    /**
     * Constructor for objects of class Introduction.
     * 
     */
    Game world;

    int numTouchTank; 
    boolean score = true;
    private int bounce;
    private final int MAX_BOUNCE = 8;
    private int soundIndex = 0;
    
    SimpleTimer timer = new SimpleTimer();
    GreenfootSound shootingSound  = new GreenfootSound("GunShotSound0.mp3");
    
    public Bullet ()
    {
        timer.mark();

        shootingSound.setVolume(40);
        shootingSound.play();
        
        numTouchTank = 0;
        bounce = 0;
    }
    
    public void act() 
    {
        fire(30,8);
        bounce();
        collisionWithTank();
        removeBullet();
        
    }
    
    /** 
     * Set the initial speed of the bullet to "init", and slows down to speed "end" after 50 milliseconds
     */
    public void fire(int init, int end)
    {
        if(timer.millisElapsed()<60)
        {
            move(init);
        }else{
            move(end);
        }
    }
    
    /**
     * Bounce at the edge of the Game world
     */
    public void turnAngleAtEdge()
    {
        //Right
        if(getRotation()==0)
        {
            setRotation(180);
        }
        
        //Left
        if(getRotation()==180)
        {
            setRotation(0);
        }
        
        //Up
        if(getRotation()==270)
        {
            setRotation(90);
        }
        
        //Down
        if(getRotation()==90)
        {
            setRotation(270);
        }
        
        //Top-Left
        if (getRotation()>180 && getRotation()<270 && getY() <= 5)
        {
            setRotation(180-(getRotation()-180));
        }
        if (getRotation()>180 && getRotation()<270 && getX() <= 5)
        {
            setRotation(270+(270-getRotation()));
        }
        
        //Bottom-Left
        if (getRotation()>90 && getRotation()<180 && getY() >= 795) 
        {
            setRotation(180+(getRotation()-90));
        }
        if (getRotation()>90 && getRotation()<180 && getX() <= 5) 
        {
            setRotation(90-(getRotation()-90));
        }
        
        //Bottom-Right
        if (getRotation()>0 && getRotation()<90 && getY() >= 795)
        {
            setRotation(360-getRotation());
        }
        if (getRotation()>0 && getRotation()<90 && getX() >= 1195)
        {
            setRotation(90+(90-getRotation()));
        }
        
        //Top-Right
        if (getRotation()>270 && getRotation()<360 && getY() <= 5)
        {
            setRotation(0+(360-getRotation()));
        }
        if (getRotation()>270 && getRotation()<360 && getX() >= 1195)
        {
            setRotation(270-(getRotation()-270));
        }
        
    }
    
    /**
     * Return true if the bullet is nearby the Wall class
     */
    public boolean collisionWithTank(Wall wal)
    {
        return Math.abs(wal.getX()-getX())<=40 && Math.abs(wal.getY()-getY())<=160;
    }
    
    /**
     * Bounce at the Wall
     */
    public void turnAngleAtWall()
    {
       world = (Game)getWorld();
       for (Wall wal : world.wall)
       {
            if (collisionWithTank(wal))
            {
                
                //Top-Left
                if (getRotation()>=180 && getRotation()<=270)
                {
                    setRotation(270+(270-getRotation()));
                }
            
                //Bottom-Left
                if (getRotation()>=90 && getRotation()<=180) 
                {
                 
                    setRotation(90-(getRotation()-90));
                }
                
                //Bottom-Right
                if (getRotation()>=0 && getRotation()<=90)
                {
                    setRotation(90+(90-getRotation()));
                }
                
                //Top-Right
                if (getRotation()>=270 && getRotation()<=360)
                {
                    setRotation(270-(getRotation()-270));
                }
               
            }
       }  
    }
    
    /**
     * Increase varible bounce each time after bullet bounces at wall or the edge of the Game world
     */
    public void bounce()
    {
        if(isAtEdge())
        {
            turnAngleAtEdge();
            bounce++;
        }
        if (isTouching(Wall.class))
        {
            turnAngleAtWall(); 
            bounce++;
        }  
    }
    
    /**
     * Remove the bullet after variable bounce is greater than MAX_BOUNCE
     */
    public void removeBullet()
    {
        if (bounce>MAX_BOUNCE && this != null)
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Check the collision with tank and start respawn timer in Game world
     */
    public void collisionWithTank()
    {
        
        if (isTouching(Tank.class))
        {
            if (numTouchTank>3)
            {
                world = (Game)getWorld();
                if (world.tank1 != null && intersects(world.tank1))
                { 
                    numTouchTank=0;
                    world.removeObject(world.tank1);
                    world.secondTankIncreaseScore(1075, 60);
                    world.removeObject(this); 
                    world.tank1 = null;
                    world.respawnTime();
                    return;
        
                }
                if(world.tank2 != null && intersects(world.tank2))
                {
                    numTouchTank=0;
                    world.removeObject(world.tank2);
                    world.firstTankIncreaseScore(70,60); 
                    world.removeObject(this); 
                    world.tank2 = null;
                    world.respawnTime();
                    return;
                }
            }
            else
            {
                numTouchTank++;
            }
        }
        
    }
       
}
