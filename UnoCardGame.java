package unocardgame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class UnoCardGame extends StateBasedGame{
    public static final String GameName="UNO!";
    public static final int menu=0;
    public static final int HowToPlay=2;
    public static final int test=1;
    public static final int LosingState=4;
    public static final int WinningState=3;
    public static final int scores = 5;
    
    
    public UnoCardGame(String GameName)
    {
        super(GameName);
        this.addState(new Menu(menu));
        this.addState(new HowToPlay(HowToPlay));
        this.addState(new MainGame(test));
        this.addState(new WinningState(WinningState));
        this.addState(new LosingState(LosingState));
        this.addState(new ScoreBoard(scores));
    }
    
    public void initStatesList(GameContainer gc)throws SlickException
    {
        this.enterState(menu);
    }
    
    
    public static void main(String[] args) {
        AppGameContainer appGC;
        try
        {
            appGC= new AppGameContainer(new UnoCardGame(GameName));
            appGC.setDisplayMode(1300,700,false);
            appGC.setTargetFrameRate(200);
            appGC.start();
        }catch(SlickException e){
           e.printStackTrace();
        }
        
        
    }
    
}
