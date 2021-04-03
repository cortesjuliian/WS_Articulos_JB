/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.dao;

import com.udec.wsrestapparticulos.domain.Usuario;
import com.udec.wsrestapparticulos.general.GenericRepositoryJPA;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julia
 */
public class UsuarioDAO extends GenericRepositoryJPA<Usuario> {

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
}
