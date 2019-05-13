/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;

import unocardgame.Card;

/**
 *
 * @author lenovo
 */
class Special extends Card
{
     String type;  
    
    
    public Special(String color, String type)
    {
        this.color = color;
        this.type = type;
    }
    public  String  GetType()
    {
        return this.type;
    }
    public  int GetNumber()
    {
        return -1;
    }
    
}