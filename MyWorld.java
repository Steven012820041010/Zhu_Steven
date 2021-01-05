import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList.*;
import java.util.*;
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
    Label scoreLabel1;
    Label scoreLabel2;
    Tank tank1 = new Tank(true);
    Tank tank2 = new Tank(false);
    BackArrow arrow;
    Wall[] wall;
    Sign[] sign;
    List<Bullet> bul;
    public int button;
    
    public static int score1 = 0;
    public static int score2 = 0;
    
    public BulletSymbol[] bulFigure1 = new BulletSymbol[8];
    public BulletSymbol[] bulFigure2 = new BulletSymbol[8];
    private int indexImage = 0;
    
    private boolean isPause = false;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        setBackground(new GreenfootImage("yellow background.jpg"));
        
        
        
        Coin coin = new Coin(); 
        scoreLabel1 = new Label(0,30);
        scoreLabel2 = new Label(0,30);
        arrow = new BackArrow();
        wall = new Wall[10];
        sign = new Sign[3];
        bul = new ArrayList<Bullet>();
        //Tank tank = new Tank(false);
        //addObject(tank,550,400);
       // tank.setRotation(270);
        
        
        
        setBulletFigure();
        setSign();
        setTenRandomWall();
        
        addBulletFigure(20,750,bulFigure1);
        addBulletFigure(1050,750,bulFigure2);

        addObject(scoreLabel1,30,30);
        addObject(scoreLabel2,1100,30);
        addObject(coin,Greenfoot.getRandomNumber(1000),Greenfoot.getRandomNumber(800));
        setTankIndex(tank1,100);
        setTankIndex(tank2,900);
        //addObject(tank1,100,100);
        //addObject(tank2,1000,100);
        addObject(arrow,1160,30);
        
        
        
       // addObject(wall,Greenfoot.getRandomNumber(1000),Greenfoot.getRandomNumber(800));
        
    }
    
    public void act()
    {
        pause();
        checkScore();
    }
    
    public void setTankIndex(Tank tank, int range)
    {
        int X = range + Greenfoot.getRandomNumber(100);
        int Y = Greenfoot.getRandomNumber(800);
        boolean nearWall = true;
            for (Wall wal:wall)
            {    
                if (Math.abs(wal.getX()-X)<40 && Math.abs(wal.getY()-Y)<40)
                {
                    X = range + Greenfoot.getRandomNumber(100);
                    Y = Greenfoot.getRandomNumber(800);
                }
            }
        
        addObject(tank,X,Y);
    }
    
    public void checkScore()
    {
        if (score1 >= 2)
        {
            EndGame end = new EndGame(true);
            Greenfoot.setWorld(end);
        }
        else if (score2 >= 2)
        {
            EndGame end = new EndGame(false);
            Greenfoot.setWorld(end);
        }
    }
    public void setSign()
    {
        sign[0] = new readySign();
        sign[1] = new battleSign();
        sign[2] = new pauseSign();
    }
    
    public void pause()
    {
        /*
        if (Greenfoot.isKeyDown("p") && !isPause)
        {
            addObject(sign[2],600,400);
            isPause = true;
            Greenfoot.stop();
        }
        else if (Greenfoot.isKeyDown("p") && isPause)
        {
            removeObject(sign[2]);
            isPause = false;
        }
        */
        if (Greenfoot.isKeyDown("p"))
        {
            while (!Greenfoot.isKeyDown("r"))
            {
                addObject(sign[2],600,400);
                Greenfoot.delay(1);
            }
        }
        removeObject(sign[2]);
        
       
    }
    public void setTenRandomWall()
    {
        //Init 10 walls
        for (int i=0; i<wall.length; i++)
        {
            wall[i] = new Wall();
            //wall[i].setRotation(Greenfoot.getRandomNumber(2)*90);
        }
        
        //Add 10 walls
        
        for (int i=0; i<wall.length; i++)
        {
            int X = Greenfoot.getRandomNumber(1000);
            int Y = Greenfoot.getRandomNumber(800);
            addObject(wall[i],X,Y);
            
        }
        
    }
    
    public void removeAllWall()
    {
        for (int i=0; i<wall.length; i++)
        {
            removeObject(wall[i]);
            
        }
    }
    
    public void removeAllTank()
    {
        removeObject(tank1);
        removeObject(tank2);
    }
    
    public void removeAllBullet()
    {
        for (int i=0; i<bul.size(); i++)
        {
            removeObject(bul.get(i));
            
        }
    }
    
    public void firstTankIncreaseScore()
    {
        score1 += Greenfoot.getRandomNumber(2)+1;
        scoreLabel1.setValue(score1);
    }
    
    public void secondTankIncreaseScore()
    {
        score2 += Greenfoot.getRandomNumber(2)+1;
        scoreLabel2.setValue(score2);
    }
    
    public void createCoin()
    {
        //
        Coin c = new Coin();
        int X = Greenfoot.getRandomNumber(1000);
        int Y = Greenfoot.getRandomNumber(800);
        addObject(c,X,Y);
    }
    
    public void respawn()
    {
        removeAllTank();
        removeAllWall();
        removeAllBullet();
        
        tank1 = new Tank(true);
        tank2 = new Tank(false);
        addObject(tank1,Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
        addObject(tank2,700 + Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
        displaySign();
        setTenRandomWall();
    }
    
    public void displaySign()
    {
        addObject(sign[0],600,400);
        Greenfoot.delay(15);
        removeObject(sign[0]);
        
        addObject(sign[1],600,400);
        Greenfoot.delay(15);
        removeObject(sign[1]);
        
        
    }
    public void setBulletFigure()
    {
        indexImage = 0;
        for (int i=0; i<8; i++)
        {
            bulFigure1[indexImage] = new BulletSymbol();
            bulFigure2[indexImage] = new BulletSymbol();
            indexImage++;
           
        } 
        
       
    }
    
    
    public void addBulletFigure(int X, int Y, BulletSymbol[] arr)
    {
        indexImage = 0;
        
        for (int i=0; i<8; i++)
        {
            addObject(arr[indexImage],X,Y);
            indexImage++;
            X+=14;
            
        }
    }
    
    
    
}
