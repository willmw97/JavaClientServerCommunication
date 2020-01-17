Author: Marshal Will 

Program will initiate a server and several clients which will communicate with each other. 
Below is a list of what it will acomplish.

1. As before, the Riddles/Answers will come from Riddles.txt 
   and be loaded into a Java LinkedList<E>. 
   This one LinkedList<E> will be used by all the Server threads and the Initial thread.
  
2. Additionally, the Riddle part of all will be loaded into a String array.  
   Only Client Threads use this one array.
   
3. The Answers to inbound Riddles will be found by the Server 
   threads from the linked list riddles found there.
   
4. Initial Thread will alter the order of the items in 
   the linked list every ¼ second.
   
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
   
   **How to Run?**
   
   To run simply put all the files in the same directory and then run RiddlesMain.java
