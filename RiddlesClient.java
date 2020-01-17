/*
RiddlesClient class will control each of the individual client threads
*/
import java.io.*;
import java.util.*;
import java.net.*;

public class RiddlesClient extends Thread
{
   //list will contain riddles
   List<String> list;
   String threadname;//sets which thread
   Socket s = null;//create socket
   int countS = 0;//count which client socket
   //riddleLisr is the riddle list
   RiddlesClient(List<String> riddleList)
   {
      list = riddleList;
   }
   
   public void run()
   {
   
      try{
         //create socket
         s = new Socket("localhost",5000);
         //creates sender for outputstream
         PrintWriter sender = new PrintWriter(s.getOutputStream());
         //creates the reader for the inputstream
         BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
         //help read the response
         String re = reader.readLine();
         //cycle for 2 times
         for(int t = 0; t<2;t++)
         {
            //runs through the list
            for(int i = 0;i<list.size();i++)
            {
               /******************************
               synchronized will cause them not to finish at same time
               *******************************/
                                 //gets gets anwser from server and print
               sender.println(list.get(i));
               sender.flush();
            
               String response = reader.readLine();
               System.out.println("From Server: " +re +": " + response);
            }
            System.out.println("");
         
            RiddlesClient.sleep(1000);//sleep before running a second time
         }
         //notifies when the thread has ended
         sender.println("END");
         sender.flush();
         sender.close();
         reader.close();
         s.close();
      }
      catch(Exception e)
      {
         System.out.print(e);
      }
   }
     
}