/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.dao;

import com.udec.wsrestapparticulos.domain.Usuario;
import com.udec.wsrestapparticulos.general.ConnectionDBJPA;
import com.udec.wsrestapparticulos.general.GenericRepositoryJPA;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author julia
 */
public class UsuarioDAO extends GenericRepositoryJPA<Usuario> {

    private EntityManager em = null;
    
    public List<Usuario> consultarUsuarios() {
        List<Usuario> listUsuario = new ArrayList<>();
        listUsuario = findAll();
        return listUsuario;
    }

    public Usuario saveUsuario(Usuario usuario) {
        Usuario newUsuario = new Usuario();
        try {
            newUsuario = create(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            newUsuario = null;
        }
        return newUsuario;
    }

    public Usuario updateUsuario(Usuario usuario) {
        Usuario updateUsuario = new Usuario();
        try {
            updateUsuario = update(usuario);
        } catch (Exception e) {
            e.printStackTrace();
            updateUsuario = null;
        }
        return updateUsuario;
    }

    public Usuario finUsuarioById(Integer id) {
        Usuario findUsuario = new Usuario();
        try {
            findUsuario = find(id);
        } catch (Exception e) {
            e.printStackTrace();
            findUsuario = null;
        }
        return findUsuario;
    }
    
    public Usuario verificarLogin(Usuario usuario) {
        Usuario verifiUserCredenciales = new Usuario();
        ConnectionDBJPA connDBJPA = new ConnectionDBJPA();
        try {
            em = connDBJPA.getEntityManager();
            String sSQL = "SELECT u.*\n"
                    + "FROM usurios u,\n"
                    + "WHERE u.usuario= ? and u.pass = ?";
            Query consultDocument = em.createNativeQuery(sSQL, Usuario.class);

            consultDocument.setParameter(1, usuario.getUsuario());
            consultDocument.setParameter(2, usuario.getPass());

            List<Usuario> listUsuario = consultDocument.getResultList();
            if (listUsuario != null && !listUsuario.isEmpty()) {
                for (Usuario u : listUsuario) {
                    if (u.getUsuario().equals(usuario.getUsuario()) && u.getPass().equals(usuario.getPass())) {
                        verifiUserCredenciales = u;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connDBJPA.closeEntityManager();
            if (em != null) {
                if (em.isOpen()) {
                    em.close();
                }
            }
        }
        return verifiUserCredenciales;
    }

   
}


