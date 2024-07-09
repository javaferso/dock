/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JFerreira
 */
@MappedSuperclass
@Table(name = "flejes")

public class Flejes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "numero_tienda")
    private int numeroTienda;
    @Size(max = 150)
    @Column(name = "nombre_tienda")
    private String nombreTienda;
    @Column(name = "flejes_total")
    private Integer flejesTotal;
    @Column(name = "flejes_pendientes")
    private Integer flejesPendientes;
    @Column(name = "flejes_impresos")
    private Integer flejesImpresos;
    @Size(max = 200)
    @Column(name = "gerente_zonal")
    private String gerenteZonal;
    @Size(max = 200)
    @Column(name = "gerente_operaciones")
    private String gerenteOperaciones;
    @Size(max = 200)
    @Column(name = "formato")
    private String formato;

    public Flejes() {
    }

    public Flejes(Integer id) {
        this.id = id;
    }

    public Flejes(Integer id, Date fechaRegistro, int numeroTienda) {
        this.id = id;
        this.fechaRegistro = fechaRegistro;
        this.numeroTienda = numeroTienda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public int getNumeroTienda() {
        return numeroTienda;
    }

    public void setNumeroTienda(int numeroTienda) {
        this.numeroTienda = numeroTienda;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public Integer getFlejesTotal() {
        return flejesTotal;
    }

    public void setFlejesTotal(Integer flejesTotal) {
        this.flejesTotal = flejesTotal;
    }

    public Integer getFlejesPendientes() {
        return flejesPendientes;
    }

    public void setFlejesPendientes(Integer flejesPendientes) {
        this.flejesPendientes = flejesPendientes;
    }

    public Integer getFlejesImpresos() {
        return flejesImpresos;
    }

    public void setFlejesImpresos(Integer flejesImpresos) {
        this.flejesImpresos = flejesImpresos;
    }

    public String getGerenteZonal() {
        return gerenteZonal;
    }

    public void setGerenteZonal(String gerenteZonal) {
        this.gerenteZonal = gerenteZonal;
    }

    public String getGerenteOperaciones() {
        return gerenteOperaciones;
    }

    public void setGerenteOperaciones(String gerenteOperaciones) {
        this.gerenteOperaciones = gerenteOperaciones;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
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
        if (!(object instanceof Flejes)) {
            return false;
        }
        Flejes other = (Flejes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Flejes[ id=" + id + " ]";
    }
    
}
