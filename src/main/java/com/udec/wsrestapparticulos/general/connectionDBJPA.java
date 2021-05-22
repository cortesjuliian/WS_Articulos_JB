/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.general;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author julia
 */
public class ConnectionDBJPA {

    private String sNombrePersistencia = "WSRestAppArticulos_PU";
    private EntityManager em = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory(sNombrePersistencia);
        }
        if (em == null) {
            em = emf.createEntityManager();
        }

        return em;
    }

    public void closeEntityManager() {
        if (emf != null) {
            if (emf.isOpen()) {
                emf.close();
            }
            emf = null;
        }
        if (em != null) {
            if (em.isOpen()) {
                em.close();
            }
            em = null;
        }
    }
}
