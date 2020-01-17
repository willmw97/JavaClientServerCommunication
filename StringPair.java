/*
Author: Marshal

*/
import java.util.*;
import java.io.*;

public class StringPair implements Serializable
{
   //Constructor
   private String firstString;
   private String secondString;
    
    //will set string pair
   public StringPair(String firstString, String secondString)
   {
      this.firstString = firstString;
      this.secondString = secondString;
   }
   //resets string
   public void StringReset()
   {
      firstString = "";
      secondString = "";
   }
    
   
   //set string
   public void setR(String firstString)
   {
      this.firstString = firstString;
   }
   //set string
   public void setA(String secondString)
   {
      this.secondString = secondString;
   }
    
    
   //returns first string
   public String firstString()
   {
      return firstString;
   }
   //returns second string
   public String secondString()
   {
      return secondString;
   }
   //will print the toString =
   public String ToString()
   {
      return firstString+"\n"+secondString;
   }


}