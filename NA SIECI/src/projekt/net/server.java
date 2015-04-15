package projekt.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projekt.Game;

public class server extends Thread{
    
    private DatagramSocket socket;
    private Game game;
    
    public server(Game game){
        this.game = game;
        try {
            this.socket = new DatagramSocket(3333);
        } catch (SocketException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run(){
        while(true){
            byte[] data = new byte[1024];
            DatagramPacket packet = new DatagramPacket(data, data.length);
            
            try {    
                socket.receive(packet);
            } catch (IOException ex) {
                Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String message = new String(packet.getData());
            
            if(message.equalsIgnoreCase("ping")){
                System.out.println("CLIENT > " + message);
                sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
            }
        }
    }
    
    public void sendData(byte[] data, InetAddress IP, int port){
        DatagramPacket packet = new DatagramPacket(data, data.length, IP, port);
        
        try {
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
