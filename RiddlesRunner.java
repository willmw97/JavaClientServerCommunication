
/*
Class RiddleRunner will control the process of which client files will get passed.

*/
//Import all the libraries' functions just for fun
import java.io.*;
import java.util.*;
import java.net.*;


public class RiddlesRunner extends Thread
{
   //RApair will hold the riddle answer pair for the socket
   LinkedList<StringPair> RApair;
   int clientNumber = 0;//counts which thread the client is on
   Socket client;//holds clients
   BufferedReader in;
   PrintWriter out;
   //passes the client and linkedlist
   public RiddlesRunner(Socket client, int counter, LinkedList<StringPair> myList) throws IOException
   {
      this.client = client;
      clientNumber = counter;
      this.RApair = myList;
   }
   
   public void run()
   {
      try{
         //checks that the connection is established
         System.out.println("Connection Established at: " + client.toString());
         //create input and output streams for server
         in = new BufferedReader(new InputStreamReader(client.getInputStream()));
         out = new PrintWriter(client.getOutputStream());
         //sends which client it is.
         out.println(clientNumber);
         out.flush();//always flush your data
      
         String response = null;
         //runs through twice
         for (int t = 0; t<2;t++)
         {  
            //runs through list
            for(int i = 0; i<RApair.size();i++)
            {
               /******************************
               synchronized will cause them not to finish at same time
               *******************************/
               //synchronized (RApair) 
               //{
                  //Collections.shuffle(RApair);//randomly shuffle the linkedlist
                  //Thread.sleep(250);//sleep for 1/4 second
               //}
               StringPair c = RApair.get(i);//get string pair
               String answer = c.secondString();//get the answer riddle
            
               response = in.readLine();//gets the response from server and print
               System.out.println("Client: "+ clientNumber+": "+ response);
               //sends riddle answer
               out.println(answer);
               out.flush();
               RiddlesRunner.sleep(150);//delay to see exchange
               
               
            }
         }
         response = in.readLine();//response is then read for the client
         System.out.println("Client: "+ clientNumber+": " + response);
         //close all streams
         out.println(response);
         out.flush();
         in.close();
         out.close();
         client.close();
      }catch(IOException | InterruptedException e)
      {
         e.printStackTrace();
      }
      
   }
}
   
   
