import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList.*;
import java.util.*;
/**
 * Tank Battle is a two-players game. Touch coin and shoot down the opponent's tank can both score. Score 21 points first 
 * will be the final winner. SO, GAME ON!
 * Game world is basically setting up all the actor classes I have created. It inclueds the most methods such as initializes 
 * each Actor class, sets their coordinates, and increase the score system. 
 * 
 * @author Steven Zhu
 * @version 2020.11.23
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
    
    //List<Bullet> bul; //Stores every bullet once the tank shoots
    BulletSymbol[] bulFigure1 = new BulletSymbol[8]; //Numbers of bullet remain for first tank
    BulletSymbol[] bulFigure2 = new BulletSymbol[8]; //Numbers of bullet remain for second tank
    int indexImage = 0; //Controls both bulFigure1 and bulFigure 2

    Wall[] wall;
    Coin coin;
    
    public Game()
    {    
        // Create a new world with 1200x800 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        setBackground(new GreenfootImage("yellow background.jpg")); //Set background
        
        //Initialize
        isPause = false;
        score1 = 0;
        score2 = 0;
        
        coin = new Coin(); 
        arrow = new BackArrow();
        
        sign = new Sign[3];
        waitingSign = new Label[3];
        wall = new Wall[10];
        scoreLabel1 = new Label(0,30);
        scoreLabel2 = new Label(0,30);
        nameLabel1 = new Label("Red:",30);
        nameLabel2 = new Label("Green:",30);
        
        //Set color for labels
        nameLabel1.setFillColor(greenfoot.Color.RED);
        nameLabel2.setFillColor(greenfoot.Color.GREEN);
        scoreLabel1.setFillColor(greenfoot.Color.RED);
        scoreLabel2.setFillColor(greenfoot.Color.GREEN);
        
        //Add object
        setSign();
        setScoreLabel();
        setBulletFigure();
        addObject(arrow,1160,30);
        addBulletFigure(30,750,bulFigure1);
        addBulletFigure(1050,750,bulFigure2);
        addObject(coin,Greenfoot.getRandomNumber(1000),Greenfoot.getRandomNumber(800));
        addObject(tank1,100+Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
        addObject(tank2,700+Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
        setTenRandomWall();
    }
    
    /**
     * Press "P" to pause the game, and press "R" to resume
     */
    public void pause()
    {
        if (Greenfoot.isKeyDown("p"))
        {
            while (!Greenfoot.isKeyDown("r"))
            {
                addObject(sign[2],600,400); //Add Pause sign
                Greenfoot.delay(1);
            }
            removeObject(sign[2]); //Remove Pause sign
            setWaitingSign(); //Display "3", "2", and "1".
       
            for (int i=0; i<waitingSign.length; i++)
            {
                waitingSign[i].setFillColor(greenfoot.Color.RED);
                addObject(waitingSign[i],600,400);
                Greenfoot.delay(15);
                removeObject(waitingSign[i]);
            }
        }
    }
   
    public void act()
    {
        pause();
        checkScore();
        removeScoreSign();
        respawn();
    }
    
    /**
     * Set the score and name labels for both tank
     */
    public void setScoreLabel()
    {
        addObject(nameLabel1,50,30);
        addObject(scoreLabel1,95,31);
        addObject(nameLabel2,1058,30);
        addObject(scoreLabel2,1110,31);
    }
    
    /**
     * Set the three signs "Ready", "Battle", and "Pause" sign
     */  
    public void setSign()
    {
        sign[0] = new ReadySign();
        sign[1] = new BattleSign();
        sign[2] = new PauseSign();
    }
    
    /**Display "Ready" and "Battle" sign
     */
    public void displaySign()
    {
        addObject(sign[0],600,400);
        Greenfoot.delay(23);
        removeObject(sign[0]);
        
        addObject(sign[1],600,400);
        Greenfoot.delay(23);
        removeObject(sign[1]);
        
    }
    
    /**
     * Set Label "3", "2", and "1"
     */
    public void setWaitingSign()
    {
        for (int i=0; i<waitingSign.length; i++)
        {
            waitingSign[i] = new Label (3-i,60);
        }
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
    
    /** 
     * Set the first bullet figure in arr at X and Y and increasing the X position 14 each time.
     */
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
    
    /** 
     * Return true if the object is near the bullet figure; otherwise, return false
     */
    public boolean awayFromBulletFigure(int X, int Y)
    {
        return ((X>150 && Y<720) && (X<1050 && Y<720));
    }
    
    /**
     * Return true if the object is near first tank; otherwise, return false
     */
    public boolean awayFromTank1(int X, int Y)
    {
        return (X<tank1.getX()-50 || X>tank1.getX()+50) && (Y<tank1.getY()-40 || Y>tank1.getY()+40);
    }
    
    /**
     * Return true if the object is near second tank; otherwise, return false
     */
    public boolean awayFromTank2(int X, int Y)
    {
        return (X<tank2.getX()-50 || X>tank2.getX()+50) && (Y<tank2.getY()-40 || Y>tank2.getY()+40);
    }
    
    /**
     * Set ten walls randomly in the Game 
     */
    public void setTenRandomWall()
    {
        //Init 10 walls
        for (int i=0; i<wall.length; i++)
        {
            wall[i] = new Wall();
            
        }
        int numWall = 0;
        
        //Add 10 walls
        int X = Greenfoot.getRandomNumber(1000);
        int Y = Greenfoot.getRandomNumber(800);
        while (numWall<wall.length)
        {
            if (awayFromTank1(X,Y) && awayFromTank2(X,Y) && awayFromBulletFigure(X,Y))
            {
                addObject(wall[numWall],X,Y);
                numWall++; 
            }
            X = Greenfoot.getRandomNumber(1000);
            Y = Greenfoot.getRandomNumber(800);
        }
        
    }
    
    /**
     * Change the world to EndGame if score1 or score 2 is greater or equal to 21 
     */
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
    
    /** 
     * First Tank increases score 
     */
    public void firstTankIncreaseScore(int x, int y)
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
        addObject(scoreSign,x+20,y);
        scoreLabel1.setValue(score1);
    }
    
    /** 
     * Second Tank increases score 
     */
    public void secondTankIncreaseScore(int x, int y)
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
        addObject(scoreSign,x+20,y);
        scoreLabel2.setValue(score2);
    }
     
    /** 
     * Create coin randomly in Game 
     */
    public void createCoin()
    {
        Coin c = new Coin();
        int X = Greenfoot.getRandomNumber(1000);
        int Y = Greenfoot.getRandomNumber(800);
        addObject(c,X,Y);
    }
    
    /** Count the time after a tank has been shoot 
     */
    public void respawnTime()
    {
        if ((!(score1 >= 21 || score2>= 21)))
        {
            respawnTimer.mark();
        }
    }
    
    /** Remove all objects in Game and initialize them again
     * 
     */
    public void respawn()
    {
        if(tank1 == null || tank2 == null)
        {
            if (respawnTimer.millisElapsed() > 3000 && respawnTimer.millisElapsed() < 4000)
            {
                //removeAllTank();
                //removeAllWall();
                //removeAllBullet();
                //removeAllBulletFigure();
                //removeAllScoreSign();
                removeObjects(getObjects(null));
                tank1 = new Tank(true);
                tank2 = new Tank(false);
                addObject(arrow,1160,30);
                addObject(tank1,Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
                addObject(tank2,700 + Greenfoot.getRandomNumber(100),Greenfoot.getRandomNumber(800));
                displaySign();
                createCoin();
                setTenRandomWall();
                setScoreLabel();
                addBulletFigure(30,750,bulFigure1);
                addBulletFigure(1050,750,bulFigure2);
            } 
        }
    }
    
    public void removeAllScoreSign()
    {
        removeObject(scoreSign);
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
    
    public void removeAllBulletFigure()
    {
        for (int i=0; i<bulFigure1.length; i++)
        {
            removeObject(bulFigure1[i]);
            removeObject(bulFigure2[i]);
        }
    }
    
    public void removeScoreSign()
    {
        if (pointTimer.millisElapsed()>1000)
        {
            removeObject(scoreSign);
        }
    }
    
}
