/*
class RiddlesServer will continue to run and act as the main server
*/
import java.io.*;
import java.util.*;
import java.net.*;

public class RiddlesServer extends Thread
{

   String answer;//stores the answer
   BufferedReader in;
   PrintWriter out;
   Socket soc;
   LinkedList<StringPair> pair;
   //stores the linked list
   public RiddlesServer(LinkedList<StringPair> myList)
   {
      this.pair = myList;
   }
   
   public void run()
   {
   
      try
      {
         //create socket on port 5000
         ServerSocket serversocket = new ServerSocket(5000);
         int counter = 0;//counts which server its on
         //in = new BufferedReader(new InputStreamReader(client.getInputStream()));
         //out = new PrintWriter(client.getOutputStream());
         String END = "END";
         while(true)
         {
            counter++;
            //accepts socket and assigns it to object
            Socket client = serversocket.accept();
         
            System.out.println();
            System.out.println(">>" + "Client: "+ counter + " started");//tells user it has started the connection
            //creates input and output streams
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter out = new PrintWriter(client.getOutputStream());
         
            //runs the Riddles Runner object
            
            RiddlesRunner runner = new RiddlesRunner(client,counter,pair);
            runner.start();
            String res = in.readLine();
            if(END.equals(res))
            {
               break;
            }
         
         }
         
      
      }catch(IOException e){
         e.printStackTrace();
      }
       
   }
}
