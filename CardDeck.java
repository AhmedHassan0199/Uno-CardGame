/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
  package unocardgame;
import unocardgame.Card;
import unocardgame.normalCard;
  import java.util.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
/**
 *
 * @author Reem Usama
 */
public class CardDeck {
   
    String types2[]={"Reverse","Skip","+2"};
    String types4[]={"+4","WildCard"};
    String colors[]={"Red","Yellow","Green","Blue"};
    public ArrayList<Card> Deck_Generator()throws SlickException
    {
     // ArrayList<Card> cards=new ArrayList<Card>();
      ArrayList<Card> cards=new ArrayList<>();
      normalCard ncards[]=new normalCard[76];
      int x=0;
      for(int i=0;i<4;i++) 
      {   int number=1;
          for(int j=x;j<x+18;j+=2)
          {
              ncards[j]= new normalCard(colors[i], number);
              ncards[j].CardImage=new Image("res/"+colors[i]+"_"+number+".png");
              ncards[j+1]= new normalCard(colors[i], number);
              ncards[j+1].CardImage=new Image("res/"+colors[i]+"_"+number+".png");
                
              number++;
              
          }
          x+=18;
      }
    ncards[72]= new normalCard(colors[0], 0);
          ncards[72].CardImage=new Image("res/"+colors[0]+"_"+"0"+".png");
    ncards[73]= new normalCard(colors[1], 0);
          ncards[73].CardImage=new Image("res/"+colors[1]+"_"+"0"+".png");
    ncards[74]= new normalCard(colors[2], 0);
          ncards[74].CardImage=new Image("res/"+colors[2]+"_"+"0"+".png");
    ncards[75]= new normalCard(colors[3], 0);
          ncards[75].CardImage=new Image("res/"+colors[3]+"_"+"0"+".png");
    
      int w=0;
      Special scards[]=new Special[32];
      for(int i=0;i<4;i++)
      {    int z=0;
          for(int j=w;j<w+6;j+=2){
         
              scards[j]=new Special(colors[i],types2[z]);
              scards[j].CardImage=new Image("res/"+colors[i]+"_"+types2[z]+".png");
              scards[j+1]=new Special(colors[i],types2[z]);
              scards[j+1].CardImage=new Image("res/"+colors[i]+"_"+types2[z]+".png");
              z++;
            }
        w+=6;
      }
      int z=24;
       for(int i=0;i<2;i++)
      {   
          for(int j=0;j<4;j++){
         
              scards[z]=new Special("Black",types4[i]);
              scards[z].CardImage=new Image("res/"+"Black"+"_"+types4[i]+".png");
              //scards[z+1]=new Special(colors[i],types4[i]);
               z++;
            }
        
      }
      
      for(int i=0;i<ncards.length;i++)
      {
        cards.add(ncards[i]);
      }
      
     
      for(int i=0;i<scards.length;i++)
      { 
        
        cards.add(scards[i]);
       
      }
     
      
     // System.out.println(cards.length);
      return cards;
    }
    public ArrayList<Card> shuffle(ArrayList<Card> cards){
    
        Random rgen = new Random();  // Random number generator			
 
        for (int i=0; i<cards.size(); i++) 
        {  
           int randomPosition = rgen.nextInt(cards.size());
           Card temp = cards.get(i);
           cards.set(i,cards.get(randomPosition));

           cards.set(randomPosition,temp);
        }
        
        //System.out.println(cards.size());
	return cards;
       
    }
    public player1 distribute(ArrayList<Card> cards)
    {  
        int itr=0;
        ArrayList<Card> Temp=new ArrayList<>();
        player1 Players = new player1();

             for(int j=0;j<7;j++)
             {
                Temp.add(cards.get(itr));
                cards.remove(itr);
             }
              Players.setPlayercards(Temp);
         return Players;
    }
     public ArrayList<Card> drawCard(ArrayList<Card> myCards, ArrayList<Card> cards, ArrayList<Card> OnGround) 
    {
        
        if(cards.isEmpty())
        {
            ArrayList<Card> tempList= new ArrayList<>();
            tempList = GameRules.DeckCheck(OnGround, cards);
            for(int i=0;i<tempList.size();i++)
            {
                cards.add(tempList.get(i));
            }
        }
            
        
        
        myCards.add(cards.get(0));
        cards.remove(0);
        return myCards;
    }
    public ArrayList<Card> plus2(ArrayList<Card> myCards, ArrayList<Card> cards, ArrayList<Card> OnGround)
    {
        
        myCards = drawCard(myCards, cards,OnGround);
        
        return drawCard(myCards, cards,OnGround);
    }
    public ArrayList<Card> plus4(ArrayList<Card>myCards, ArrayList<Card> cards, ArrayList<Card> OnGround)
    {
        for(int i=0; i<4; i++)
            myCards = drawCard(myCards, cards,OnGround);
        
        return myCards;
    }
}
