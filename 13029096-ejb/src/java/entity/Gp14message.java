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
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Entity
@Table(name = "GP14MESSAGE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gp14message.findAll", query = "SELECT g FROM Gp14message g"),
    @NamedQuery(name = "Gp14message.findByCustomerId", query = "SELECT g FROM Gp14message g WHERE g.customerId = :customerId"),
    @NamedQuery(name = "Gp14message.findByMessage", query = "SELECT g FROM Gp14message g WHERE g.message = :message")})
public class Gp14message implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CUSTOMER_ID")
    private Integer customerId;
    @Size(max = 500)
    @Column(name = "MESSAGE")
    private String message;

    /**
     *
     */
    public Gp14message() {
    }

    /**
     *
     * @param customerId
     */
    public Gp14message(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     *
     * @param customerId
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
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
        if (!(object instanceof Gp14message)) {
            return false;
        }
        Gp14message other = (Gp14message) object;
        if ((this.customerId == null && other.customerId != null) || (this.customerId != null && !this.customerId.equals(other.customerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Gp14message[ customerId=" + customerId + " ]";
    }
    
}
