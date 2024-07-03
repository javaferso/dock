/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JFerreira
 */
@Embeddable
public class BalanzaPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "tienda_numero")
    private int tiendaNumero;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private short id;

    public BalanzaPK() {
    }

    public BalanzaPK(int tiendaNumero, short id) {
        this.tiendaNumero = tiendaNumero;
        this.id = id;
    }

    public int getTiendaNumero() {
        return tiendaNumero;
    }

    public void setTiendaNumero(int tiendaNumero) {
        this.tiendaNumero = tiendaNumero;
    }

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) tiendaNumero;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BalanzaPK)) {
            return false;
        }
        BalanzaPK other = (BalanzaPK) object;
        if (this.tiendaNumero != other.tiendaNumero) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.BalanzaPK[ tiendaNumero=" + tiendaNumero + ", id=" + id + " ]";
    }
    
}
