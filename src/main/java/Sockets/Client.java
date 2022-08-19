/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author css124646
 */
public class Client {
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket, String username) {
        
        try{
            this.socket = socket;
            this.username = username;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
        
    }
    
    public void sendMessage(){
        try{
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            while(socket.isConnected()){
                String mensaje=JOptionPane.showInputDialog("Introduzca su mensaje aca:");//ir al minuto 32 del video y usar scan in read line 
                bufferedWriter.write(username + ":"+ mensaje);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                
            }
        }catch(IOException e){
            closeEverything(socket, bufferedReader, bufferedWriter);    
        }
        
    }
    
    public void listenForMessage(){
        new Thread(new Runnable(){
            
            public void run(){
                String messageFromGroupChat;
                
                while (socket.isConnected()){
                    try {
                        messageFromGroupChat=bufferedReader.readLine();
                        System.out.println(messageFromGroupChat);
                        
                    } catch (IOException e) {
                        closeEverything(socket, bufferedReader, bufferedWriter);   
                    }
                }
            }
            
        }).start();
    }
    
    public void closeEverything(Socket socket, BufferedReader bufferedreader, BufferedWriter bufferedWriter){
        
        try{
            if (bufferedreader!= null){
                bufferedreader.close();
            }
            if (bufferedWriter!= null){
                bufferedWriter.close();
            }
            if(socket!= null){
                socket.close();
            }
      
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main (String[] args) throws IOException{
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username in the chat");
        String username=JOptionPane.showInputDialog("Digite su nombre de usuario");
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username);
        client.listenForMessage();
        client.sendMessage();
    }

}
