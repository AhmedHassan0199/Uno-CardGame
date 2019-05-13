/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
/**
 *
 * @author Yasmeen
 */
public class HowToPlay extends BasicGameState {
    Image background;
    Image rules;
    Image backbutton;
    public Sound sound;
    public String mouse="";
      public HowToPlay(int state){
     
     }
     public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
      background=new Image("Res/uno7.jpg");  
      rules=new Image("Res/rules.png");
      backbutton=new Image("Res/MainMenuButton.png");
     sound=new Sound("Res/button sound.wav"); 
     }
     public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
   
     background.draw(0,0,1300,700);
     g.drawString(mouse, 0, 100);
     rules.draw(300,100,700,500);
 
     backbutton.draw(1100,650,175,50);
     }
     public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException{
     int x=Mouse.getX();
     int y=Mouse.getY();
     mouse="x=" + x +"y=" +y;
     //back button
      if((x>1100 && x<1270) && (y>5&&y<50) ){
         if(Mouse.isButtonDown(0)){
          Menu.sound.play();
           
           sbg.enterState(0);
         }
     }
     }
     
     public int getID(){
     return 2;
     }
}
