/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.dao;

import com.udec.wsrestapparticulos.domain.Mensajes;
import com.udec.wsrestapparticulos.general.GenericRepositoryJPA;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julia
 */
public class MensajesDAO extends GenericRepositoryJPA<Mensajes> {
    public List<Mensajes> consultarMensajes() {
        List<Mensajes> listMensajes = new ArrayList<>();
        listMensajes = findAllOrderByCampo("DESC", "idMensaje");
        return listMensajes;
    }

    public Mensajes saveMensajes(Mensajes mensajes) {
        Mensajes newMensajes = new Mensajes();
        try {
            newMensajes = create(mensajes);
        } catch (Exception e) {
            e.printStackTrace();
            newMensajes = null;
        }
        return newMensajes;
    }

    public Mensajes updateMensajes(Mensajes mensajes) {
        Mensajes updateMensajes = new Mensajes();
        try {
            updateMensajes = update(mensajes);
        } catch (Exception e) {
            e.printStackTrace();
            updateMensajes = null;
        }
        return updateMensajes;
    }

    public Mensajes finMensajesById(Integer id) {
        Mensajes findMensajes = new Mensajes();
        try {
            findMensajes = find(id);
        } catch (Exception e) {
            e.printStackTrace();
            findMensajes = null;
        }
        return findMensajes;
    }
}
