/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
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
@Table(name = "incidentes", schema = "servicios")

@NamedQueries({
    @NamedQuery(name = "Incidentes.findAll", query = "SELECT i FROM Incidentes i"),
    @NamedQuery(name = "Incidentes.findById", query = "SELECT i FROM Incidentes i WHERE i.id = :id"),
    @NamedQuery(name = "Incidentes.findByTipo", query = "SELECT i FROM Incidentes i WHERE i.tipo = :tipo"),
    @NamedQuery(name = "Incidentes.findByMes", query = "SELECT i FROM Incidentes i WHERE i.mes = :mes"),
    @NamedQuery(name = "Incidentes.findByFormato", query = "SELECT i FROM Incidentes i WHERE i.formato = :formato"),
    @NamedQuery(name = "Incidentes.findByInc", query = "SELECT i FROM Incidentes i WHERE i.inc = :inc"),
    @NamedQuery(name = "Incidentes.findBySap", query = "SELECT i FROM Incidentes i WHERE i.sap = :sap"),
    @NamedQuery(name = "Incidentes.findByTienda", query = "SELECT i FROM Incidentes i WHERE i.tienda = :tienda"),
    @NamedQuery(name = "Incidentes.findByDetalle", query = "SELECT i FROM Incidentes i WHERE i.detalle = :detalle"),
    @NamedQuery(name = "Incidentes.findByMonto", query = "SELECT i FROM Incidentes i WHERE i.monto = :monto"),
    @NamedQuery(name = "Incidentes.findByMoneda", query = "SELECT i FROM Incidentes i WHERE i.moneda = :moneda"),
    @NamedQuery(name = "Incidentes.findByProveedor", query = "SELECT i FROM Incidentes i WHERE i.proveedor = :proveedor"),
    @NamedQuery(name = "Incidentes.findByFAutorizar", query = "SELECT i FROM Incidentes i WHERE i.fAutorizar = :fAutorizar"),
    @NamedQuery(name = "Incidentes.findByOc", query = "SELECT i FROM Incidentes i WHERE i.oc = :oc"),
    @NamedQuery(name = "Incidentes.findByFEnvioProv", query = "SELECT i FROM Incidentes i WHERE i.fEnvioProv = :fEnvioProv"),
    @NamedQuery(name = "Incidentes.findByHes", query = "SELECT i FROM Incidentes i WHERE i.hes = :hes"),
    @NamedQuery(name = "Incidentes.findBySociedad", query = "SELECT i FROM Incidentes i WHERE i.sociedad = :sociedad"),
    @NamedQuery(name = "Incidentes.findByOrdenEstadistica", query = "SELECT i FROM Incidentes i WHERE i.ordenEstadistica = :ordenEstadistica"),
    @NamedQuery(name = "Incidentes.findByTextoBreve", query = "SELECT i FROM Incidentes i WHERE i.textoBreve = :textoBreve"),
    @NamedQuery(name = "Incidentes.findByCotizacion", query = "SELECT i FROM Incidentes i WHERE i.cotizacion = :cotizacion"),
    @NamedQuery(name = "Incidentes.findByActivo", query = "SELECT i FROM Incidentes i WHERE i.activo = :activo"),
    @NamedQuery(name = "Incidentes.findByFechaCreacion", query = "SELECT i FROM Incidentes i WHERE i.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "Incidentes.findByFechaActualizacion", query = "SELECT i FROM Incidentes i WHERE i.fechaActualizacion = :fechaActualizacion"),
    @NamedQuery(name = "Incidentes.findByFechaCierre", query = "SELECT i FROM Incidentes i WHERE i.fechaCierre = :fechaCierre")})
public class Incidentes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tipo")
    private String tipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mes")
    private int mes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "formato")
    private String formato;
    @Size(max = 255)
    @Column(name = "inc")
    private String inc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sap")
    private int sap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "tienda")
    private String tienda;
    @Size(max = 2147483647)
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "monto")
    private BigInteger monto;
    @Size(max = 10)
    @Column(name = "moneda")
    private String moneda;
    @Size(max = 255)
    @Column(name = "proveedor")
    private String proveedor;
    @Column(name = "f_autorizar")
    @Temporal(TemporalType.DATE)
    private Date fAutorizar;
    @Size(max = 255)
    @Column(name = "oc")
    private String oc;
    @Column(name = "f_envio_prov")
    @Temporal(TemporalType.DATE)
    private Date fEnvioProv;
    @Size(max = 255)
    @Column(name = "hes")
    private String hes;
    @Size(max = 255)
    @Column(name = "sociedad")
    private String sociedad;
    @Size(max = 255)
    @Column(name = "orden_estadistica")
    private String ordenEstadistica;
    @Size(max = 2147483647)
    @Column(name = "texto_breve")
    private String textoBreve;
    @Size(max = 255)
    @Column(name = "cotizacion")
    private String cotizacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "activo")
    private boolean activo;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "fecha_actualizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @Column(name = "fecha_cierre")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCierre;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Moneda moneda1;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Proveedores proveedores;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Sociedades sociedades;
    @JoinColumn(name = "id", referencedColumnName = "id", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Tipos tipos;

    public Incidentes() {
    }

    public Incidentes(Integer id) {
        this.id = id;
    }

    public Incidentes(Integer id, String tipo, int mes, String formato, int sap, String tienda, boolean activo) {
        this.id = id;
        this.tipo = tipo;
        this.mes = mes;
        this.formato = formato;
        this.sap = sap;
        this.tienda = tienda;
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getInc() {
        return inc;
    }

    public void setInc(String inc) {
        this.inc = inc;
    }

    public int getSap() {
        return sap;
    }

    public void setSap(int sap) {
        this.sap = sap;
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public BigInteger getMonto() {
        return monto;
    }

    public void setMonto(BigInteger monto) {
        this.monto = monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public Date getFAutorizar() {
        return fAutorizar;
    }

    public void setFAutorizar(Date fAutorizar) {
        this.fAutorizar = fAutorizar;
    }

    public String getOc() {
        return oc;
    }

    public void setOc(String oc) {
        this.oc = oc;
    }

    public Date getFEnvioProv() {
        return fEnvioProv;
    }

    public void setFEnvioProv(Date fEnvioProv) {
        this.fEnvioProv = fEnvioProv;
    }

    public String getHes() {
        return hes;
    }

    public void setHes(String hes) {
        this.hes = hes;
    }

    public String getSociedad() {
        return sociedad;
    }

    public void setSociedad(String sociedad) {
        this.sociedad = sociedad;
    }

    public String getOrdenEstadistica() {
        return ordenEstadistica;
    }

    public void setOrdenEstadistica(String ordenEstadistica) {
        this.ordenEstadistica = ordenEstadistica;
    }

    public String getTextoBreve() {
        return textoBreve;
    }

    public void setTextoBreve(String textoBreve) {
        this.textoBreve = textoBreve;
    }

    public String getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(String cotizacion) {
        this.cotizacion = cotizacion;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Moneda getMoneda1() {
        return moneda1;
    }

    public void setMoneda1(Moneda moneda1) {
        this.moneda1 = moneda1;
    }

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }

    public Sociedades getSociedades() {
        return sociedades;
    }

    public void setSociedades(Sociedades sociedades) {
        this.sociedades = sociedades;
    }

    public Tipos getTipos() {
        return tipos;
    }

    public void setTipos(Tipos tipos) {
        this.tipos = tipos;
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
        if (!(object instanceof Incidentes)) {
            return false;
        }
        Incidentes other = (Incidentes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Incidentes[ id=" + id + " ]";
    }
    
}
