import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    GreenfootSound shootingSound = new GreenfootSound ("GunShotSound.mp3");
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        TankBattle tank = new TankBattle();
        
        Label score = new Label(0,20);
        addObject(score,30,30);
        addObject(tank,100,100);
        Bullet bullet = new Bullet();
       
        
        
        
    }
    
    
}
