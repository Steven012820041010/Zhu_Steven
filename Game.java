
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList.*;
import java.util.*;
/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Game extends World
{
    
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    SimpleTimer pointTimer = new SimpleTimer(); //Increase score
    SimpleTimer respawnTimer = new SimpleTimer(); //Check the respawn time
    
    public static int score1; //Red tank score
    public static int score2; //Green tank score
    int currPoint = 0; //Once tank increases score
    Sign scoreSign; //Plus one point or two points
    Sign[] sign; //Contains "Battle", "Pause", and "Ready" sign
    BackArrow arrow;
   
    Label scoreLabel1; //Label Red tank score
    Label scoreLabel2; //Label Green tank score
    Label nameLabel1; //Label Red: 
    Label nameLabel2; //Label Green: 
    Label[] waitingSign; //Stores 3, 2, 1 after the game resumes
    boolean isPause; 
    
    Tank tank1 = new Tank(true); //True means first tank
    Tank tank2 = new Tank(false); //False means second tank
    
    List<Bullet> bul; //Stores every bullet once the tank shoots
    BulletSymbol[] bulFigure1 = new BulletSymbol[8]; //Numbers of bullet remain for first tank
    BulletSymbol[] bulFigure2 = new BulletSymbol[8]; //Numbers of bullet remain for second tank
    int indexImage = 0; //Controls both bulFigure1 and bulFigure 2

    Wall[] wall;
    
    public Game()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        setBackground(new GreenfootImage("yellow background.jpg")); //Set wor
        
        
        
        Coin coin = new Coin(); 
        waitingSign = new Label[3];
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
        isPause = false;
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
        removeScoreSign();
        respawn();
        
    }
    
    public void removeScoreSign()
    {
        if (pointTimer.millisElapsed()>1000)
        {
            
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
    
    public void setWaitingSign()
    {
        
        for (int i=0; i<waitingSign.length; i++)
        {
            waitingSign[i] = new Label (3-i,60);
            
        }
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
            removeObject(sign[2]);
            setWaitingSign();
       
            for (int i=0; i<waitingSign.length; i++)
            {
                
                waitingSign[i].setFillColor(greenfoot.Color.RED);
                addObject(waitingSign[i],600,400);
                Greenfoot.delay(15);
                removeObject(waitingSign[i]);
            }
        }
       
       
    }
    public void setTenRandomWall()
    {
        //Init 10 walls
        for (int i=0; i<wall.length; i++)
        {
            wall[i] = new Wall();
            
        }
        int counter = 0;
        //Add 10 walls
        int X = Greenfoot.getRandomNumber(1000);
        int Y = Greenfoot.getRandomNumber(800);
        while (counter<wall.length)
        {
 
            if (awayFromTank1(X,Y) && awayFromTank2(X,Y) && awayFromBulletFigure(X,Y))
            {
                addObject(wall[counter],X,Y);
                counter++;
               
            }
                X = Greenfoot.getRandomNumber(1000);
                Y = Greenfoot.getRandomNumber(800);
            
            
        }
        
    }
    
    public boolean awayFromBulletFigure(int X, int Y)
    {
        return ((X>150 && Y<720) && (X<1050 && Y<720));
    }
    
    public boolean awayFromTank1(int X, int Y)
    {
        return (X<tank1.getX()-50 || X>tank1.getX()+50) && (Y<tank1.getY()-40 || Y>tank1.getY()+40);
    }
    
    public boolean awayFromTank2(int X, int Y)
    {
        return (X<tank2.getX()-50 || X>tank2.getX()+50) && (Y<tank2.getY()-40 || Y>tank2.getY()+40);
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
    
    public void removeAllBulletFigure()
    {
        for (int i=0; i<bulFigure1.length; i++)
        {
            removeObject(bulFigure1[i]);
            removeObject(bulFigure2[i]);
        }
    }
    
    public void firstTankIncreaseScore()
    {
        pointTimer.mark();
        currPoint = Greenfoot.getRandomNumber(2)+1;
        score1 += currPoint;
        if(currPoint==1)
        {
            scoreSign = new GainOneSign();
        }
        else if (currPoint==2)
        {
            scoreSign = new GainTwoSign();
        }

        addObject(scoreSign,tank1.getX()+20,tank1.getY());
        scoreLabel1.setValue(score1);
    }
    
    public void secondTankIncreaseScore()
    {
        pointTimer.mark();
        currPoint = Greenfoot.getRandomNumber(2)+1;
        score2 += currPoint;
        if(currPoint==1)
        {
            scoreSign = new GainOneSign();
        }
        else if (currPoint==2)
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
    public void respawnTime()
    {
        if ((!(score1 >= 21 || score2>= 21)))
        {
            respawnTimer.mark();
            
        }
    }
    public void respawn()
    {
        if (tank1 == null || tank2 == null)
        {
            if (respawnTimer.millisElapsed() > 3000 && respawnTimer.millisElapsed() < 4000)
            {
              
                removeAllTank();
                removeAllWall();
                removeAllBullet();
                removeAllBulletFigure();
                
                tank1 = new Tank(true);
                tank2 = new Tank(false);
                addObject(tank1,Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
                addObject(tank2,700 + Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
                displaySign();
                setTenRandomWall();
                addBulletFigure(30,750,bulFigure1);
                addBulletFigure(1050,750,bulFigure2);
            }   
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
