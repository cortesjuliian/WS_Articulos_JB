/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.general;

import java.util.List;

/**
 *
 * @author julia
 */
public interface GenericRepository<T> {

  T create(T t) throws Exception;

    void delete(T t) throws Exception;

    T find(T t) throws Exception;

    T update(T t) throws Exception;

    List<T> findAll() throws Exception;

    List<T> findAllOrderByCampo(String sTipoOrder, String sCampoOrder) throws Exception;

    List<T> findByNamedQueryForValueString(T t, String sNameQuery, String sCampoFind, String sValueFind) throws Exception;

    List<T> findByNamedQueryForValueEntity(T t, String sNameQuery, String sCampoFind, T valueEntity) throws Exception;
}
