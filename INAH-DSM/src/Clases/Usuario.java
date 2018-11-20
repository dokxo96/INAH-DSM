
package Clases;

public class Usuario {
private static String nombre,clave,tipo,Id;
public Usuario() {   

}
public Usuario(String nombre,String clave,String tipo) {
        this.nombre = nombre;
        this.clave=clave;
        this.tipo=tipo;
        this.Id=Id;
}  


    public String getNombre() {  return nombre;   }
    public void setNombre(String nombre) {   this.nombre = nombre;    }
    public String getClave() {   return clave;    }
    public void setClave(String clave) {  this.clave = clave;   }    
    public String getTipo() {  return tipo;    }
    public void setTipo(String tipo) {    this.tipo = tipo;    }
    public String getId() {  return Id;    }
    public void setId(String Id) {    this.Id = Id;    }
}
