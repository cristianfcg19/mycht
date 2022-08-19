/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mychat.Objects;

import java.util.ArrayList;
import Conexion.Conexion;
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
public class Administrador extends Persona {
    
    final String SELECT_TODOS_LOS_USUARIOS="Select * from mostrarUsuarios";
    ArrayList listaUsuarios=new ArrayList<>();
    ArrayList<Administrador> listaAmigos = new ArrayList<Administrador>();
    Direcciónred direcciónred =new Direcciónred();
    private String ip ;
    private String tipored; 
    private String tipoRol;//este espara imprimir en la tabla
    private int rol;//esta para añadir registro
    private String contrasenna;
    public  DefaultTableModel modelo=new DefaultTableModel();
    
    public Administrador() {
    }
    

    public ArrayList<Administrador> getListaAmigos() {
        return listaAmigos;
    }

    public void setListaAmigos(ArrayList<Administrador> listaAmigos) {
        this.listaAmigos = listaAmigos;
    }

  

    public String getTipored() {
        return tipored;
    }

    public void setTipored(String tipored) {
        this.tipored = tipored;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }
    

    
    @Override
    public void eliminarDatos() {
       
            try{
            
            PreparedStatement consulta= Conexion.getConexion().prepareStatement("DELETE FROM usuarios WHERE idusuario=?;");
            consulta.setInt(1, id);
            consulta.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha eliminado usuario");
            
                
            
            
        }catch (SQLException ex){
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, ex);
        }
        
          
        
            
        }
    

    @Override
    public void crearDatos() {
           try{
//            DateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
//            this.nombrePersona=JOptionPane.showInputDialog("Digite el nombre");
//            this.apellido1=JOptionPane.showInputDialog("Digite el primer apellido");
//            this.apellido2=JOptionPane.showInputDialog("Digite el segundo apellido");
//            this.fechaNacimiento=Date.valueOf(JOptionPane.showInputDialog("Digite la fecha de nacimiento del estudiante"));
//            this.email=JOptionPane.showInputDialog("Digite el email");
            int ipsiguien=contarIdSiguiente();
            JOptionPane.showMessageDialog(null, ipsiguien);
            PreparedStatement ps=Conexion.getConexion().prepareStatement("INSERT INTO usuarios(nombreusuarios,primerApellido,SegundoApellido,Email,contraseña,FechaNacimiento,ipv4)VALUES (?,?,?,?,?,?,?)");
            PreparedStatement consutaRoles=Conexion.getConexion().prepareStatement("INSERT INTO rol_usuario values(?,?)");
            ps.setString(1, nombrePersona);
            ps.setString(2, apellido1);
            ps.setString(3, apellido2);
            ps.setString(4, email);
            ps.setString(5, contrasenna);
            ps.setDate(6, fechaNacimiento);
            ps.setString(7, ip);
            consutaRoles.setInt(1,ipsiguien);
            consutaRoles.setInt(2, rol);
            ps.execute();
            consutaRoles.execute();
            JOptionPane.showMessageDialog(null,"Se ha guardado el usuario");
                
            }
            catch (SQLException exception)
            {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, exception);
        }
    }
    public int contarIdSiguiente()
    {int siguienteid;
        try {
            
        
        PreparedStatement consulta =Conexion.getConexion().prepareStatement("Select c.idusuario from mostrarusuarios as c");
        ResultSet rs= consulta.executeQuery();
          
       
        while(rs!=null&&rs.next())
            {
                
                this.id=rs.getInt(1);   
               
            }
        } catch (SQLException e) {
             Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, e);
        } 
        return  siguienteid=id+1;
    }
    @Override
    public void modificarDatos() {
      try{
          
             
            PreparedStatement ps=Conexion.getConexion().prepareStatement("UPDATE usuarios\n" +
"SET nombreusuarios=?,primerApellido=?,SegundoApellido=?,Email=?,contraseña=?,FechaNacimiento=? ,ipv4=? WHERE idusuario=?");
           PreparedStatement consutaRoles=Conexion.getConexion().prepareStatement("UPDATE rol_usuario\n" +
"SET  idrole=?;");
            ps.setString(1, nombrePersona);
            ps.setString(2, apellido1);
            ps.setString(3, apellido2);
            ps.setString(4, email);
            ps.setString(5, contrasenna);
            ps.setDate(6, fechaNacimiento);
            ps.setString(7, ip);
            ps.setInt(8, id);
            consutaRoles.setInt(1, rol);
            ps.executeUpdate();
            consutaRoles.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha modificado el Usuario");
        }
         catch (SQLException e) {
             Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, e);
        } 
      
      
    }

    @Override
    public void actualizarDatos() 
    {
        try{
          
             
            PreparedStatement ps=Conexion.getConexion().prepareStatement("UPDATE usuarios\n" +
"SET nombreusuarios=?,primerApellido=?,SegundoApellido=?,Email=?,contraseña=?,FechaNacimiento=? ,ipv4=? WHERE idusuario=?");
           PreparedStatement consutaRoles=Conexion.getConexion().prepareStatement("UPDATE rol_usuario\n" +
"SET  idrole=?;");
            ps.setString(1, nombrePersona);
            ps.setString(2, apellido1);
            ps.setString(3, apellido2);
            ps.setString(4, email);
            ps.setString(5, contrasenna);
            ps.setDate(6, fechaNacimiento);
            ps.setString(7, ip);
            ps.setInt(8, id);
            consutaRoles.setInt(1, rol);
            ps.executeUpdate();
            consutaRoles.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se ha actualizado el Usuario");
        }
         catch (SQLException e) {
             Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, e);
        } 
    }
    @Override
    public void  mostrarDatos()
    {try {
            
        
        PreparedStatement consulta =Conexion.getConexion().prepareStatement(SELECT_TODOS_LOS_USUARIOS);
        ResultSet rs= consulta.executeQuery();
          
                
                modelo.addColumn("ID");
                modelo.addColumn("Nombre");
                modelo.addColumn("Primer apellido");
                modelo.addColumn("Segundo Apellido");
                modelo.addColumn("Email");
                modelo.addColumn("Contraseña");
                modelo.addColumn("Fecha Nacimiento");
                modelo.addColumn("Direccion ip");
                modelo.addColumn("Roles");    
        while(rs!=null&&rs.next())
            {
                this.id=rs.getInt(1);
                this.nombrePersona=rs.getString(2);
                this.apellido1=rs.getString(3);
                this.apellido2=rs.getString(4);
                this.email=rs.getString(5);
                this.contrasenna=rs.getString(6);
                this.fechaNacimiento=rs.getDate(7);
                this.direcciónred.setIpv4(rs.getString(8));
                this.tipoRol=rs.getString(9);
                listaUsuarios.add(0,id);
                listaUsuarios.add(1,nombrePersona);
                listaUsuarios.add(2,apellido1);
                listaUsuarios.add(3,apellido2);
                listaUsuarios.add(4,email);
                listaUsuarios.add(5,contrasenna);
                listaUsuarios.add(6,fechaNacimiento);
                listaUsuarios.add(7,direcciónred.getIpv4());
                listaUsuarios.add(8,tipoRol);
                modelo.addRow(listaUsuarios.toArray());
                
            }
        } catch (SQLException e) {
             Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, e);
        }        // TODO add your handling code here:
    }
 public  void BuscarInventario()
    {
        try {
            
            
            
            PreparedStatement consulta =Conexion.getConexion().prepareStatement("Select * from mostrarUsuarios where idusuario in(?)");
            consulta.setInt(1,id );
            ResultSet rs= consulta.executeQuery();   
            
        while(rs!=null&&rs.next()){    
                this.id=rs.getInt(1);
                this.nombrePersona=rs.getString(2);
                this.apellido1=rs.getString(3);
                this.apellido2=rs.getString(4);
                this.email=rs.getString(5);
                this.contrasenna=rs.getString(6);
                this.fechaNacimiento=rs.getDate(7);
                this.ip=rs.getString(8);
                this.tipoRol=rs.getString(9);
                JOptionPane.showMessageDialog(null, "Se encontro el Usuario");
          
        }
   
            
            }
            catch (SQLException exception)
            {
            Logger.getLogger(Administrador.class.getName()).log(Level.SEVERE,null, exception);
        }   
    } 
    
}
