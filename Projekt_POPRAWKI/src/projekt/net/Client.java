package projekt.net;

import java.io.*;
import java.net.*;


public class Client
{
   public void go()
   {
      String serverIP = "localhost";
      int port = 1331;
      try
      {
         System.out.println("CLIENT > Connecting...");
         Socket client = new Socket(serverIP, port);
         
         OutputStream outToServer = client.getOutputStream();
         DataOutputStream out = new DataOutputStream(outToServer);

         out.writeUTF("\nCLIENT > I just wanted to say hello!!\n");
         
         InputStream inFromServer = client.getInputStream();
         DataInputStream in = new DataInputStream(inFromServer);
         System.out.println("######## " + in.readUTF());
         
         client.close();
      }catch(IOException e)
      {
         e.printStackTrace();
      }
   }
}