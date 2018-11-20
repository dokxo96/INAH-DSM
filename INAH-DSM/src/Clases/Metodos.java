package Clases;
//import Datos.*;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.Clip;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Oliveros.Carlos
 */
public class Metodos {
//Acciones a = new Acciones();
//Usuario u = new Usuario();
private Connection conexion = null;
private Statement comando = null;    
private ResultSet resultados = null;
String time,date;
public Metodos()
{}
public void iniciarConexion() throws Exception{
        String user="postgres";
        String pass = "robo";
        try{
            Class.forName( "com.mysql.jdbc.Driver"); 
            
            conexion = DriverManager.getConnection("jdbc:postgresql://localhost/SCN",user,pass);
            System.out.println("conectado");
        }catch(Exception e)
        {
            System.err.println("Error al inciar la conexion a base de datos\t\t");            
            e.printStackTrace();            
            throw e;
        }
    }
public ResultSet select(String consulta) throws SQLException, Exception{
        /*
    Realiza una consulta y retorna el resultado(resultset)
    
    */
        try {
            iniciarConexion();
            String instruccion = consulta;
            System.out.println(consulta);
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
            String instruccion = "INSERT INTO"+consulta;
            System.out.println(consulta);
            comando = conexion.createStatement();            
            resultados = comando.executeQuery(instruccion);
        } catch (SQLException e) {            
            e.printStackTrace();            
            throw e;
        }        
    }

public void cerrar() throws SQLException{
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
public ResultSet consultaTabla(String tabla){
    /*realiza una consulta a BD
    parametros 1 = equipo, 2 = equipo_laboratorio, 3 = reactivos
    devuelve un resultset = resultado de la consulta*/
    try {
        iniciarConexion();
        String consulta;
        switch(tabla){
            case "usuarios":
                consulta = "select * from usuarios;";
                comando = conexion.createStatement();            
                resultados = comando.executeQuery(consulta);
                System.out.println(consulta);
                return resultados;
            case "mesa":
/*                consulta = "select nombre_mesa, impresa from mesa where mesero = '"+a.getMesero()+"';";
                comando = conexion.createStatement();            
                resultados = comando.executeQuery(consulta);
                System.out.println(consulta);
                return resultados;*/
            case "mesa2":
                consulta = "select * from mesa ;";
                comando = conexion.createStatement();            
                resultados = comando.executeQuery(consulta);
                System.out.println(consulta);
                return resultados;
            case "B":
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

return null;
}
public String  extraeHora(){
        Calendar fecha = new GregorianCalendar();
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);
        time=hora+":"+minuto+":"+segundo;
        return time;
    }
public int getHora()
{
Calendar fecha = new GregorianCalendar();
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        return hora;
}
public int getMin()
{
Calendar fecha = new GregorianCalendar();
        int minuto = fecha.get(Calendar.MINUTE);
        return minuto;
}
public String extraeFecha(){
        Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        date= año+ "-" + (mes+1) + "-" + dia;
        return date;
    }
public int getAño()
{
Calendar fecha = new GregorianCalendar();
        int año = fecha.get(Calendar.YEAR);
        return año;
}

public int getMes()
{
Calendar fecha = new GregorianCalendar();
        int mes = fecha.get(Calendar.MONTH);
        return mes;
}
public void llenaTabla(JTable tbl) throws SQLException{
    DefaultTableModel tm;
    tm = (DefaultTableModel)tbl.getModel();
        tm.setRowCount(0);
    
        resultados = consultaTabla("usuarios");
        while (resultados.next())
        {
            
            tm.addRow(new Object[]{resultados.getString("nombre_usuario"),resultados.getString("contrasena")});
        }    
}
public int[] getTiempo(String cad,int z){
    //se divide el String del tiempo en dos para poder calcular, se convierte de 24hrs a 12 hrs
    //argumento 1, se calcula el tiempo restante segun la hora del sistema 
String[] partes = cad.split(":");
if(z==1){
    System.out.println("Reservacion");
int horas = Integer.parseInt(partes[0]);
int minutos = Integer.parseInt(partes[1]);
int []t={horas-getHora(),minutos-getMin()-1};
return t;
}
else
    if(z==2)
{
    System.out.println("Pedido");
int horas = Integer.parseInt(partes[0]);
int minutos = Integer.parseInt(partes[1]);
int []t={horas,minutos-1};

       System.out.println(t[0]+" aqui 1");
       
       System.out.println(t[1]+" aqui 2");
return t;
}
return null;
}
public void playSound()
{
try {
            
            // Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File("src\\componentes\\sound.wav")));
            
            // Comienza la reproducción
            sonido.start();
            
        } catch (Exception e) {
            System.out.println("" + e);
        }
}

}