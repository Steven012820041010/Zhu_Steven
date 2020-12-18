import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TankBattle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tank extends Actor
{
    private final int MAX_COOLDOWN = 60;
    private final int MAX_BULLETS = 8;

    private GreenfootImage image;
    GreenfootSound shootingSound = new GreenfootSound ("GunShotSound.mp3");

    private int cooldown;
    private int numBullet;
    private boolean needToRefill = false;
    public static boolean remove = false;
    /**
     * Act - do whatever the TankBattle wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Tank()
    {
        int angle = Greenfoot.getRandomNumber(360);
        setRotation(angle);

        image = new GreenfootImage("TankModel.png");
        setImage(image);

        cooldown = 0;
        numBullet = 0;
    }

    public void act() 
    {
        moveTank();
        if (Greenfoot.isKeyDown("space") && canShoot())
        {
            shoot();
        }

    }

    private boolean canShoot()
    {
        return numBullet<MAX_BULLETS && cooldown == 0 && needToRefill == false;
    }

    //Using "up" and "down" to move forward and backward,
    //      "left" and "right" to control the direction
    public void moveTank()
    {

        if (Greenfoot.isKeyDown("up"))
        {
            move(8);
        }
        else if (Greenfoot.isKeyDown("down"))
        {
            move(-8);
        }
        if (Greenfoot.isKeyDown("left"))
        {
            turn(-30);
            setRotation(getRotation()-30);
        }
        if (Greenfoot.isKeyDown("right"))
        {
            turn(30);
            setRotation(getRotation()+30);
        }

    }

    //shoot the bullet if "space" is pressed
    public void shoot()
    {
        // Prevent rapid shooting.
        if (cooldown > 0)
        {
            cooldown--;
            return;
        }

        numBullet++;
        if (numBullet >= MAX_BULLETS)
        {
            cooldown = MAX_COOLDOWN;
            //needToRefill = true;
            //remove = false;
        }

        shootingSound.play();

        Bullet bul = new Bullet();
        bul.setRotation(getRotation());

        MyWorld world = (MyWorld)getWorld();
        world.addObject(bul,getX(),getY());

    }
}
