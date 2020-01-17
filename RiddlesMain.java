//Author: Marshal Will 
//Program will intiate a server. Below is main and what it will acomplish.
/*
1. As before, the Riddles/Answers will come from Riddles.txt 
   and be loaded into a Java LinkedList<E>. 
   This one LinkedList<E> will be used by all the Server threads and the Initial thread.
  
2. Additionally, the Riddle part of all will be loaded into a String array.  
   Only Client Threads use this one array.
3. The Answers to inbound Riddles will be found by the Server 
   threads from the linked list riddles found there.
4. Initial Thread will alter the order of the items in 
   the linked list every ¼ second.
        (i.e., Concurrency control required)
   
5. The Client Threads will choose a riddle from the array, printing the riddle 
   and the thread’s name as it sends it, and then will print the answer 
   and the name of the Server thread upon receipt of the answer. 
   To do so, the Server threads will respond to the riddle with 
   the answer AND the name of the Server Thread answering. 
6. After working through all of the Riddles in the array twice,
   with about 1-second delay between each, the Client thread will
   send a message reading “END”, which tells the paired Server Thread that it is ending.
   
7. Shortly after the end message, the Client and Server threads blow 
   away their sockets, and end themselves.
8. When the Initial Thread, which has been slowly monitoring, 
   finds that the Client threads are done, 
   close Master thread’s ServerSocket (the Master thread is waiting there)
   to allow the Master thread to stop waiting and end.

*/  

//libraries are imported
import java.io.*;
import java.util.*;
import java.net.*;
//main class will run it
public class RiddlesMain extends Thread
{

   public static void main(String[] args) throws IOException
   {
      //create linked list for our riddles
      LinkedList<StringPair> myList = new LinkedList<StringPair>();
      //riddleList will store the answers
      List<String> riddleList = new ArrayList<String>();
      //RApair is an object that will store the pair
      StringPair RApair = null;
      String riddles = null;
      String answers = null;
      
               
      try{
                  
         System.out.println(" ");
         //import the buffered reader
         BufferedReader sb = new BufferedReader(new FileReader("Riddles.txt"));
         String line = null;//line will read in the file list
         //runs while there is something in this list
         while((line = sb.readLine()) != null)
         {
         
            //takes in the riddle part
            if(line.startsWith("<Riddle>"))
            {
               //String temp = line.substring(8);
               String temp = line;
               riddles = temp;
               riddleList.add(riddles);
            }
            //takes in answer part 
            if(line.startsWith("<Answer>"))
            {
               //String temp = line.substring(8);
               String temp = line;
               answers = temp;
            }
            //adds riddle answer pairs to linkedlist
            if(riddles != null && answers != null)
            {
               RApair = new StringPair(riddles,answers);
               myList.add(RApair);
               riddles = null;
               answers = null;
            }
            
         }
          
         sb.close();
      }
      catch(FileNotFoundException c)
      {
         System.out.println("An error occured.");
         c.printStackTrace();
      }
         
      try{
      //starts all threads
      //*****************************
      //Note: Not all threads will end at the same time
      //*****************************  
      //loads all 3 client threads
         //synchronized (riddleList) 
         //{
         Collections.shuffle(riddleList);//randomly shuffle the linkedlist
         Thread.sleep(250);//sleep for 1/4 second
         
         RiddlesClient c1 = new RiddlesClient(riddleList);
         c1.start();
         RiddlesClient c2 = new RiddlesClient(riddleList);
         c2.start();
         RiddlesClient c3 = new RiddlesClient(riddleList);
         c3.start();
         //}
      //intiate the server    
         RiddlesServer server = new RiddlesServer(myList);
         server.start();
          
         System.out.println("Server will be waiting...");
         System.out.println();
      }catch(Exception e)
      {
         System.out.print(e);
      }
   
      
   }
}