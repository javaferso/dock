/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheType;

/**
 *
 * @author JFerreira
 */
@Entity
@Cache(type = CacheType.SOFT, size = 64000)
@Table(name = "locales", schema = "supervision")
@NamedQueries({
    @NamedQuery(name = "Locales.findDistinctFormato", query = "SELECT DISTINCT l.formato FROM Locales l"),
    @NamedQuery(name = "Locales.findDistinctLocalesByFormato", query = "SELECT DISTINCT l.local FROM Locales l WHERE l.formato = :formato ORDER BY l.local asc"),
    @NamedQuery(name = "Locales.findDistinctNombreTiendaByLocal", query = "SELECT DISTINCT l.nombreTienda FROM Locales l WHERE l.local = :local"),
    @NamedQuery(name = "Locales.findByLocal", query = "SELECT l.caja FROM Locales l WHERE l.local = :local"),
    @NamedQuery(name = "Locales.findByTicket", query = "SELECT l.tickets FROM Locales l WHERE l.caja = :caja AND l.local = :local"),
    @NamedQuery(name = "Locales.findLocalesporNombreoLocal", query = "SELECT l FROM Locales l WHERE l.local LIKE :query OR LOWER(l.nombreTienda) LIKE LOWER(:query)")
})

public class Locales implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String formato;
    private String local;
    private String caja;
    private String ip;
    @Column(name = "nombre_tienda")
    private String nombreTienda;
    private int tickets;
    @Column(name = "estado_ip")
    private int estadoIp;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "modified")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    public Locales() {
    }

    public Locales(Long id, String formato, String local, String caja, String ip, String nombreTienda, int tickets, int estadoIp, Date createdAt, Date updatedAt, Date modified) {
        this.id = id;
        this.formato = formato;
        this.local = local;
        this.caja = caja;
        this.ip = ip;
        this.nombreTienda = nombreTienda;
        this.tickets = tickets;
        this.estadoIp = estadoIp;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.modified = modified;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
    
    
    public int getEstadoIp() {
        return estadoIp;
    }

    public void setEstadoIp(int estadoIp) {
        this.estadoIp = estadoIp;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
