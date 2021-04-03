/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.response;

import com.udec.wsrestapparticulos.domain.Articulo;
import java.util.List;

/**
 *
 * @author julia
 */
public class ResponseCrudArticulo {
    private boolean isSuccess;
    private String sMsj;
    private Articulo CrudArticulo;
    private List<Articulo> ListArticulo;
    
    public boolean isIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getsMsj() {
        return sMsj;
    }

    public void setsMsj(String sMsj) {
        this.sMsj = sMsj;
    }

    public Articulo getCrudArticulo() {
        return CrudArticulo;
    }

    public void setCrudArticulo(Articulo CrudArticulo) {
        this.CrudArticulo = CrudArticulo;
    }

    public List<Articulo> getListArticulo() {
        return ListArticulo;
    }

    public void setListArticulo(List<Articulo> ListArticulo) {
        this.ListArticulo = ListArticulo;
    }

    public void setArticulo(Articulo updateArticulo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
