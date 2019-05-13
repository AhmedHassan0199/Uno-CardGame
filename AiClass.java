package unocardgame;

import java.util.ArrayList;

public class AiClass {
     
    public Card CardDes(ArrayList<Card> inHand, Card OnGround)
    {
               Card ToBePlayed;
               
               ToBePlayed= SpecialToBePlayed(inHand, OnGround);
               
               if(ToBePlayed.color.equals(""))
               {
                   ToBePlayed=NormalToBePlayed(inHand, OnGround);
               }
               
               if(!ToBePlayed.color.equals(""))return ToBePlayed;
               else
               {
                   ToBePlayed.color="";
                     return ToBePlayed;
               }
               
    }
    public Card SpecialToBePlayed(ArrayList<Card> inHand, Card OnGround)
    {
        boolean ColorFound = false;
        for(int i=0;i<inHand.size();i++)
        {
               if(inHand.get(i).color.equals(OnGround.color) && inHand.get(i) instanceof Special)
               {
                   ColorFound=true;
                   break;
               }
        }
        if(ColorFound)
        {
            // <editor-fold defaultstate="collapsed" desc="If Color  Found">
            Card MaxInColor=null;
            for(int i=0;i<inHand.size();i++)
            {
               if(inHand.get(i).color.equals(OnGround.color) && inHand.get(i) instanceof Special)
               {
                   MaxInColor=inHand.get(i);
               }
            }
            inHand.remove(MaxInColor);
            return MaxInColor;
             // </editor-fold>
        }
        else
        {
            // <editor-fold defaultstate="collapsed" desc="If Color Not Found">
            if(OnGround instanceof Special)
            {
                

                if(OnGround.GetType().equals("+2"))
                    {
                        for(int i=0;i<inHand.size();i++)
                        {
                            if(inHand.get(i) instanceof Special && inHand.get(i).GetType().equals("+2"))
                            {
                                Card temp=inHand.get(i);
                                inHand.remove(i);
                                return temp;
                            }
                        }
                    }

                else if(OnGround.GetType().equals("Reverse"))
                {
                    
                    for(int i=0;i<inHand.size();i++)
                    {
                        
                        if(inHand.get(i) instanceof Special && inHand.get(i).GetType().equals("Reverse"))
                        {
                            Card temp=inHand.get(i);
                            inHand.remove(i);
                            return temp;
                        }
                    }
                }
                else if(OnGround.GetType().equals("Skip"))
                {
                    for(int i=0;i<inHand.size();i++)
                    {
                        if(inHand.get(i) instanceof Special && inHand.get(i).GetType().equals("Skip"))
                        {
                            Card temp=inHand.get(i);
                            inHand.remove(i);
                            return temp;
                        }
                    }
                }
        
                  // <editor-fold defaultstate="collapsed" desc="Will Play Wild Or +4">
                    for(int i=0;i<inHand.size();i++)
                    {
                        
                        if(inHand.get(i) instanceof Special && inHand.get(i).GetType().equals("+4"))
                        {
                            Card temp=inHand.get(i);
                            inHand.remove(i);
                            return temp;
                        }
                    } 
                    for(int i=0;i<inHand.size();i++)
                    {
                        
                        if(inHand.get(i) instanceof Special && inHand.get(i).GetType().equals("WildCard"))
                        {
                            Card temp=inHand.get(i);
                            inHand.remove(i);
                            return temp;
                        }
                    }
                    // </editor-fold>
                    
                
        
            }
// </editor-fold>
        }
        
        Card ToBePlayed=new normalCard("",-1);
        return ToBePlayed;
    }
    public Card NormalToBePlayed(ArrayList<Card> inHand, Card OnGround)
    {
         boolean found=false;
         Card MaxTemp=null;
         // <editor-fold defaultstate="collapsed" desc="Choose Suitable Max Color">
                if(OnGround.color.equals("Red"))
                {
                    for(int i=0;i<inHand.size();i++)
                    {
                        if(inHand.get(i) instanceof normalCard && inHand.get(i).color.equals("Red"))
                        {
                            MaxTemp=inHand.get(i);
                            found=true;
                        }
                    }
                }
                else if(OnGround.color.equals("Blue"))
                {
                    for(int i=0;i<inHand.size();i++)
                    {
                        if(inHand.get(i) instanceof normalCard && inHand.get(i).color.equals("Blue"))
                        {
                            MaxTemp=inHand.get(i);
                            found=true;
                        }
                    }
                }
                else if(OnGround.color.equals("Yellow"))
                {
                    
                    for(int i=0;i<inHand.size();i++)
                    {
                        
                        if(inHand.get(i) instanceof normalCard && inHand.get(i).color.equals("Yellow"))
                        {
                            MaxTemp=inHand.get(i);
                            found=true;
                            break;
                        }
                    }
                }
                else if(OnGround.color.equals("Green"))
                {
                    for(int i=0;i<inHand.size();i++)
                    {
                        if(inHand.get(i) instanceof normalCard && inHand.get(i).color.equals("Green"))
                        {
                            MaxTemp=inHand.get(i);
                            found=true;
                        }
                    }
                }
                
        if(found)
        {
        inHand.remove(MaxTemp);
        return MaxTemp;
        }        
        else
        {
            Card temp= new Special("","");
            return temp;
        }
         // </editor-fold>
    }
    public String WildCardEffect(ArrayList<Card>inHand)
    {
        /*
            0->Red
            1->Green
            2->Blue
            3->Yellow
         */
        String color= new String();
        int[] Colors= new int[4];
        for(int i=0;i<inHand.size();i++)
        {
            if(inHand.get(i).color.equals("Red"))
                Colors[0]++;
            else if(inHand.get(i).color.equals("Green"))
                Colors[1]++;
            else if(inHand.get(i).color.equals("Blue"))
                Colors[2]++;
            else if(inHand.get(i).color.equals("Yellow"))
                Colors[3]++;
            
        }
        int Max=0;
        for(int i=0;i<4;i++)
        {
            if(Colors[i]>=Max)
            {
                if(i==0)color="Red";
                if(i==1)color="Green";
                if(i==2)color="Blue";
                if(i==3)color="Yellow";
                Max=Colors[i];
            }
            
        }
        
        return color;
    }
    
}
