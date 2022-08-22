/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mychat.GUI.FormServer;

/**
 *
 * @author css124646
 */
public class ReceivingMessages extends Thread{
        int puerto;
        FormServer form;
        public ReceivingMessages(int puerto, FormServer formServer) {
        this.puerto = puerto;
        this.form=formServer;
    }
        
        public  void run(){
            try {
                ServerSocket sc=new ServerSocket(puerto);
            } catch (IOException ex) {
                Logger.getLogger(ReceivingMessages.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    
}
