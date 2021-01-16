import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TankBattle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tank extends Actor
{
    private final int MAX_COOLDOWN = 50;
    private final int MAX_BULLETS = 8;

    private GreenfootImage image;
    public Game world;
    public boolean player;
    private int cooldown;
    private int numBullet;
    private int index = 7;
    private boolean needToWait = false;
    SimpleTimer timer = new SimpleTimer();
    int bulletCounter;
    int speed;
    //private int[] bulFigure = new int[8];
    
    /**
     * Act - do whatever the TankBattle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Tank(boolean player)
    {
        timer.mark();
        world = (Game)getWorld();
        int angle = Greenfoot.getRandomNumber(360);
        setRotation(angle);
        this.player = player;
        setTankImage();

        speed = 3;
        cooldown = 0;
        numBullet = 0;
        
        bulletCounter = 0;
    }
    public void setTankImage()
    {
        if (player)
        {
           image = new GreenfootImage("TankModel.png");
           setImage(image); 
        }else{
           image = new GreenfootImage("Tank2Model.png");
           setImage(image); 
        }
    }

    public void act() 
    {
        coolDown();
        moveTank();
        touchCoin();
       
        if (Greenfoot.isKeyDown("space") && canShoot() && player)
        {
            shoot();
        }
        else if((Greenfoot.isKeyDown("j") && canShoot() && !player))
        {
            shoot();
        }
        else{
            needToRefill();
        }

    }

    private boolean canShoot()
    {
        return numBullet<MAX_BULLETS && cooldown == 0 && needToWait == false;
    }
    //wohaoshuai
    //Using "up" and "down" to move forward and backward,
    //      "left" and "right" to control the direction
    public void needToRefill()
    {
        if (numBullet==0){
            needToWait = false;
            world = (Game)getWorld();
            if (player)
            {
                world.addBulletFigure(20,750,world.bulFigure1);
            }
            else
            {
                world.addBulletFigure(1050,750,world.bulFigure2);
            }
            index = 7;
        }
        if (needToWait){
           numBullet--; 
        }
        
    }
    public void coolDown()
    {
        if (cooldown > 0)
        {
            cooldown--;
            return;
        }
    }
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
           if (Math.abs(wal.getX()-this.getX())<40 && Math.abs(wal.getY()-this.getY())<40)
           {
                move(speed);
           }
       }
        
    }
    public void touchCoin()
    {
        
        if (isTouching(Coin.class) && player)
        {
            
            removeTouching(Coin.class);
            world.firstTankIncreaseScore();
            world.createCoin();
            
        }
        else if (isTouching(Coin.class) && !player)
        {
            
            removeTouching(Coin.class);
            world.secondTankIncreaseScore();
            world.createCoin();
        }
        
        
        
       
    }
   
    public void moveTank()
    {
        if (player){
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
    
    

    //shoot the bullet if "space" is pressed
    public void shoot()
    {
        numBullet++;
        cooldown = MAX_COOLDOWN;
        world = (Game)getWorld();
        
        Bullet bullet = new Bullet();
        
        world.bul.add(bullet);
       
        
       
        if (player)
        {
            bullet.setImage(new GreenfootImage("Player1Bullet.png"));
        }
        else
        {
            bullet.setImage(new GreenfootImage("Player2Bullet.png"));
        }
            
        bullet.setRotation(getRotation());
        
       
        world.addObject(bullet,getX(),getY());
        //world.addObject(bullet,X,Y);
        if (numBullet >= MAX_BULLETS)
        {
            
            needToWait = true;
           
        }
        
        
        if (player){
            getWorld().removeObject(world.bulFigure1[index]);
            index--;
        }
        else
        {
            getWorld().removeObject(world.bulFigure2[world.bulFigure2.length-index-1]);
            index--;
        }
        bulletCounter++;

    }
    
    
    
    
    
    
    
    
    
}
