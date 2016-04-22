/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "GP14USER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gp14user.findAll", query = "SELECT g FROM Gp14user g"),
    @NamedQuery(name = "Gp14user.findByCustomerId", query = "SELECT g FROM Gp14user g WHERE g.customerId = :customerId"),
    @NamedQuery(name = "Gp14user.findByUsername", query = "SELECT g FROM Gp14user g WHERE g.username = :username"),
    @NamedQuery(name = "Gp14user.findByPassword", query = "SELECT g FROM Gp14user g WHERE g.password = :password")})
public class Gp14user implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "PASSWORD")
    private String password;

    public Gp14user() {
    }

    public Gp14user(Integer customerId) {
        this.customerId = customerId;
    }

    public Gp14user(Integer customerId, String username, String password) {
        this.customerId = customerId;
        this.username = username;
        this.password = password;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerId != null ? customerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gp14user)) {
            return false;
        }
        Gp14user other = (Gp14user) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gp14user[ customerId=" + customerId + " ]";
    }
    
}
