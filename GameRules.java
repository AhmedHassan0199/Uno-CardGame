package unocardgame;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameRules
{

    private static CardDeck deck = new CardDeck();
    static String GameSequnece = "AntiClockwise";

    public static void Uno_call(player1 Player, ArrayList<Card> DeckOfCards, ArrayList<Card> OnGround)
    {
        if (Player.uno == false && Player.getPlayerCards().size() == 1)// draw 2 cards 
        {
            deck.plus2(Player.getPlayerCards(), DeckOfCards, OnGround);
        }

    }

    public static int turns(int prevplayer, player1[] Player, ArrayList<Card> DeckOfCards, ArrayList<Card> ongroundcards, Card onground)
    {
        if (onground.color.equals(""))
        {
            System.out.println("PASSING");
            if (GameSequnece.equals("AntiClockwise"))
            {
                return (prevplayer + 1) % 4;
            }
            else
            {
                if (prevplayer == 0)
                {
                    return 3;
                }
                return --prevplayer;
            }
        }

        onground.xPos = 670;
        onground.yPos = 330;

        if (onground instanceof Special)
        {
            if (onground.GetType().equals("Skip"))
            {
                if (GameSequnece.equals("AntiClockwise"))
                {
                    return (prevplayer + 2) % 4;
                }
                else
                {
                    if (prevplayer == 0)
                    {
                        return 2;
                    }
                    else if (prevplayer == 1)
                    {
                        return 1;
                    }
                    return prevplayer -= 2;
                }

            }
            else if (onground.GetType().equals("+2"))
            {
                if (GameSequnece.equals("AntiClockwise"))
                {
                    deck.plus2(Player[(prevplayer + 1) % 4].getPlayerCards(), DeckOfCards, ongroundcards);
                    return (prevplayer + 2) % 4;
                }
                else
                {
                    if (prevplayer == 0)
                    {
                        deck.plus2(Player[3].getPlayerCards(), DeckOfCards, ongroundcards);
                        return 2;
                    }
                    else if (prevplayer == 1)
                    {
                        deck.plus2(Player[0].getPlayerCards(), DeckOfCards, ongroundcards);
                        return 3;
                    }
                    deck.plus2(Player[(prevplayer - 1) % 4].getPlayerCards(), DeckOfCards, ongroundcards);
                    return prevplayer -= 2;
                }

            }
            else if (onground.GetType().equals("+4"))
            {
                if (GameSequnece.equals("AntiClockwise"))
                {
                    deck.plus4(Player[(prevplayer + 1) % 4].getPlayerCards(), DeckOfCards, ongroundcards);
                    return (prevplayer + 1) % 4;
                }
                else
                {
                    if (prevplayer == 0)
                    {
                        deck.plus4(Player[1].getPlayerCards(), DeckOfCards, ongroundcards);
                        return 1;
                    }
                    deck.plus4(Player[(prevplayer - 1) % 4].getPlayerCards(), DeckOfCards, ongroundcards);
                    return prevplayer -= 1;
                }

            }
            else if (onground.GetType().equals("Reverse"))
            {

                if (GameSequnece.equals("AntiClockwise"))
                {

                    GameSequnece = "Clockwise";
                }
                else
                {
                    GameSequnece = "AntiClockwise";
                }

                if (GameSequnece.equals("AntiClockwise"))
                {
                    return (prevplayer + 1) % 4;
                }
                else
                {
                    if (prevplayer == 0)
                    {
                        return 3;
                    }
                    System.out.println("Wasal Hena Safely");
                    return --prevplayer;
                }
            }
            else//WildCard
            {

                if (GameSequnece.equals("AntiClockwise"))
                {
                    return (prevplayer + 1) % 4;
                }
                else
                {
                    if (prevplayer == 0)
                    {
                        return 3;
                    }
                    return --prevplayer;
                }
            }
        }
        else
        {
            if (GameSequnece.equals("AntiClockwise"))
            {
                return (prevplayer + 1) % 4;
            }
            else
            {
                if (prevplayer == 0)
                {
                    return 3;
                }
                return --prevplayer;
            }
        }

    }

    public static void OngroundCheck(player1 Player, ArrayList<Card> ongroundcards, Card playedcard)
    {
        for (int i = 0; i < Player.getPlayerCards().size(); i++)
        {
            if (Player.getPlayerCards().get(i) == playedcard)
            {
                Player.getPlayerCards().remove(i);
                ongroundcards.add(playedcard);
            }
        }

    }

    public static ArrayList<Card> DeckCheck(ArrayList<Card> ongroundcards, ArrayList<Card> deckcards)
    {
        ArrayList<Card> tempList= new ArrayList<>();
        Card temp = ongroundcards.get(ongroundcards.size() - 1);
        ongroundcards.remove(ongroundcards.size() - 1);
        for(int i=0;i<ongroundcards.size();i++)
        {
            if(ongroundcards.get(i) instanceof Special&&(ongroundcards.get(i).GetType().equals("WildCard")||ongroundcards.get(i).GetType().equals("+4")))
            {
                ongroundcards.get(i).color="Black";
            }
                tempList.add(ongroundcards.get(i));
        }
        ongroundcards.clear();
        ongroundcards.add(temp);
        deckcards=deck.shuffle(tempList);
        System.out.println("New Deck Size : "+ deckcards.size() );
        return deckcards;
    }

    public static String WINorLOSE(player1[] player)
    {

        if (player[0].getPlayerCards().isEmpty())
        {
            return "WINNER";
        }

        else if (player[1].getPlayerCards().isEmpty() || player[2].getPlayerCards().isEmpty() || player[3].getPlayerCards().isEmpty())
        {
            return "LOST";
        }

        else
        {
            return "TIE";
        }
    }
}
