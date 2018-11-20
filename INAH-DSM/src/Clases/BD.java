package Clases;

/**
 *
 * @author Oliveros.Carlos
 */
//import Datos.Acciones;
//import Ventanas_Generales.Login;
//import Datos.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.Clock.system;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 *
 * @author Oliveros.Carlos
 */
public class BD {
//Acciones A = new Acciones();
//Usuario u = new Usuario();
private Connection conexion = null;
private Statement comando = null;    
private ResultSet resultados = null;
protected final String url ="jdbc:sqlserver://localhost:1433;databaseName=BD_INAH"; //Aqui se pone el nombre de el servidor de tu bd y el nombre de la bd

public BD(){}

    public void iniciarConexion(){
                    System.out.println("aqui");
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Driver Registrado: "+url);
            conexion=DriverManager.getConnection(url,"sa","123"); // usuario y contraseña usuario: sa  contraseña:123
            System.out.println("Conexion Establecida");
           comando = conexion.createStatement();
            
        }catch(SQLException e1){
            System.out.println("ERROR SQL:"+ e1.getMessage());
        }
        catch(ClassNotFoundException e2){
            System.out.println("ERROR DRIVER:"+ e2.getMessage());
        }
    }
public ResultSet select(String consulta) throws SQLException, Exception{
    /*
    Realiza una consulta y retorna el resultado(resultset)
    */
        try {
            iniciarConexion();
            String instruccion = consulta;
            //System.out.println(consulta);
            comando = conexion.createStatement();            
            resultados = comando.executeQuery(instruccion);
            
            return resultados;
        } catch (SQLException e) {            
            e.printStackTrace();            
            throw e;
        }        
        
    }
public void insertar(String consulta) throws SQLException, Exception{
        
        try {
            iniciarConexion();
            String instruccion = "INSERT INTO "+consulta;
            //System.out.println(instruccion);
            comando = conexion.createStatement();            
            comando.executeUpdate(instruccion);
        } catch (SQLException e) {            
            e.printStackTrace();            
            throw e;
        }        
        cerrar();
    }
public void eliminar(String consulta) throws SQLException, Exception{
        
        try {
            iniciarConexion();
            String instruccion = "DELETE FROM "+consulta;
            //System.out.println(instruccion);
            comando = conexion.createStatement();            
            comando.executeUpdate(instruccion);
        } catch (SQLException e) {            
            e.printStackTrace();            
            throw e;
        }        
        cerrar();
    }
public void actualizar(String consulta) throws Exception{
        
        try {
            iniciarConexion();
            String instruccion = "UPDATE "+consulta;
            //System.out.println(instruccion);
            comando = conexion.createStatement();            
            comando.executeUpdate(instruccion);
        } catch (SQLException e) {            
            e.printStackTrace();            
            throw e;
        }        
        cerrar();
}
/*public boolean entrar(String usuario,String contrasena) throws Exception{
    //M.ValidarNombre(txtUsuario.getText());    
    //M.validarContra(txtContra.getText());
    String user ="";
    String pass ="";
    String tipo="";
       try {
           System.out.println(u.getNombre());
             if(!u.getNombre().equals("")){
             select("SELECT * FROM usuarios");
                if (resultados != null) {
                    while (resultados.next()) {
                        user = resultados.getString("nombre_usuario");
                        pass = resultados.getString("contrasena");
                        if(usuario.equals(user)&&contrasena.equals(pass)){
                            System.out.println("usuario correcto");
                            tipo=resultados.getString("tipo");
                            System.out.println(tipo);
                            switch(tipo){
            case "ADMINISTRADOR": new Ventanas.Gerente.VP_Administrador().setVisible(true);
            break;
            case "CAJERO": new Ventanas.Cajero.M_P_Cajero().setVisible(true);
            break;
            case "MESERO": A.setIDMesero(resultados.getInt(1));
                new Ventanas.Mesero.M_P_Mesero().setVisible(true);
            break;
                            }//switch
                            return true;
                         }
                        else
                        conexion.close();   
                        }//while
                    
                    return false;
                }//if resultados != null
                else
                {
                showMessageDialog(null,"NO HAY USUARIOS REGISTRADOS");
                
                }
                }//if getAlumno
                
               
            
        } catch (SQLException ex) {
            showMessageDialog(null,ex.getMessage());
        }
       comando.close();
        return false;
    }*/
public void cerrar() throws SQLException{
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
public ResultSet consultaTabla(int t) throws SQLException{
    /*realiza una consulta a BD
    parametros 1 = equipo, 2 = equipo_laboratorio, 3 = reactivos
    devuelve un resultset = resultado de la consulta*/
    try {
        iniciarConexion();
        String consulta;
        switch(t){
            case 1:
                consulta = "select * from equipo;";
                comando = conexion.createStatement();            
                resultados = comando.executeQuery(consulta);
                System.out.println(consulta);
                return resultados;
            case 2:
                consulta = "select * from equipo_laboratorio;";
                comando = conexion.createStatement();            
                resultados = comando.executeQuery(consulta);
                System.out.println(consulta);
                return resultados;
            case 3:
                consulta = "select * from reactivos;";
                comando = conexion.createStatement();            
                resultados = comando.executeQuery(consulta);
                System.out.println(consulta);
                return resultados;
        }
        //System.out.println(cadena);
        
    } catch (Exception ex) {
        Logger.getLogger(Metodos.class.getName()).log(Level.SEVERE, null, ex);
    }
cerrar();
return null;
}
public String getTexto(String cad,int c) throws Exception{
ResultSet s = select(cad);
    try {
        while(s.next())
        {
            System.out.println(s.getString(c));
            
            return s.getString(c);
            
        }
    } catch (Exception ex) {
        Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}
public int getEntero(String cad,int c) throws Exception{
ResultSet s = select(cad);
    try {
        while(s.next())
        {
            System.out.println(s.getInt(c));
            conexion.close();
            return s.getInt(c);
        }
    } catch (Exception ex) {
        Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
    }
    conexion.close();
    return 0;
}

}


