package unocardgame;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector2f;
import java.util.*;
import static unocardgame.player1.PlayerScoreCalculator;

public class MainGame extends BasicGameState
{

    public String mouse = "";
    public Sound CardPlayed;
    public ArrayList<String> CardCounter = new ArrayList<>();
    Font font;
    public static TrueTypeFont font2;
    public static String username;
    Image bg;
    Image win;
    Image lose;
    Image tie;
    Image Direction;
    Card[] AI = new Card[3];
    String mode = "Play";
    ArrayList<Card> Deck = new ArrayList<>();
    ArrayList<Card> OnGround = new ArrayList<>();
    player1[] Player = new player1[4];
    CardDeck x = new CardDeck();
    int TestingCards = 0;
    boolean m3aya = true;
    Image DrawButton;
    Image unobutton;
    Card cardTest;
    Card Pile;
    Card UNO;
    Card ToBePlayed = null;
    AiClass Ai = new AiClass();
    int Turn = 0;
    public ArrayList<Card> wildChoices = new ArrayList<>();
    public ArrayList<String> Colors = new ArrayList<>();
    public String chosenWild = "";
    //Set true when a wild card is chosen
    public boolean drawWildCards = false;
    //Set true if a wild card is ALREADY 
    public boolean wildDrawn = false;
    public String CardColor = "";
    public static Boolean saveScore = true;

