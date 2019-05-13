package unocardgame;
import java.awt.Rectangle;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public abstract class Card
{
    Image CardImage;
    int xPos,yPos;
    Vector2f lowerlimit,upperlimit;
    String color;
    public abstract String GetType();
    public abstract int GetNumber();
    
}

class normalCard extends Card
{
    int num;
    normalCard(String color, int num)
    {
        this.color = color;
        this.num = num;
    }
    public  String GetType()
    {
        return null;
    }
    public  int GetNumber()
    {
        return this.num;
        
    }
    
}

