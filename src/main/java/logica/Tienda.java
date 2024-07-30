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
@Table(name = "tienda")

@NamedQueries({
    @NamedQuery(name = "Tienda.findAll", query = "SELECT t FROM Tienda t"),
    @NamedQuery(name = "Tienda.findByFormatoCodigo", query = "SELECT t FROM Tienda t WHERE t.formatoCodigo = :formatoCodigo"),
    @NamedQuery(name = "Tienda.findByNumero", query = "SELECT t FROM Tienda t WHERE t.numero = :numero"),
    @NamedQuery(name = "Tienda.findByNombre", query = "SELECT t FROM Tienda t WHERE t.nombre = :nombre"),
    @NamedQuery(name = "Tienda.findByDireccion", query = "SELECT t FROM Tienda t WHERE t.direccion = :direccion"),
    @NamedQuery(name = "Tienda.findByComuna", query = "SELECT t FROM Tienda t WHERE t.comuna = :comuna"),
    @NamedQuery(name = "Tienda.findByProvincia", query = "SELECT t FROM Tienda t WHERE t.provincia = :provincia"),
    @NamedQuery(name = "Tienda.findByRegion", query = "SELECT t FROM Tienda t WHERE t.region = :region"),
    @NamedQuery(name = "Tienda.findByDireccionIpVirtual", query = "SELECT t FROM Tienda t WHERE t.direccionIpVirtual = :direccionIpVirtual"),
    @NamedQuery(name = "Tienda.findByDireccionIp", query = "SELECT t FROM Tienda t WHERE t.direccionIp = :direccionIp"),
    @NamedQuery(name = "Tienda.findByInstanciaInformix", query = "SELECT t FROM Tienda t WHERE t.instanciaInformix = :instanciaInformix"),
    @NamedQuery(name = "Tienda.findByEstado", query = "SELECT t FROM Tienda t WHERE t.estado = :estado"),
    @NamedQuery(name = "Tienda.findByHabilitado", query = "SELECT t FROM Tienda t WHERE t.habilitado = :habilitado"),
    @NamedQuery(name = "Tienda.findByFechaActualizacion", query = "SELECT t FROM Tienda t WHERE t.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Tienda.findByHostName", query = "SELECT t FROM Tienda t WHERE t.hostName = :hostName"),
    @NamedQuery(name = "Tienda.findByComunaNumero", query = "SELECT t FROM Tienda t WHERE t.comunaNumero = :comunaNumero"),
    @NamedQuery(name = "Tienda.findByRegionNumero", query = "SELECT t FROM Tienda t WHERE t.regionNumero = :regionNumero"),
    @NamedQuery(name = "Tienda.findBySdwanHabilitado", query = "SELECT t FROM Tienda t WHERE t.sdwanHabilitado = :sdwanHabilitado"),
    @NamedQuery(name = "Tienda.findByHabilitadoReporteFlejes", query = "SELECT t FROM Tienda t WHERE t.habilitadoReporteFlejes = :habilitadoReporteFlejes"),
    @NamedQuery(name = "Tienda.findByAbiertoDiasDomingo", query = "SELECT t FROM Tienda t WHERE t.abiertoDiasDomingo = :abiertoDiasDomingo"),
    @NamedQuery(name = "Tienda.findTiendaPorCriterio", query = "SELECT t FROM Tienda t WHERE t.numero LIKE :query OR LOWER(t.nombre) LIKE LOWER(:query)")})
public class Tienda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 3)
    @Column(name = "formato_codigo")
    private String formatoCodigo;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero")
    private String numero;
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 255)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 255)
    @Column(name = "comuna")
    private String comuna;
    @Size(max = 255)
    @Column(name = "provincia")
    private String provincia;
    @Size(max = 255)
    @Column(name = "region")
    private String region;
    @Size(max = 15)
    @Column(name = "direccion_ip_virtual")
    private String direccionIpVirtual;
    @Size(max = 15)
    @Column(name = "direccion_ip")
    private String direccionIp;
    @Size(max = 255)
    @Column(name = "instancia_informix")
    private String instanciaInformix;
    @Column(name = "estado")
    private Short estado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "habilitado")
    private int habilitado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Size(max = 30)
    @Column(name = "host_name")
    private String hostName;
    @Column(name = "comuna_numero")
    private Integer comunaNumero;
    @Column(name = "region_numero")
    private Integer regionNumero;
    @Column(name = "sdwan_habilitado")
    private Short sdwanHabilitado;
    @Column(name = "habilitado_reporte_flejes")
    private Boolean habilitadoReporteFlejes;
    @Column(name = "abierto_dias_domingo")
    private Boolean abiertoDiasDomingo;

    public Tienda() {
    }

    public Tienda(String numero) {
        this.numero = numero;
    }

    public Tienda(String numero, int habilitado, Date fechaActualizacion) {
        this.numero = numero;
        this.habilitado = habilitado;
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getFormatoCodigo() {
        return formatoCodigo;
    }

    public void setFormatoCodigo(String formatoCodigo) {
        this.formatoCodigo = formatoCodigo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDireccionIpVirtual() {
        return direccionIpVirtual;
    }

    public void setDireccionIpVirtual(String direccionIpVirtual) {
        this.direccionIpVirtual = direccionIpVirtual;
    }

    public String getDireccionIp() {
        return direccionIp;
    }

    public void setDireccionIp(String direccionIp) {
        this.direccionIp = direccionIp;
    }

    public String getInstanciaInformix() {
        return instanciaInformix;
    }

    public void setInstanciaInformix(String instanciaInformix) {
        this.instanciaInformix = instanciaInformix;
    }

    public Short getEstado() {
        return estado;
    }

    public void setEstado(Short estado) {
        this.estado = estado;
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

    public Integer getComunaNumero() {
        return comunaNumero;
    }

    public void setComunaNumero(Integer comunaNumero) {
        this.comunaNumero = comunaNumero;
    }

    public Integer getRegionNumero() {
        return regionNumero;
    }

    public void setRegionNumero(Integer regionNumero) {
        this.regionNumero = regionNumero;
    }

    public Short getSdwanHabilitado() {
        return sdwanHabilitado;
    }

    public void setSdwanHabilitado(Short sdwanHabilitado) {
        this.sdwanHabilitado = sdwanHabilitado;
    }

    public Boolean getHabilitadoReporteFlejes() {
        return habilitadoReporteFlejes;
    }

    public void setHabilitadoReporteFlejes(Boolean habilitadoReporteFlejes) {
        this.habilitadoReporteFlejes = habilitadoReporteFlejes;
    }

    public Boolean getAbiertoDiasDomingo() {
        return abiertoDiasDomingo;
    }

    public void setAbiertoDiasDomingo(Boolean abiertoDiasDomingo) {
        this.abiertoDiasDomingo = abiertoDiasDomingo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tienda)) {
            return false;
        }
        Tienda other = (Tienda) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Tienda[ numero=" + numero + " ]";
    }
    
}
