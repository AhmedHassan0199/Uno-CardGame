/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author lenovo
 */
public class LosingState extends BasicGameState
{
    Image LosingImage; 
    Image MainMenu;
    LosingState(int LosingState)
    {
    }
    public int getID()
    {
        return 4;
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
             LosingImage=new Image("Res/Losing.jpg");
             MainMenu= new Image("Res/MainMenuButton.png");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException
    {
        LosingImage.draw(0,0);
        MainMenu.draw(1100,650,175,50);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        int x=Mouse.getX();
        int y=Mouse.getY();
        if((x>1100 && x<1270) && (y>5&&y<50) ){
         if(Mouse.isButtonDown(0)){
          Menu.sound.play();
           
           sbg.enterState(0);
         }
     }
    }
    
}
