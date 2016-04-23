/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author casper
 */
@Entity
@Table(name = "GP14GROUP")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gp14group.findAll", query = "SELECT g FROM Gp14group g"),
    @NamedQuery(name = "Gp14group.findByCustomerId", query = "SELECT g FROM Gp14group g WHERE g.gp14groupPK.customerId = :customerId"),
    @NamedQuery(name = "Gp14group.findByUsername", query = "SELECT g FROM Gp14group g WHERE g.username = :username"),
    @NamedQuery(name = "Gp14group.findByCustIdAndGroupname", 
            query = "SELECT g FROM Gp14group g WHERE g.gp14groupPK.customerId = :customerId AND g.gp14groupPK.groupname = :groupname"),
    @NamedQuery(name = "Gp14group.findByGroupname", query = "SELECT g FROM Gp14group g WHERE g.gp14groupPK.groupname = :groupname")})
public class Gp14group implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected Gp14groupPK gp14groupPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;

    public Gp14group() {
    }

    public Gp14group(Gp14groupPK gp14groupPK) {
        this.gp14groupPK = gp14groupPK;
    }

    public Gp14group(Gp14groupPK gp14groupPK, String username) {
        this.gp14groupPK = gp14groupPK;
        this.username = username;
    }

    public Gp14group(int customerId, String groupname) {
        this.gp14groupPK = new Gp14groupPK(customerId, groupname);
    }

    public Gp14groupPK getGp14groupPK() {
        return gp14groupPK;
    }

    public void setGp14groupPK(Gp14groupPK gp14groupPK) {
        this.gp14groupPK = gp14groupPK;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gp14groupPK != null ? gp14groupPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gp14group)) {
            return false;
        }
        Gp14group other = (Gp14group) object;
        if ((this.gp14groupPK == null && other.gp14groupPK != null) || (this.gp14groupPK != null && !this.gp14groupPK.equals(other.gp14groupPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gp14group[ gp14groupPK=" + gp14groupPK + " ]";
    }
    
}
