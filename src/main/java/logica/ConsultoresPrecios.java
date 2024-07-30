/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JFerreira
 */
@Entity
@Table(name = "consultores_precios", schema = "supervision")
@NamedQueries({
    @NamedQuery(name = "ConsultoresPrecios.findAll", query = "SELECT c FROM ConsultoresPrecios c"),
    @NamedQuery(name = "ConsultoresPrecios.findById", query = "SELECT c FROM ConsultoresPrecios c WHERE c.id = :id"),
    @NamedQuery(name = "ConsultoresPrecios.findBySapName", query = "SELECT c FROM ConsultoresPrecios c WHERE c.sapName = :sapName"),
    @NamedQuery(name = "ConsultoresPrecios.findByIp", query = "SELECT c FROM ConsultoresPrecios c WHERE c.ip = :ip"),
    @NamedQuery(name = "ConsultoresPrecios.findByLocalNumber", query = "SELECT c FROM ConsultoresPrecios c WHERE c.localNumber = :localNumber"),
    @NamedQuery(name = "ConsultoresPrecios.findByCreatedAt", query = "SELECT c FROM ConsultoresPrecios c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "ConsultoresPrecios.findByUpdatedAt", query = "SELECT c FROM ConsultoresPrecios c WHERE c.updatedAt = :updatedAt"),
    @NamedQuery(name = "ConsultoresPrecios.findByFormato", query = "SELECT c FROM ConsultoresPrecios c WHERE c.formato = :formato"),
    @NamedQuery(name = "ConsultoresPrecios.findByFormatoCodigo", query = "SELECT c FROM ConsultoresPrecios c WHERE c.formatoCodigo = :formatoCodigo"),
    @NamedQuery(name = "ConsultoresPrecios.findByNumber", query = "SELECT c FROM ConsultoresPrecios c WHERE c.number = :number"),
    @NamedQuery(name = "ConsultoresPrecios.findByHostname", query = "SELECT c FROM ConsultoresPrecios c WHERE c.hostname = :hostname"),
    @NamedQuery(name = "ConsultoresPrecios.findByCpNumber", query = "SELECT c FROM ConsultoresPrecios c WHERE c.cpNumber = :cpNumber")})
public class ConsultoresPrecios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "sap_name")
    private String sapName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "ip")
    private String ip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "local_number")
    private String localNumber;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Size(max = 20)
    @Column(name = "formato")
    private String formato;
    @Size(max = 20)
    @Column(name = "formato_codigo")
    private String formatoCodigo;
    @Column(name = "number")
    private Integer number;
    @Size(max = 22)
    @Column(name = "hostname")
    private String hostname;
    @Column(name = "cp_number")
    private Integer cpNumber;

    public ConsultoresPrecios() {
    }

    public ConsultoresPrecios(Integer id) {
        this.id = id;
    }

    public ConsultoresPrecios(Integer id, String ip, String localNumber) {
        this.id = id;
        this.ip = ip;
        this.localNumber = localNumber;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSapName() {
        return sapName;
    }

    public void setSapName(String sapName) {
        this.sapName = sapName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLocalNumber() {
        return localNumber;
    }

    public void setLocalNumber(String localNumber) {
        this.localNumber = localNumber;
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

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getFormatoCodigo() {
        return formatoCodigo;
    }

    public void setFormatoCodigo(String formatoCodigo) {
        this.formatoCodigo = formatoCodigo;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getCpNumber() {
        return cpNumber;
    }

    public void setCpNumber(Integer cpNumber) {
        this.cpNumber = cpNumber;
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
        if (!(object instanceof ConsultoresPrecios)) {
            return false;
        }
        ConsultoresPrecios other = (ConsultoresPrecios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.ConsultoresPrecios[ id=" + id + " ]";
    }
    
}
