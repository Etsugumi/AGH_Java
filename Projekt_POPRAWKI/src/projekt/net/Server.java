package projekt.net;

import java.io.*;
import java.net.*;

public class Server extends Thread
{
   private ServerSocket serverSocket;
   
   public Server(int port) throws IOException
   {
      serverSocket = new ServerSocket(port);
   }

   public void run()
   {
      int c=0;
      while(true)
      {
         try
         {
            System.out.println("***********************************");
            System.out.println("***** WAITING FOR CONNECTIONS *****");
            System.out.println("***********************************");
            Socket server = serverSocket.accept();
            if(server != null) c++;
            System.out.println("######## CONNECTION FROM: " + server.getRemoteSocketAddress());
            
            System.out.println("######## PLAYERS: " + c);
            
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("Hello there :)");
            
            server.close();
         }catch(SocketTimeoutException s)
         {
            System.out.println("****** Socket timed out ******\n");
            break;
         }catch(IOException e)
         {
            e.printStackTrace();
            break;
         }
      }
   }
}