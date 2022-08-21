/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;
import mychat.GUI.FormServer;
/**
 *
 * @author Christian
 */
public class Server    extends Thread
{
    private  FormServer form ; 
    DataOutputStream salida;
    DataInputStream entrada;
    String msn = "";
    String mensajeRecibido;
    Socket server =new Socket();
    String[] args;
    int puerto;

    
    public Server(int puerto, FormServer formServer) {
        this.puerto = puerto;
        this.form=formServer;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }


    @Override
    public  void run()
    {

      
     
      try {
        
        
        ServerSocket sc=new ServerSocket(puerto);
        
        
        System.out.println("Esperando conexión...");
        form.informacion.setText("Esperando conexión...");
        server=sc.accept();
        if(server.isConnected()){
      

         form.informacion.setText("Se conecto uno...");
        entrada = new DataInputStream(server.getInputStream());
        salida = new DataOutputStream(server.getOutputStream());

        while(!msn.equals("Exit")){
                
            mensajeRecibido = entrada.readUTF();//Leemos respuesta
          
            form.Chat.setText(mensajeRecibido);
            
            form.informacion.setText("Escriba un msn para enviar");
            
            salida.writeUTF("Server:"+msn);//enviamos mensaje
            
        }
        sc.close();
        server.shutdownInput();
        server.shutdownOutput();
     //hilos hilo =new hilos(puerto);
     //hilo.run();  
    
    }
      } catch (IOException e) {
        System.out.println(e);
      }

    
    
}
}

