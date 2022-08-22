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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    ArrayList<String> arraylistTerrorifico = new ArrayList<String>();

    
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
        String datosQueSevanADesplegarEnChat= "";
        while(!msn.equals("Exit")){
             try {
                 Thread.sleep(100);
             } catch (InterruptedException ex) {
                 Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
             }
            
            mensajeRecibido = entrada.readUTF();//Leemos respuesta
            if (!mensajeRecibido.equals("")){
                datosQueSevanADesplegarEnChat=datosQueSevanADesplegarEnChat+ mensajeRecibido+"\n";
                form.areaTextoChat.setText(datosQueSevanADesplegarEnChat);
                //arraylistTerrorifico.add(mensajeRecibido);
                //for(int i = 0; i<arraylistTerrorifico.size(); i++){
                    
                //}
            }
            
            //if(!mensajeRecibido.equals("")){
                //form.Chat.setText(mensajeRecibido);
            //}
            System.out.println("Server mensaje recibido:"+mensajeRecibido);
            mensajeRecibido="";
            System.out.println("Server mensaje enviado:"+salida);
            mensajeRecibido="";
            
            
            form.informacion.setText("Escriba un msn para enviar");
            if(!form.msg.equals("")|| msn.equals("")){
                salida.writeUTF(msn);
                msn="";
            }
            //enviamos mensaje
            
            
        }
        JOptionPane.showMessageDialog(null, "Ha cerrado la comunicacion");
        sc.close();
        form.dispose();
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

