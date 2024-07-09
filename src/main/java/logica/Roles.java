/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JFerreira
 */
@Entity
@Table(name = "roles", schema = "supervision")
@NamedQueries({
    @NamedQuery(name = "Roles.findAll", query = "SELECT r FROM Roles r"),
    @NamedQuery(name = "Roles.findByIdRole", query = "SELECT r FROM Roles r WHERE r.idRole = :idRole"),
    @NamedQuery(name = "Roles.findByNameRole", query = "SELECT r FROM Roles r WHERE r.nameRole = :nameRole"),
    @NamedQuery(name = "Roles.findByDescriptionRole", query = "SELECT r FROM Roles r WHERE r.descriptionRole = :descriptionRole"),
    @NamedQuery(name = "Roles.findByEliminatedRole", query = "SELECT r FROM Roles r WHERE r.eliminatedRole = :eliminatedRole")
})
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_role")
    private Integer idRole;

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "name_role")
    private String nameRole;

    @Size(max = 2147483647)
    @Column(name = "description_role")
    private String descriptionRole;

    @Column(name = "eliminated_role")
    private Boolean eliminatedRole;

    @JoinTable(name = "usuario_rol", joinColumns = {
        @JoinColumn(name = "id_role", referencedColumnName = "id_role")}, inverseJoinColumns = {
        @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")})
    @ManyToMany
    private Collection<Usuario> usuariosCollection;

    @OneToMany(mappedBy = "idRole")
    private Collection<Usuario> usuariosCollection1;

    public Roles() {
    }

    public Roles(Integer idRole) {
        this.idRole = idRole;
    }

    public Roles(Integer idRole, String nameRole) {
        this.idRole = idRole;
        this.nameRole = nameRole;
    }

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getDescriptionRole() {
        return descriptionRole;
    }

    public void setDescriptionRole(String descriptionRole) {
        this.descriptionRole = descriptionRole;
    }

    public Boolean getEliminatedRole() {
        return eliminatedRole;
    }

    public void setEliminatedRole(Boolean eliminatedRole) {
        this.eliminatedRole = eliminatedRole;
    }

    public Collection<Usuario> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuario> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
    }

    public Collection<Usuario> getUsuariosCollection1() {
        return usuariosCollection1;
    }

    public void setUsuariosCollection1(Collection<Usuario> usuariosCollection1) {
        this.usuariosCollection1 = usuariosCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRole != null ? idRole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roles)) {
            return false;
        }
        Roles other = (Roles) object;
        if ((this.idRole == null && other.idRole != null) || (this.idRole != null && !this.idRole.equals(other.idRole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "logica.Roles[ idRole=" + idRole + " ]";
    }
    
}
