/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JFerreira
 */
@Entity
@Table(name = "sociedades", schema = "servicios")

@NamedQueries({
    @NamedQuery(name = "Sociedades.findAll", query = "SELECT s FROM Sociedades s"),
    @NamedQuery(name = "Sociedades.findById", query = "SELECT s FROM Sociedades s WHERE s.id = :id"),
    @NamedQuery(name = "Sociedades.findByNombre", query = "SELECT s FROM Sociedades s WHERE s.nombre = :nombre")})
public class Sociedades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "nombre")
    private String nombre;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "sociedades")
    private Incidentes incidentes;

    public Sociedades() {
    }

    public Sociedades(Integer id) {
        this.id = id;
    }

    public Sociedades(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Incidentes getIncidentes() {
        return incidentes;
    }

    public void setIncidentes(Incidentes incidentes) {
        this.incidentes = incidentes;
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
        if (!(object instanceof Sociedades)) {
            return false;
        }
        Sociedades other = (Sociedades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Sociedades[ id=" + id + " ]";
    }
    
}
