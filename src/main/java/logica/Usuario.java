
package logica;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios", schema = "supervision")
public class Usuario implements Serializable {

    @Id
    @Column(name = "id_usuario")
    private String idUsuario;
    private String password;
    private String nombre;
    private String apellido;
    private String sexo;
    @Column(name = "id_role")
    private int idRole;
    private String habilitado;
    private byte[] imagen;

    
   
    
    
    public Usuario () {
        
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    
    public Usuario(String idUsuario, String password, String nombre, String apellido, String sexo, int idRole, String habilitado, byte[] imagen) {
        
        this.idUsuario = idUsuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.idRole = idRole;
        this.habilitado = habilitado;
        this.imagen = imagen;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }


    public String isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

}