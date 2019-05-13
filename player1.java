/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;
import unocardgame.Card;

import java.util.*;
/**
 *
 * @author lenovo
 */
public class player1 {
    private String PlayerName="";
    public boolean uno;
    private  ArrayList<Card> playercards;
    player1(){
    PlayerName="";
    playercards=new ArrayList<Card>();
    }
    public ArrayList<Card> getPlayerCards(){
    return playercards;
    
    }

    public void setPlayerName(String PlayerName) {
        this.PlayerName = PlayerName;
    }

    public String getPlayerName() {
        return PlayerName;
    }


    public void setPlayercards(ArrayList<Card> playercards) {
        
       this.playercards=playercards;
    }

  
   
    
    
    public static int PlayerScoreCalculator(player1[] player){
        int calculateScore=0;
        for(int i=0;i<4;i++){
        calculateScore+=player[i].getPlayerCards().size();
        }
        return calculateScore*10;
    }
    
    
   
}
