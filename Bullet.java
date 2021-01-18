
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
    SimpleTimer timer = new SimpleTimer();
    SimpleTimer bounce_cool_down = new SimpleTimer();
    GreenfootSound shootingSound  = new GreenfootSound("GunShotSound0.mp3");
    Game world;
    
    private int MAX_BOUNCE = 8;
    private int bounce;
    private int soundIndex = 0;
    boolean score = true;
    
    public Bullet ()
    {
        timer.mark();
        //time.mark();
        
        shootingSound.setVolume(40);
        shootingSound.play();
        
        bounce = 0;
    }
    
    
  
    public void act() 
    {
        if(timer.millisElapsed()<70)
        {
            move(40);
        }else{
            move(8);
        }
         
        bounce();
        collisionWithTank();
        removeBullet();
        
        
        
    }
    
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
    
    
    public void turnAngleAtWall()
    {
       world = (Game)getWorld();
        
       for (Wall wal : world.wall)
       {
            
            if (Math.abs(wal.getX()-getX())<=120 && Math.abs(wal.getY()-getY())<=160)
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
    
    public void removeBullet()
    {
        if (bounce>MAX_BOUNCE)
        {
            getWorld().removeObject(this);
        }
    }
    
    public void collisionWithTank()
    {
        
        world = (Game)getWorld(); 
        if (world.tank1 != null && intersects(world.tank1))
        {
            
            //world.removeObject(world.tank1);
            world.removeObject(world.tank1);
            world.secondTankIncreaseScore(getX(), getY());
            world.removeObject(this); 
            world.tank1 = null;
            world.respawnTime();
            
            return;

        }
        else if(world.tank2 != null && intersects(world.tank2))
        {
            
            world.removeObject(world.tank2);
            
            world.firstTankIncreaseScore(getX(),getY()); 
            world.removeObject(this); 
            world.tank2 = null;
            world.respawnTime();
            
            
            //world.respawn();
            return;
        }
        
        
        
        
        
    }
    
    
        
}
