/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.gui.*;

/**
 *
 * @author Yasmeen
 */
public class Menu extends BasicGameState {
    Image playNow;
    Image exitGame;
    Image mainMenu;
    Image volume;
    Image HowToPlay;
    Image ScoreBoard;
    //Image rules;
    public static Sound sound;
    public String mouse=" ";
    public Music music;
    TextField username;
    public static boolean flag=false; 
    public static boolean ReadAll=false; 
  
     public Menu(int state){
     
     }
//     }
     
     
     public void init(GameContainer gc,StateBasedGame sbg)throws SlickException{
     mainMenu=new Image("Res/uno5.png");
     playNow=new Image("Res/play button3.jpg");
     exitGame=new Image("Res/exit button3.jpg");
     volume=new Image("Res/volumebtn3.png");
     ScoreBoard= new Image("Res/SBButton.png");
     //rules=new Image("res/rules.png");
     HowToPlay=new Image("Res/how to play button.jpg");
     music=new Music("Res/Off Limits.wav");
     music.setVolume(0.5f);
     music.loop();
     sound=new Sound("Res/button sound.wav");
     username=new TextField(gc,gc.getDefaultFont(),530,200,250,80);
     username.setBorderColor(Color.black);
     username.setBackgroundColor(Color.black);
     username.setTextColor(Color.white);
     username.setText("ENTER USERNAME");
     username.setMaxLength(20);
     username.setCursorPos(14);
     
     
      }
     public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException{
     g.setColor(Color.white);
     mainMenu.draw(0, 0, 1300, 700);
     g.drawString(mouse, 50, 50);
     playNow.draw(600,425,175,50);
     HowToPlay.draw(600,500,175,50);
     exitGame.draw(600,575,175,50);
     ScoreBoard.draw(600,650,175,50);
     volume.draw(1200,15,130,60);
         if(flag==true){
     username.render(gc, g);
     }
     }
     public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException{
     int x=Mouse.getX();
     int y=Mouse.getY();
     mouse="x=" + x +"y=" +y;
          
    //play button
     if((x>600 && x<770) && (y>225&&y<275) ){
         if(Mouse.isButtonDown(0)){
             flag=true;
         }
     }
     //entering game state
       if(flag && username.getText().length()!=0)
       {
       if(Keyboard.isKeyDown(Input.KEY_ENTER))
       {
           sound.play();
           flag=false;
           MainGame.username=username.getText();
           username.setText("ENTER USERNAME");
         sbg.enterState(1);
       }
       }
     //how to play button
     if((x>600 && x<770) && (y>150&&y<200) ){
         if(Mouse.isButtonDown(0)){
           sound.play();
           
           sbg.enterState(2);
         }
     }
     
     //music button
      if((x>1200 && x<1290) && (y>625&&y<685) ){
          if(music.playing()){
         if(Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)){
             Mouse.setCursorPosition(x, 624);
             music.stop();
         }  
          }
          else{
          if(Mouse.isButtonDown(Input.MOUSE_LEFT_BUTTON)){
              Mouse.setCursorPosition(x, 624);
              music.loop();
          }
          }
     }
      //exit button
    if((x>600 &&x <770) &&(y>75 && y<125)){
    
        if(Mouse.isButtonDown(0)){
             sound.play();
             
          System.exit(0);
         }
        
    }
      //ScoreBoard button
    if((x>600 &&x <770) &&(y>5 && y<45)){
    
        if(Mouse.isButtonDown(0)){
            sound.play();
            ReadAll=true;
           sbg.enterState(5);
         }
        
    }
    }
     
     public int getID(){
     return 0;
     }
}
