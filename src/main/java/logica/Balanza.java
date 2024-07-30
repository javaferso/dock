/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

/**
 *
 * @author JFerreira
 */
@Cache(type = CacheType.SOFT, size = 64000)
@Entity
@Table(name = "balanza", schema = "monitor")

@NamedQueries({
    @NamedQuery(name = "Balanza.findAll", query = "SELECT b FROM Balanza b"),
    @NamedQuery(name = "Balanza.findByTiendaNumero", query = "SELECT b FROM Balanza b WHERE b.balanzaPK.tiendaNumero = :tiendaNumero AND b.habilitado = 0"),
    @NamedQuery(name = "Balanza.findById", query = "SELECT b FROM Balanza b WHERE b.balanzaPK.id = :id"),
    @NamedQuery(name = "Balanza.findByDireccionIp", query = "SELECT b FROM Balanza b WHERE b.direccionIp = :direccionIp"),
    @NamedQuery(name = "Balanza.findByHabilitado", query = "SELECT b FROM Balanza b WHERE b.habilitado = :habilitado"),
    @NamedQuery(name = "Balanza.findByFechaActualizacion", query = "SELECT b FROM Balanza b WHERE b.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Balanza.findByHostName", query = "SELECT b FROM Balanza b WHERE b.hostName = :hostName"),
    @NamedQuery(name = "Balanza.findByNombre", query = "SELECT b FROM Balanza b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Balanza.findByPuerto", query = "SELECT b FROM Balanza b WHERE b.puerto = :puerto")})
public class Balanza implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected BalanzaPK balanzaPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "direccion_ip")
    private String direccionIp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private int habilitado;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Size(max = 30)
    @Column(name = "host_name")
    private String hostName;
    @Size(max = 30)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "puerto")
    private Integer puerto;
    
    public Balanza() {
    }

    public Balanza(BalanzaPK balanzaPK) {
        this.balanzaPK = balanzaPK;
    }

    public Balanza(BalanzaPK balanzaPK, String direccionIp, int habilitado) {
        this.balanzaPK = balanzaPK;
        this.direccionIp = direccionIp;
        this.habilitado = habilitado;
    }

    public Balanza(int tiendaNumero, short id) {
        this.balanzaPK = new BalanzaPK(tiendaNumero, id);
    }

    public BalanzaPK getBalanzaPK() {
        return balanzaPK;
    }

    public void setBalanzaPK(BalanzaPK balanzaPK) {
        this.balanzaPK = balanzaPK;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuerto() {
        return puerto;
    }

    public void setPuerto(Integer puerto) {
        this.puerto = puerto;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (balanzaPK != null ? balanzaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Balanza)) {
            return false;
        }
        Balanza other = (Balanza) object;
        if ((this.balanzaPK == null && other.balanzaPK != null) || (this.balanzaPK != null && !this.balanzaPK.equals(other.balanzaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Balanza[ balanzaPK=" + balanzaPK + " ]";
    }
    
}
