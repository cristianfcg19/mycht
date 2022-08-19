/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mychat.Objects;
import Conexion.Conexion;
import java.util.ArrayList;
import java.sql.Date;//Se utiliza el Date Sql para que la consulta funcione correctamente

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author css124646
 */
public class ChatGrupo implements GestionDatos
{
ArrayList listaUsuarios=new ArrayList<>();
private int id_chat ;   
private String nombre_chat;
private final String grupo="Grupo";
private String tipoGrupo;
public  DefaultTableModel modelo=new DefaultTableModel();

    public ChatGrupo(int id_chat, String nombre_chat) {
        this.id_chat = id_chat;
        this.nombre_chat = nombre_chat;
    }

    public ChatGrupo() {
        
      
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }

    public int getId_chat() {
        return id_chat;
    }

    public void setId_chat(int id_chat) {
        this.id_chat = id_chat;
    }

    public String getNombre_chat() {
        return nombre_chat;
    }

    public void setNombre_chat(String nombre_chat) {
        this.nombre_chat = nombre_chat;
    }

    @Override
    public void eliminarDatos() {
            try {
          PreparedStatement ps =Conexion.getConexion().prepareStatement("DELETE FROM grupos WHERE nombre_chat=?");
          ps.setString(1, nombre_chat);
          ps.execute();
          JOptionPane.showMessageDialog(null, "Se ha eliminado");
        } catch (SQLException e) {
          Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, e);

        }

    }

    @Override
    public void crearDatos()
    {
     try {
        PreparedStatement ps=Conexion.getConexion().prepareStatement("INSERT INTO grupos (nombre_chat,tipochat) VALUES (?,?)");
        ps.setString(1,nombre_chat);
        ps.setString(2, grupo);
        ps.execute();
        JOptionPane.showMessageDialog(null, "Se guardo el grupo");
        } catch (SQLException ex)
        {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, ex);
        }
    }

    @Override
    public void modificarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void actualizarDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mostrarDatos()
    {
        try {
            
        
        PreparedStatement consulta =Conexion.getConexion().prepareStatement("SELECT * FROM grupos");
        ResultSet rs= consulta.executeQuery();
          
                
                modelo.addColumn("ID");
                modelo.addColumn("Nombre Chat");
                modelo.addColumn("TipoChat");
                 
        while(rs!=null&&rs.next())
            {
                this.id_chat=rs.getInt(1);
                this.nombre_chat=rs.getString(2);
                this.tipoGrupo=rs.getString(3);
              
                listaUsuarios.add(0,id_chat);
                listaUsuarios.add(1,nombre_chat);
                listaUsuarios.add(2,tipoGrupo);
           
                modelo.addRow(listaUsuarios.toArray());
                
            }
        } catch (SQLException e) {
             Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, e);
        }        // TODO add your handling code here:
    }
    }
    

