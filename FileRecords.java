/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;



/**
 *
 * @author abdog
 */
public class FileRecords 
{
    public float xPos;
    public float yPos;
    public String Score;
    String Name;
    
    public FileRecords()
    {
        this.xPos = 580;
        this.yPos = 700;
    }
    
    public FileRecords(int xPos, int yPos)
    {
        this.xPos = xPos;
        this.yPos = yPos;
    }
}
