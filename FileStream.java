/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unocardgame;
import java.util.*;
import java.lang.*;
import java.io.*;

public class FileStream 
{
    public Formatter objWriter;
    
    public FileWriter fw;
    public BufferedWriter writer;
    
    private Scanner objReader;
    
    // DELETE THIS FUN
    public void StreamWriter()
    {
        try
        {
            objWriter = new Formatter("scores.txt");
        }
        catch(Exception e)
        {
            System.out.println("Error: Can't open the required file");
        }
    }
    
    public void addScore(int score1, String p1)
    {
        String rec1 = p1 + ":" + String.valueOf(score1);
        try 
        {
            writer = new BufferedWriter(new FileWriter("scores.txt", true));
            writer.write(rec1);
            writer.newLine();
            writer.close();
        } catch (Exception e)
        {
            System.out.println("ERROR!!!!!");
        }
    }
    
    public ArrayList<FileRecords> readScore(ArrayList<FileRecords> fr)
    {
        
         try
         {
             objReader = new Scanner(new File("scores.txt"));
         }
         catch(Exception e)
         {
             System.out.println("Error accessing the file!");
         }
 
          while(objReader.hasNext())
         {
             FileRecords fileRec = new FileRecords();
             String[] sc1 = objReader.nextLine().split(":");
             System.out.println(sc1[0]);
             System.out.println(sc1[1]);
             fileRec.Name = sc1[0];
             fileRec.Score = sc1[1];
             fr.add(fileRec);
             
         }
          Collections.sort(fr, new Comparator<FileRecords>() {
             @Override
             public int compare(FileRecords o1, FileRecords o2) {
                 return Integer.valueOf(o2.Score).compareTo(Integer.valueOf(o1.Score));
             }
         });

          objReader.close();
          
          return fr;
   } 
 
    
    
   public void closeWriter()
    {
        objWriter.close();
    }
   
   public void closeReader()
    {
        objReader.close();
    }
}
