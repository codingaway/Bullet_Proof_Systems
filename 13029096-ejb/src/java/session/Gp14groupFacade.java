/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.Gp14group;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Abdul Halim <13029096@studentmail.ul.ie>
 */
@Stateless
public class Gp14groupFacade extends AbstractFacade<Gp14group> {

    @PersistenceContext(unitName = "13029096-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Gp14groupFacade() {
        super(Gp14group.class);
    }
    
}
