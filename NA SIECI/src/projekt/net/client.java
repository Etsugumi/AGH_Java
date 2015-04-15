package projekt.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import projekt.Game;

public class client extends Thread{
    
    private InetAddress IP;
    private DatagramSocket socket;
    private Game game;
    
    public client(Game game, String IP){
        this.game = game;
        try {
            this.socket = new DatagramSocket();
            this.IP = InetAddress.getByName(IP);
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex){
            ex.printStackTrace();
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
            
            System.out.println("SERVER > " + new String(packet.getData()));
        }
    }
    
    public void sendData(byte[] data){
        DatagramPacket packet = new DatagramPacket(data, data.length, IP, 3333);
        
        try {
            socket.send(packet);
        } catch (IOException ex) {
            Logger.getLogger(client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
