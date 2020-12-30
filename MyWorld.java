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
    Label scoreLabel1;
    Label scoreLabel2;
    Tank tank1;
    Tank tank2;
    BackArrow arrow;
    Wall[] wall;
    public int button;
    
    public int score1 = 0;
    public int score2 = 0;
    
    public BulletSymbol[] symbol = new BulletSymbol[8];
    private int indexImage = 0;
    
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 800, 1);
        
        tank1 = new Tank(true);
        tank2 = new Tank(false);
        //Tank tank2 = new Tank(false);
        Coin coin = new Coin(); 
        scoreLabel1 = new Label(0,30);
        scoreLabel2 = new Label(0,30);
        arrow = new BackArrow();
        wall = new Wall[10];
       
        
        setTenRandomWall();
        setBulletFigure();
        addBulletFigure();
        
        addObject(scoreLabel1,30,30);
        addObject(scoreLabel2,1100,30);
        addObject(coin,Greenfoot.getRandomNumber(1000),Greenfoot.getRandomNumber(800));
        addObject(tank1,100,100);
        addObject(tank2,1000,100);
        addObject(arrow,1160,30);
       // addObject(wall,Greenfoot.getRandomNumber(1000),Greenfoot.getRandomNumber(800));
        
    }
    
    public void act()
    {
        
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
    
    public void setBulletFigure()
    {
        
        for (int i=0; i<8; i++)
        {
            symbol[indexImage] = new BulletSymbol();
            indexImage++;
        } 
       
    }
    public void addBulletFigure()
    {
        int X = 20;
        int Y = 750;
        indexImage = 0;
        
        for (int i=0; i<8; i++)
        {
            addObject(symbol[indexImage],X,Y);
            indexImage++;
            X+=14;
            
        }
    }
    
    
    
}
