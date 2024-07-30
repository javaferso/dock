/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;
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
@Entity
@Cache(type = CacheType.SOFT, size = 64000)
@Table(name = "servidores", schema = "supervision")
@NamedQueries({
    @NamedQuery(name = "Servidores.findAll", query = "SELECT s FROM Servidores s"),
    @NamedQuery(name = "Servidores.findByLocal", query = "SELECT s FROM Servidores s WHERE s.local = :local"),
    @NamedQuery(name = "Servidores.findByNombreTienda", query = "SELECT s FROM Servidores s WHERE s.nombreTienda = :nombreTienda"),
    @NamedQuery(name = "Servidores.findByIpAddress", query = "SELECT s FROM Servidores s WHERE s.ipAddress = :ipAddress"),
    @NamedQuery(name = "Servidores.findByIpEnlace", query = "SELECT s FROM Servidores s WHERE s.ipEnlace = :ipEnlace"),
    @NamedQuery(name = "Servidores.findByDireccion", query = "SELECT s FROM Servidores s WHERE s.direccion = :direccion"),
    @NamedQuery(name = "Servidores.findByCiudad", query = "SELECT s FROM Servidores s WHERE s.ciudad = :ciudad"),
    @NamedQuery(name = "Servidores.findByCreatedAt", query = "SELECT s FROM Servidores s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "Servidores.findByModified", query = "SELECT s FROM Servidores s WHERE s.modified = :modified"),
    @NamedQuery(name = "Servidores.findByFormato", query = "SELECT s FROM Servidores s WHERE s.formato = :formato"),
    @NamedQuery(name = "Servidores.findByIpVirtual", query = "SELECT s FROM Servidores s WHERE s.ipVirtual = :ipVirtual"),
    @NamedQuery(name = "Servidores.findById", query = "SELECT s FROM Servidores s WHERE s.id = :id"),
    @NamedQuery(name = "Servidores.findServidoresPorCriterio", query = "SELECT s FROM Servidores s WHERE s.local LIKE :query OR LOWER(s.nombreTienda) LIKE LOWER(:query)")})


public class Servidores implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "ip_address")
    private String ipAddress;
    @Size(max = 15)
    @Column(name = "ip_enlace")
    private String ipEnlace;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 100)
    @Column(name = "ciudad")
    private String ciudad;
    @Size(max = 255)
    @Column(name = "ip_virtual")
    private String ipVirtual;
    @Size(max = 2147483647)
    @Column(name = "estado_ip")
    private String estadoIp;
    @Size(max = 2147483647)
    @Column(name = "estado_enlace")
    private String estadoEnlace;
    @Column(name = "fleje_electronico")
    private Boolean flejeElectronico;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Id
    @GeneratedValue (strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nombre_tienda")
    private String nombreTienda;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;
    @Column(name = "formato")
    private String formato;
    @Column(name = "local", unique = true)
    private String local;
    @Size(max=50)
    

    public Servidores() {
    }

    public Servidores(Integer id) {
        this.id = id;
    }

    public Servidores(String ipAddress, String ipEnlace, String direccion, String ciudad, String ipVirtual, String estadoIp, String estadoEnlace, Boolean flejeElectronico, Date updatedAt, Integer id, String nombreTienda, Date createdAt, Date modified, String formato, String local) {
        this.ipAddress = ipAddress;
        this.ipEnlace = ipEnlace;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.ipVirtual = ipVirtual;
        this.estadoIp = estadoIp;
        this.estadoEnlace = estadoEnlace;
        this.flejeElectronico = flejeElectronico;
        this.updatedAt = updatedAt;
        this.id = id;
        this.nombreTienda = nombreTienda;
        this.createdAt = createdAt;
        this.modified = modified;
        this.formato = formato;
        this.local = local;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getEstadoIp() {
        return estadoIp;
    }

    public void setEstadoIp(String estadoIp) {
        this.estadoIp = estadoIp;
    }

    public String getEstadoEnlace() {
        return estadoEnlace;
    }

    public void setEstadoEnlace(String estadoEnlace) {
        this.estadoEnlace = estadoEnlace;
    }

    
   
    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getIpEnlace() {
        return ipEnlace;
    }

    public void setIpEnlace(String ipEnlace) {
        this.ipEnlace = ipEnlace;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getIpVirtual() {
        return ipVirtual;
    }

    public void setIpVirtual(String ipVirtual) {
        this.ipVirtual = ipVirtual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servidores)) {
            return false;
        }
        Servidores other = (Servidores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Servidores[ id=" + id + " ]";
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getFlejeElectronico() {
        return flejeElectronico;
    }

    public void setFlejeElectronico(Boolean flejeElectronico) {
        this.flejeElectronico = flejeElectronico;
    }

}
