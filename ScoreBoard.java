/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;

import java.awt.Font;
import java.util.ArrayList;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author abdog
 */
public class ScoreBoard extends BasicGameState
{
    Image scoreboard;
    Image volume;
    public Sound sound;
    public String mouse=" ";
    public Music music;
    Image MainMenu;
    public ArrayList<FileRecords> fr = new ArrayList<FileRecords>();
    
    public ScoreBoard(int state)
    {
    }
    
    public void init(GameContainer gc,StateBasedGame sbg)throws SlickException
    {
         scoreboard=new Image("Res/scb.png");
         MainMenu= new Image("Res/MainMenuButton.png");
    }
    
    
    public void render(GameContainer gc,StateBasedGame sbg,Graphics g)throws SlickException
    {
         scoreboard.draw(0, 0, 1300, 700);
         MainMenu.draw(1100,650,175,50);
           
         for(int i=0; i<fr.size(); i++)
         {
             g.drawString(fr.get(i).Name, fr.get(i).xPos-80, fr.get(i).yPos+50*i);
             g.drawString(fr.get(i).Score, fr.get(i).xPos + 150, fr.get(i).yPos+50*i);
         }
         
         

         
    }
    
    
    public void update(GameContainer gc,StateBasedGame sbg,int delta)throws SlickException
    {
       if(Menu.ReadAll)
       {
        ArrayList<FileRecords> fr2 = new ArrayList<FileRecords>();
        FileStream fs = new FileStream();
        fr = fs.readScore(fr2);
        System.out.println("TMAAAM");
        Menu.ReadAll=false;
       } 
       if(fr.get(fr.size()-1).yPos >= -200)
       {
            for(int i=0; i<fr.size(); i++)
            {
                fr.get(i).yPos-=0.04*delta;
            }
       }
       else
       {
           for(int i=0; i<fr.size(); i++)
         {
            fr.get(i).yPos = 700;
         }
       }
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
     return 5;
     }
}
