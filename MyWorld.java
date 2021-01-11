
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
    Label nameLabel1;
    Label nameLabel2;
    
    Sign scoreSign;
    
    
    SimpleTimer timer = new SimpleTimer();
    
    Tank tank1 = new Tank(true);
    Tank tank2 = new Tank(false);
    BackArrow arrow;
    Wall[] wall;
    Sign[] sign;
    List<Bullet> bul;
    public int button;
    
    public static int score1;
    public static int score2 = 0;
    
    public BulletSymbol[] bulFigure1 = new BulletSymbol[8];
    public BulletSymbol[] bulFigure2 = new BulletSymbol[8];
    private int indexImage = 0;
    int num = 0;
    
    private boolean isPause = false;
    
    public MyWorld()
    {    
       // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
       
        super(1200, 800, 1);
        
        setBackground(new GreenfootImage("yellow background.jpg"));
        
        
        
        Coin coin = new Coin(); 
        scoreLabel1 = new Label(0,30);
        scoreLabel2 = new Label(0,30);
        
        nameLabel1 = new Label("Red:",30);
        nameLabel2 = new Label("Green:",30);
        
        
        arrow = new BackArrow();
        wall = new Wall[10];
        sign = new Sign[3];
        bul = new ArrayList<Bullet>();
        //Tank tank = new Tank(true);
        //addObject(tank,600,400);
        // addObject(tank,200,160);
         //tank.setRotation(0);
        
        score1 = 0;
        score2 = 0;
        nameLabel1.setFillColor(greenfoot.Color.RED);
        nameLabel2.setFillColor(greenfoot.Color.GREEN);
        scoreLabel1.setFillColor(greenfoot.Color.RED);
        scoreLabel2.setFillColor(greenfoot.Color.GREEN);
        
        setBulletFigure();
        setSign();
        
        
        
        addBulletFigure(30,750,bulFigure1);
        addBulletFigure(1050,750,bulFigure2);

        addObject(nameLabel1,50,30);
        addObject(scoreLabel1,95,31);
        addObject(nameLabel2,1058,30);
        addObject(scoreLabel2,1110,31);
        
        
        addObject(coin,Greenfoot.getRandomNumber(1000),Greenfoot.getRandomNumber(800));
        addObject(tank1,100+Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
        addObject(tank2,700+Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
        
        //addObject(tank1,100,100);
        //addObject(tank2,1000,100);
        addObject(arrow,1160,30);
        setTenRandomWall();
        
        
        // addObject(wall,Greenfoot.getRandomNumber(1000),Greenfoot.getRandomNumber(800));
        
    }
    
    public void act()
    {
        pause();
        checkScore();
        if (timer.millisElapsed()>800)
        {
            //timer.mark();
            removeObject(scoreSign);
        }
    }
    
   
    
    public void checkScore()
    {
        if (score1 >= 21)
        {
            
            EndGame end = new EndGame(true);
            Greenfoot.setWorld(end);
        }
        else if (score2 >= 21)
        {
         
            EndGame end = new EndGame(false);
            Greenfoot.setWorld(end);
            
        }
    }
    public void setSign()
    {
        sign[0] = new ReadySign();
        sign[1] = new BattleSign();
        sign[2] = new PauseSign();
    }
    
    public void pause()
    {
        
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
        int counter = 0;
        //Add 10 walls
        while (counter<wall.length)
        {
            int X = Greenfoot.getRandomNumber(1000);
            int Y = Greenfoot.getRandomNumber(800);
            if (awayFromTank1(X,Y) || awayFromTank2(X,Y))
            {
                addObject(wall[counter],X,Y);
                counter++;
            }else{
                X = Greenfoot.getRandomNumber(1000);
                Y = Greenfoot.getRandomNumber(800);
            }
            
        }
        
    }
    
    public boolean awayFromTank1(int X, int Y)
    {
        return (X<tank1.getX()-30 || X>tank1.getX()) && (Y<tank1.getY()-20 || Y>tank1.getY()+20);
    }
    
    public boolean awayFromTank2(int X, int Y)
    {
        return (X<tank2.getX()-30 || X>tank2.getX()) && (Y<tank2.getY()-20 || Y>tank2.getY()+20);
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
        timer.mark();
        num = Greenfoot.getRandomNumber(2)+1;
        score1 += num;
        if(num==1)
        {
            scoreSign = new GainOneSign();
        }
        else if (num==2)
        {
            scoreSign = new GainTwoSign();
        }

        addObject(scoreSign,tank1.getX()+20,tank1.getY());
        scoreLabel1.setValue(score1);
    }
    
    public void secondTankIncreaseScore()
    {
        timer.mark();
        num = Greenfoot.getRandomNumber(2)+1;
        score2 += num;
        if(num==1)
        {
            scoreSign = new GainOneSign();
        }
        else if (num==2)
        {
            scoreSign = new GainTwoSign();
        }
        addObject(scoreSign,tank2.getX()+20,tank2.getY());
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
        if (!(score1 >= 21 || score2>= 21))
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
    }
    
    public void displaySign()
    {
        addObject(sign[0],600,400);
        Greenfoot.delay(23);
        removeObject(sign[0]);
        
        addObject(sign[1],600,400);
        Greenfoot.delay(23);
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