    public MainGame(int state)
    {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        Turn=0;
        drawWildCards=false;
        wildDrawn=false;
        chosenWild="";
        ToBePlayed=null;
        mode="Play";
        CardColor="";
        CardPlayed= new Sound("Res/PlayCard.wav");
        ///////////////////// 4 Wild Cards initialization
        Colors.add("Blue");
        Colors.add("Green");
        Colors.add("Red");
        Colors.add("Yellow");
        for (int i = 0; i < 4; i++)
        {
            //Image wc = new Image("res/w"+i+".png");
            Card wc = new Special(Colors.get(i), "WildCard");
            wc.CardImage = new Image("Res/" + Colors.get(i) + ".png");
            wildChoices.add(wc);
        }
        ////////////////Set position of empty wild cards
        int xPos = 1200;
        int yPos;
        for (int i = 0; i < 4; i++)
        {
            yPos = 120 + 108 * i;
            wildChoices.get(i).yPos = yPos;
            wildChoices.get(i).xPos = xPos;
            wildChoices.get(i).lowerlimit = new Vector2f();
            wildChoices.get(i).upperlimit = new Vector2f();

            //ResetBounds(wildChoices.get(i));
        }

        DrawButton = new Image("res/Back_1.png");
        unobutton = new Image("res/UnoButton12345.png");
        CardCounter.clear();
        CardCounter.add("7");
        CardCounter.add("7");
        CardCounter.add("7");
        font2 = new TrueTypeFont(new java.awt.Font("Helvetica", 0, 40), false);

        cardTest = new normalCard("", -1);
        Pile = new normalCard("", -1);
        UNO = new normalCard("", -1);
        Deck = x.Deck_Generator();
        x.shuffle(Deck);
        for (int i = 0; i < 4; i++)
        {
            Player[i] = x.distribute(Deck);
            Player[i].uno = false;
        }
        Pile.CardImage = DrawButton;
        Pile.xPos = 550;
        Pile.yPos = 330;
        Pile.lowerlimit = new Vector2f();
        Pile.upperlimit = new Vector2f();
        ResetBounds(Pile);
        UNO.CardImage = unobutton;
        UNO.xPos = 1075;
        UNO.yPos = 530;
        UNO.lowerlimit = new Vector2f();
        UNO.upperlimit = new Vector2f();
        ResetBounds(UNO);
        PlayerCardsSettings(Player[0].getPlayerCards(), cardTest);
        AIinit();
        for (int i = 1; i <= 3; i++)
        {
            AiCardsInit(i);
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {

        g.setFont(font2);

        Image MainViewBG = new Image("res/main_view_backgrond.jpg");
        Image char1 = new Image("res/char385.png");
        Image char2 = new Image("res/char485.png");
        Image char3 = new Image("res/char190.png");
        Image cardscounter1 = new Image("res/counter12.png");
        Direction = new Image("Res/" + GameRules.GameSequnece + ".png");

        g.drawImage(MainViewBG, -180, -110);
        g.drawImage(char1, 114, 300);
        g.drawImage(char2, 1050, 300);
        g.drawImage(char3, 582, 10);
        g.drawImage(unobutton, 1075, 530);
        g.drawImage(cardscounter1, 268, 348);
        g.drawImage(cardscounter1, 637, 140);
        g.drawImage(cardscounter1, 1001, 345);
        g.drawImage(Pile.CardImage, 550, 330);
        g.drawImage(Direction, 470, 250);
        g.drawString(CardCounter.get(2), 282, 347);
        g.drawString(CardCounter.get(1), 651, 141);
        g.drawString(CardCounter.get(0), 1015, 345);
        g.setColor(Color.black);
        g.drawString(CardColor, 600, 250);


        for (int i = 0; i < OnGround.size(); i++)
        {
            OnGround.get(i).CardImage.draw(OnGround.get(i).xPos, OnGround.get(i).yPos);
        }

        for (int i = 0; i < Player[0].getPlayerCards().size(); i++)
        {
            Player[0].getPlayerCards().get(i).CardImage.draw(Player[0].getPlayerCards().get(i).xPos, Player[0].getPlayerCards().get(i).yPos);
        }

        //////////Draws empty wild cards if not already drawn
        if (drawWildCards)
        {
            for (int i = 0; i < 4; i++)
            {
                Card tmp2 = wildChoices.get(i);
                tmp2.CardImage.draw(tmp2.xPos, tmp2.yPos);
            }
        }

        /////////////Erases wild cards if they are drawn and a color is selected
        /*else if (!drawWildCards)
         {
         for (int i = 0; i < 4; i++)
         {
         Card tmp2 = wildChoices.get(i);
         tmp2.CardImage.destroy();
         }
         wildDrawn = true;
         }*/
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {

        CardCounter.set(0, String.valueOf(Player[1].getPlayerCards().size()));
        CardCounter.set(1, String.valueOf(Player[2].getPlayerCards().size()));
        CardCounter.set(2, String.valueOf(Player[3].getPlayerCards().size()));

        if (GameRules.WINorLOSE(Player).equals("TIE"))
        {

            if (Turn != 0)
            {
                CardColor = OnGround.get(OnGround.size() - 1).color;
                Pile.CardImage = new Image("res/Back_1.png");
                m3aya = PlayerCardsSettings(Player[0].getPlayerCards(), OnGround.get(OnGround.size() - 1));
                for (int i = 1; i <= 3; i++)
                {
                    AiCardsInit(i);
                }
            }
            if (Turn == 0)
            {
                if (!m3aya)
                {
                    Pile.CardImage = new Image("res/Back.png");
                }
                else
                {
                    Pile.CardImage = new Image("res/Back_1.png");
                }
                if (Player[0].getPlayerCards().size() == 2)
                {
                    SetButtonBounds(UNO);
                }
                else
                {
                    unobutton = new Image("res/UnoButton12345.png");
                    ResetBounds(UNO);
                }
                if (UNO.lowerlimit.x != -20 && CardChosen(UNO, gc))
                {
                    UnoButtonPressed(Player[0]);
                    ResetBounds(UNO);
                    unobutton = new Image("res/UnoButton12345(Clicked).png");
                }
                if (!m3aya)
                {

                    SetButtonBounds(Pile);
                }
                if (Pile.lowerlimit.x != -20 && CardChosen(Pile, gc))
                {
                    ArrayList<Card> temp = Player[0].getPlayerCards();
                    temp = x.plus2(temp, Deck, OnGround);
                    Player[0].setPlayercards(temp);
                    m3aya = PlayerCardsSettings(Player[0].getPlayerCards(), OnGround.get(OnGround.size() - 1));
                    if (!m3aya)
                    {
                        Turn = GameRules.turns(Turn, Player, Deck, OnGround, OnGround.get(OnGround.size() - 1));
                        m3aya = true;
                    }
                    ResetBounds(Pile);
                }
                    
                UserChoseCard(delta, gc);
            }
            else if (Turn == 1)
            {
                AiPlayCard(1, delta);
                if (Turn == 0)
                {
                    PlayerCardsSettings(Player[0].getPlayerCards(), OnGround.get(OnGround.size() - 1));
                }
            }
            else if (Turn == 2)
            {
                AiPlayCard(2, delta);
                if (Turn == 0)
                {
                    PlayerCardsSettings(Player[0].getPlayerCards(), OnGround.get(OnGround.size() - 1));
                }
            }
            else if (Turn == 3)
            {
                AiPlayCard(3, delta);
                if (Turn == 0)
                {
                    PlayerCardsSettings(Player[0].getPlayerCards(), OnGround.get(OnGround.size() - 1));
                }
            }
        }
        else
        {
            if (saveScore)
            {
                WinningState.WinScore = PlayerScoreCalculator(Player);
                FileStream fs = new FileStream();
                saveScore = false;
                if (GameRules.WINorLOSE(Player) == "LOST")
                {
                    init(gc, sbg);
                    OnGround.clear();
                    fs.addScore(0, username);
                    sbg.enterState(4);
                }
                else
                {
                    init(gc, sbg);
                    OnGround.clear();
                    fs.addScore(WinningState.WinScore, username);
                    sbg.enterState(3);
                }
            }
        }

    }

    public int getID()
    {
        return 1;
    }

    public boolean MouseLeftPressed()
    {

        if (Mouse.isButtonDown(0))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void SetButtonBounds(Card card)
    {
        card.lowerlimit.x = card.xPos;
        card.lowerlimit.y = card.yPos;
        card.upperlimit.x = card.lowerlimit.x + card.CardImage.getWidth() - 25;
        card.upperlimit.y = card.lowerlimit.y + card.CardImage.getHeight();
    }

    public void ResetBounds(Card card)
    {
        card.lowerlimit.x = -20;
        card.lowerlimit.y = -20;
        card.upperlimit.x = -20;
        card.upperlimit.y = -20;

    }

    public boolean MouseHoveredOnCard(Card card, GameContainer gc)
    {
        Input input = gc.getInput();
        if (input.getMouseX() > card.lowerlimit.x && input.getMouseX() < card.upperlimit.x && input.getMouseY() > card.lowerlimit.y && input.getMouseY() < card.upperlimit.y)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean CardChosen(Card card, GameContainer gc)
    {
        if (MouseHoveredOnCard(card, gc) && MouseLeftPressed())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean PlayerCardsSettings(ArrayList<Card> inHand, Card onGround) throws SlickException
    {
        boolean found = false;
        int initX;
        if (inHand.size() <= 7)
        {
            initX = 500;
        }
        else
        {
            initX = 400;
        }
        for (int i = 0; i < inHand.size(); i++)
        {
            inHand.get(i).xPos = (int) (initX + (50 * i));
            inHand.get(i).yPos = 580;
            inHand.get(i).lowerlimit = new Vector2f();
            inHand.get(i).upperlimit = new Vector2f();
            if (inHand.get(i).color.equals(onGround.color) || onGround.color.equals("") || inHand.get(i).color.equals("Black"))
            {
                SetButtonBounds(inHand.get(i));
                found = true;
            }
            else if (inHand.get(i) instanceof Special && inHand.get(i).GetType().equals(onGround.GetType()))
            {
                SetButtonBounds(inHand.get(i));
                found = true;
            }
            else if (inHand.get(i) instanceof normalCard && inHand.get(i).GetNumber() == onGround.GetNumber())
            {
                SetButtonBounds(inHand.get(i));
                found = true;
            }
        }
        return found;
    }

    public void AIinit() throws SlickException
    {
        AI[0] = new Special("", "");
        AI[0].xPos = 1100;
        AI[0].yPos = 333;
        AI[0].CardImage = new Image("res/Back.png");
        AI[1] = new Special("", "");
        AI[1].xPos = 672;
        AI[1].yPos = 62;
        AI[1].CardImage = new Image("res/Back.png");
        AI[2] = new Special("", "");
        AI[2].xPos = 203;
        AI[2].yPos = 328;
        AI[2].CardImage = new Image("res/Back.png");
    }

    public void AiPlayCard(int AiNumber, int delta)
    {

        if (mode.equals("Play"))
        {

            ToBePlayed = Ai.CardDes(Player[AiNumber].getPlayerCards(), OnGround.get(OnGround.size() - 1));
            if (ToBePlayed.color.equals(""))
            {
                Player[AiNumber].setPlayercards(x.plus2(Player[AiNumber].getPlayerCards(), Deck, OnGround));
                AiCardsInit(AiNumber);
                ToBePlayed = Ai.CardDes(Player[AiNumber].getPlayerCards(), OnGround.get(OnGround.size() - 1));
            }
            else if (ToBePlayed instanceof Special && ((ToBePlayed.GetType().equals("WildCard") || ToBePlayed.GetType().equals("+4"))))
            {
                ToBePlayed.color = Ai.WildCardEffect(Player[AiNumber].getPlayerCards());
            }
            if(ToBePlayed.color.equals("Black"))ToBePlayed.color="Red";
            if (!ToBePlayed.color.equals(""))
            {
                OnGround.add(ToBePlayed);
            }
            mode = "GUI";
        }
        else
        {

            if (ToBePlayed.color.equals(""))
            {
                Turn = GameRules.turns(Turn, Player, Deck, OnGround, ToBePlayed);
                AiCardsInit(AiNumber);
                mode = "Play";
            }
            else if (AiNumber == 1 && ToBePlayed.xPos > 670)
            {
                ToBePlayed.xPos -= (0.6 * delta);
            }
            else if (AiNumber == 2 && ToBePlayed.yPos < 320)
            {
                ToBePlayed.yPos += (0.6 * delta);
            }
            else if (AiNumber == 3 && ToBePlayed.xPos < 670)
            {
                ToBePlayed.xPos += (0.6 * delta);
            }
            else
            {
                Turn = GameRules.turns(Turn, Player, Deck, OnGround, ToBePlayed);
                AiCardsInit(AiNumber);
                mode = "Play";
                CardPlayed.play();
            }
        }
    }

    public void AiCardsInit(int AiNumber)
    {
        for (int i = 0; i < Player[AiNumber].getPlayerCards().size(); i++)
        {
            Player[AiNumber].getPlayerCards().get(i).xPos = AI[AiNumber - 1].xPos;
            Player[AiNumber].getPlayerCards().get(i).yPos = AI[AiNumber - 1].yPos;
        }
    }

    public void UnoButtonPressed(player1 Player)
    {
        Player.uno = true;

    }

    public void DrawButtonPressed(ArrayList<Card> Player, ArrayList<Card> Deck, ArrayList<Card> OnGround)
    {
        x.plus2(Player, Deck, OnGround);
    }

    public int PassButtonPressed(int turn)
    {
        if (GameRules.GameSequnece.equals("Clockwise"))
        {
            return 1;
        }
        else
        {
            return 3;
        }

    }

    public void UserChoseCard(int delta, GameContainer gc) throws SlickException
    {
        // <editor-fold defaultstate="collapsed" desc="User Choose Card">

        for (int i = 0; i < Player[0].getPlayerCards().size(); i++)
        {
            Card temp = Player[0].getPlayerCards().get(i);
            if (MouseHoveredOnCard(temp, gc))
            {
                if (temp.yPos > 560)
                {
                    temp.yPos -= delta;
                }
            }
            else
            {
                if (temp.yPos < 580 && temp.lowerlimit.x != -20)
                {
                    temp.yPos += delta;
                }
            }
        }
        for (int i = 0; i < Player[0].getPlayerCards().size(); i++)
        {
            Card temp = Player[0].getPlayerCards().get(i);
            if (CardChosen(temp, gc) || (temp instanceof Special && (temp.GetType().equals("WildCard") || temp.GetType().equals("+4")) && drawWildCards))
            {
                if (temp instanceof Special && (temp.GetType().equals("WildCard") || temp.GetType().equals("+4")))
                {
                    drawWildCards = true;

                    Input input;
                    input = gc.getInput();
                    if (input.isKeyDown(Input.KEY_1) || input.isKeyDown(Input.KEY_NUMPAD1))
                    {
                        chosenWild = "Blue";
                        drawWildCards = false;
                    }
                    else if (input.isKeyPressed(Input.KEY_2) || input.isKeyPressed(Input.KEY_NUMPAD2))
                    {
                        chosenWild = "Green";
                        drawWildCards = false;
                    }
                    else if (input.isKeyPressed(Input.KEY_3) || input.isKeyPressed(Input.KEY_NUMPAD3))
                    {
                        chosenWild = "Red";
                        drawWildCards = false;
                    }
                    else if (input.isKeyPressed(Input.KEY_4) || input.isKeyPressed(Input.KEY_NUMPAD4))
                    {
                        chosenWild = "Yellow";
                        drawWildCards = false;
                    }
                    if (!drawWildCards)
                    {
                        ResetBounds(temp);
                        Player[0].getPlayerCards().remove(temp);
                        temp.color = chosenWild;
                        OnGround.add(temp);
                        Turn = GameRules.turns(Turn, Player, Deck, OnGround, temp);
                        GameRules.Uno_call(Player[0], Deck, OnGround);
                        PlayerCardsSettings(Player[0].getPlayerCards(), OnGround.get(OnGround.size() - 1));
                        CardPlayed.play();
                    }
                }
                else
                {
                    drawWildCards = false;
                    temp.xPos = 670;
                    if (temp.yPos > 370)
                    {
                        temp.yPos -= delta;
                    }
                    else
                    {
                        ResetBounds(temp);
                        Player[0].getPlayerCards().remove(temp);
                        OnGround.add(temp);
                        Turn = GameRules.turns(Turn, Player, Deck, OnGround, temp);
                        GameRules.Uno_call(Player[0], Deck, OnGround);
                        PlayerCardsSettings(Player[0].getPlayerCards(), OnGround.get(OnGround.size() - 1));
                        CardPlayed.play();
                    }
                }
            }

        }
        // </editor-fold>
    }
}
