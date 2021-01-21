import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Grab the user's attention.
 * Present as the main page, including the start, music and key button
 * 
 * @author Steven Zhu
 * @version 2020.12.30
 */
public class TitlePage extends World
{
     
    SimpleTimer time = new SimpleTimer();
    
    public static Music soundFigure; //
    
    GreenfootImage tankImage = new GreenfootImage("tankBattle.png"); //tank image
    GreenfootImage[] sound = new GreenfootImage [2]; //An array contains 2 images: one is on, and other is off
    
    StartButton start = new StartButton();
    InfoButton info = new InfoButton();
    
    Label titleTank;
    Label titleBattle;
    Label[] startButton;
    Label[] gameInfo;
    
    /**
     * Constructor for objects of class TitlePage.
     * 
     */
    public TitlePage()
    {    
        // Create a new world with 1000x800 cells with a cell size of 1x1 pixels.
        super(1000, 800, 1); 
        time.mark();
       
        //Initialize
        setBackground(new GreenfootImage("tank background.png"));//Set background image
        getBackground().drawImage(tankImage,350,60);
        titleTank = new Label ("TANK" , 110);
        titleBattle = new Label ("BATTLE" , 110);
        soundFigure = new Music();
        
        //Label "Tank"
        titleTank.setFillColor(greenfoot.Color.GREEN);
        titleTank.setLineColor(greenfoot.Color.GREEN.darker().darker());//Dark green
        
        //Label "Battle"
        titleBattle.setFillColor(greenfoot.Color.RED);
        titleBattle.setLineColor(greenfoot.Color.RED.darker().darker());//Dark red
        
        //Adding Object
        addObject(titleTank,400,240);
        addObject(titleBattle,550,350);
        addObject(soundFigure,912,60);
        addObject(start,500,500);
        addObject(info,90,60);
        
       
    }
    
    public void act()
    {
        playGameOrReadIntro();
    }
    
    /**
     * Start the game or explains the operation of the game
     */
    public void playGameOrReadIntro()
    {
        if (Greenfoot.isKeyDown("s"))
        {
           Game gameWorld = new Game();
           Greenfoot.setWorld(gameWorld);
        }
        
        if (Greenfoot.isKeyDown("i"))
        {
            Introduction intro = new Introduction();
            Greenfoot.setWorld(intro);
        }
    }
    
}
