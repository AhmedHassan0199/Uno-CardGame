package unocardgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Yasmeen
 */

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.gui.*;

public class WinningState extends BasicGameState{
    public static int WinScore;
    Image WinningImage; 
    Image MainMenu;
    
      public WinningState(int state){
       
     }
       
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        WinningImage=new Image("Res/Winning.jpg");
        MainMenu= new Image("Res/MainMenuButton.png");
    
    }
       
     public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
   
         WinningImage.draw(0,0);
         MainMenu.draw(1100,650,175,50);
    
     }
     public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException{
           int x=Mouse.getX();
        int y=Mouse.getY();
        if((x>1100 && x<1270) && (y>5&&y<50) ){
         if(Mouse.isButtonDown(0)){
          Menu.sound.play();
           
           sbg.enterState(0);
         }
     }
     }
     
   
     
     public int getID(){
     return 3;
     }

    

     
      

}