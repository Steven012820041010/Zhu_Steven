import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TankBattle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tank extends Actor
{
    SimpleTimer timer = new SimpleTimer();
    Game world;
    private final int MAX_COOLDOWN = 40; //The time that needs to wait before next shooting
    private final int MAX_BULLETS = 8; //Maximum numbers of bullets can store
    
    private int speed; //Speed of the tank
    private int cooldown;
    private int numBullet;
    private int index = 7;
    private boolean needToWait = false;
    private GreenfootImage image;
    
    public boolean redOrGreen;

    /**
     * Act - do whatever the TankBattle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Tank(boolean firstOrSecond)
    {
        timer.mark();
        world = (Game)getWorld();
        
        //Initialize
        speed = 3; //Set tank speed to 3
        cooldown = 0; 
        numBullet = 0; 
        redOrGreen = firstOrSecond;  
        
        setRotation(Greenfoot.getRandomNumber(360)); //Set tank's direction randomly
        setTankImage();
    }
    
    /**
     * Set tank image to red or green based on the boolean redOrGreen
     */
    public void setTankImage()
    {
        if (redOrGreen)
        {
            image = new GreenfootImage("TankModel.png");
            setImage(image); 
        }else{
            image = new GreenfootImage("Tank2Model.png");
            setImage(image); 
        }
    }

    /**
     * Return the tank is ready to shoot 
     */
    private boolean canShoot()
    {
        return numBullet<MAX_BULLETS && cooldown == 0 && needToWait == false;
    }
    
    /**
     * Return whether the red tank can shoot or not
     */
    public boolean redCanShoot()
    {
        return Greenfoot.isKeyDown("space") && canShoot() && redOrGreen;
    }
    
    /**
     * Return whther the green tank can shoot or not
     */
    public boolean greenCanShoot()
    {
        return Greenfoot.isKeyDown("j") && canShoot() && !redOrGreen;
    }
    
    public void act() 
    {
        coolDown(); //Check for cooldown all the time
        moveTank(); //Move both tank 
        touchCoin(); //Detect the collision between tanks and coin all the time
        if (redCanShoot())
        {
            shoot();
        }
        if(greenCanShoot())
        {
            shoot();
        }
        else
        {
            needToRefill();
        }

    }
    
    /** 
     * Refill the bullets after numBullet exceed MAX_BULLETS and wait until the number of bullet equal to zero
     */
    public void needToRefill()
    {
        if (numBullet==0){ //No more bullet need to be removed
            needToWait = false; //Do not need to wait 
            world = (Game)getWorld();
            if (redOrGreen)
            {
                world.addBulletFigure(20,750,world.bulFigure1); //Reset the 8 eight bullet figures for first tank
            }
            else
            {
                world.addBulletFigure(1050,750,world.bulFigure2); //Reset the 8 eight bullet figures for second tank
            }
            index = 7;
        }
        if (needToWait){ // There are still bullets that need to be removed
            numBullet--; 
        }
        
    }
    
    /**
     * Wait MAX_COOLDOWN milliseconds to shoot next bullet
     */
    public void coolDown()
    {
        if (cooldown > 0)
        {
            cooldown--;
            return;
        }
    }
    
    /**
     * Increase the score of the tank who touches the coin
     */
    public void touchCoin()
    {
        
        if (isTouching(Coin.class) && redOrGreen)
        {
            removeTouching(Coin.class);
            world.firstTankIncreaseScore(70,60);
            world.createCoin();
            
        }
        else if (isTouching(Coin.class) && !redOrGreen)
        {
            removeTouching(Coin.class);
            world.secondTankIncreaseScore(1075,60);
            world.createCoin();
        }
    }
   
    /**
     * Use arrow keys to control the first tank and use "w", "a", "s", "d" to control the second tank
     */
    public void moveTank()
    {
        if (redOrGreen){
            if (Greenfoot.isKeyDown("up"))
            {
                move(speed);
                moveForwardTouchWall();
               
            }
            else if (Greenfoot.isKeyDown("down"))
            {
                move(-speed);
                moveBackwardTouchWall();
            }
            if (Greenfoot.isKeyDown("left"))
            {
                turn(-3);
                
            }
            if (Greenfoot.isKeyDown("right"))
            {
                turn(3);
                
            }
        }else{
            if (Greenfoot.isKeyDown("w"))
            {
                move(speed);
                moveForwardTouchWall();
               
            }
            else if (Greenfoot.isKeyDown("s"))
            {
                move(-speed);
                moveBackwardTouchWall();
            }
            if (Greenfoot.isKeyDown("a"))
            {
                turn(-3);
                
            }
            if (Greenfoot.isKeyDown("d"))
            {
                turn(3);
                
            }
        }

    }
    
    /**
     * Move backward if the tank moves forward and touches the wall  
     */
    public void moveForwardTouchWall()
    {
        world = (Game)getWorld();
        for (Wall wal : world.wall)
        {
            if (Math.abs(wal.getX()-this.getX())<30 && Math.abs(wal.getY()-this.getY())<55)
            {
                move(-speed);
            }
        }
        
    }
    
    public void moveBackwardTouchWall()
    {
       world = (Game)getWorld();
       for (Wall wal : world.wall)
       {
           if (Math.abs(wal.getX()-this.getX())<30 && Math.abs(wal.getY()-this.getY())<40)
           {
                move(speed);
           }
       }
        
    }
    
    /**
     * Set bullet color based on the boolean redOrGreen
     */
    public void setBulletColor(Bullet bullet)
    {
        if (redOrGreen)
        {
            bullet.setImage(new GreenfootImage("Player1Bullet.png"));
        }
        else
        {
            bullet.setImage(new GreenfootImage("Player2Bullet.png"));
        }
    }

    
    public void shoot()
    {
        world = (Game)getWorld();
        
        numBullet++; //Shoot 1 bullet
        cooldown = MAX_COOLDOWN; //Wait MAX_COOLDOWN milliseconds to shoot next bullet
  
        Bullet bullet = new Bullet(); //Create new bullet class
        setBulletColor(bullet);
        bullet.setRotation(getRotation()); //Set the angle of bullet the same as the tank angle
        world.addObject(bullet,getX(),getY());
        if (numBullet >= MAX_BULLETS)
        {
            cooldown = 100;// wait 100 milliseconds to cooldown 
            needToWait = true;
           
        }
        if (redOrGreen){ //First tank
            getWorld().removeObject(world.bulFigure1[index]);
            index--;
        }
        else //Second tank
        {
            getWorld().removeObject(world.bulFigure2[world.bulFigure2.length-index-1]);
            index--;
        }
    }
    
}
