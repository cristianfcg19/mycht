/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author css124646
 */
public class ServerMain {
    private ServerSocket serverSocket;

    public ServerMain(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }
    
    public void startServer() throws IOException{
        try{
            while(!serverSocket.isClosed()){
                Socket socket = serverSocket.accept();
                System.out.println("A new client has connected");
                ClientHandler clienthandler = new ClientHandler(socket);
                
                Thread thread = new Thread (clienthandler);
                thread.start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }      
    }
    
    public void closeServerSocket(){
        try {
            if (serverSocket != null){
                serverSocket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main (String [] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1234); //ServerSocket serverSocket = new ServerSocket(port: 1234);
        ServerMain serverMain = new ServerMain(serverSocket);
        serverMain.startServer();
    }
}
