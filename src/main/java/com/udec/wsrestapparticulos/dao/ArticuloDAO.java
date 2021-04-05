/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.dao;

import com.udec.wsrestapparticulos.domain.Articulo;
import com.udec.wsrestapparticulos.general.GenericRepositoryJPA;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author julia
 */
public class ArticuloDAO extends GenericRepositoryJPA<Articulo> {

    public List<Articulo> consultarArticulos() {
        List<Articulo> listArticulo = new ArrayList<>();
        listArticulo = findAllOrderByCampo("DESC", "idArticulo");
        return listArticulo;
    }

    public Articulo saveArticulo(Articulo articulo) {
        Articulo newArticulo = new Articulo();
        try {
            newArticulo = create(articulo);
        } catch (Exception e) {
            e.printStackTrace();
            newArticulo = null;
        }
        return newArticulo;
    }

    public Articulo updateArticulo(Articulo articulo) {
        Articulo updateArticulo = new Articulo();
        try {
            updateArticulo = update(articulo);
        } catch (Exception e) {
            e.printStackTrace();
            updateArticulo = null;
        }
        return updateArticulo;
    }

    public Articulo finArticuloById(Integer id) {
        Articulo findArticulo = new Articulo();
        try {
            findArticulo = find(id);
        } catch (Exception e) {
            e.printStackTrace();
            findArticulo = null;
        }
        return findArticulo;
    }
}
