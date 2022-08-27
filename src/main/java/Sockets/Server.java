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
public class Server extends Thread {

    private FormServer form;
    DataOutputStream salida;
    DataInputStream entrada;
    String mensajeAEnviarServer = "";
    String mensajeRecibido = "";
    Socket server = new Socket();
    String[] args;
    int puerto;
    ServerSocket serverSocket;
    ArrayList<String> arraylistTerrorifico = new ArrayList<String>();
    String datosQueSevanADesplegarEnChat = "";

    public Server(int puerto, FormServer formServer) {
        this.puerto = puerto;
        this.form = formServer;
    }

    public String getMensajeAEnviar() {
        return mensajeAEnviarServer;
    }

    public void setMensajeAEnviar(String msn) {
        this.mensajeAEnviarServer = msn;
    }
    
    public void hacerDelay(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    private void  agregarDatosAChatDisplaySend(String mensaje){
    System.out.println("entra a agregar DATOS CHAT!!!");
    System.out.println("el mensaje SALE del chat es +"+mensaje+"+");
    form.JTextAreaaAreaTextoChatDisplay.setText(form.JTextAreaaAreaTextoChatDisplay.getText()+"local-> :"+mensaje+"\n");
    mensajeAEnviarServer="";
}
        private void  agregarDatosAChatDisplayReceive(String mensaje){
    System.out.println("ENTRA DATOS al chat");
    System.out.println("el mensaje ENTRA chat es +"+mensaje+"+");
    form.JTextAreaaAreaTextoChatDisplay.setText(form.JTextAreaaAreaTextoChatDisplay.getText()+"remote-> :"+mensaje+"\n");
    mensajeAEnviarServer="";
}
    
    public void run() {

        try {

            serverSocket = new ServerSocket(puerto);

            System.out.println("Esperando conexión...");
            form.informacion.setText("Esperando conexión...");
            server = serverSocket.accept();

            if (server.isConnected()) {

                System.out.println("si se conecta");
                form.informacion.setText("Se conecto si hay un usuario conectado");
                entrada = new DataInputStream(server.getInputStream());//esta linea se va a ir
                salida = new DataOutputStream(server.getOutputStream());
                datosQueSevanADesplegarEnChat = "";

                while (!mensajeAEnviarServer.equals("Exit")) {
                    
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    System.out.println("!!!!!!!!");
                    System.out.println("inicio del while para enviar datos");
                    /*
                esto es de lectura se puede borrar fijarse en linea 80 y 88
                try {
                    Thread.sleep(25);
                    System.out.println("agrega el delay de 25ms");
                } catch (InterruptedException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("aplica el catch");
                }*/

                    
                    mensajeRecibido = entrada.readUTF();//esto psiblemente se quite 


                    /*
                
                
                if (!mensajeRecibido.equals("")){
                    System.out.println("entra al if de !mensajeRecibido.equals(\"\" )");
                    datosQueSevanADesplegarEnChat=datosQueSevanADesplegarEnChat+ mensajeRecibido+"\n";
                    form.areaTextoChatDisplay.setText(datosQueSevanADesplegarEnChat);
                    //arraylistTerrorifico.add(mensajeRecibido);
                    //for(int i = 0; i<arraylistTerrorifico.size(); i++){
                    mensajeRecibido="";
                }
                     */
                    //if(!mensajeRecibido.equals("")){
                    //form.Chat.setText(mensajeRecibido);
                    //}
                    

                    form.informacion.setText("Escriba un msn para enviar");//label 
                    salida.writeUTF(mensajeAEnviarServer);//label 
                    

                    System.out.println("!mensajeAEnviarServer.equals(): " + !mensajeAEnviarServer.equals(""));

                    if (!mensajeAEnviarServer.equals("")) {
                        salida.writeUTF(mensajeAEnviarServer);
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("Server mensaje enviado: +" + mensajeAEnviarServer+"+");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        
                        agregarDatosAChatDisplaySend(mensajeAEnviarServer);
                        salida.writeUTF("");
                        mensajeAEnviarServer="";
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                        System.out.println("Server mensaje enviado: +" + mensajeAEnviarServer+"+");
                        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    }
                    //enviamos mensaje

                }
                
                JOptionPane.showMessageDialog(null, "Ha cerrado la comunicacion");
                serverSocket.close();
                form.dispose();
                server.shutdownInput();
                server.shutdownOutput();
                server.close();

            }
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
