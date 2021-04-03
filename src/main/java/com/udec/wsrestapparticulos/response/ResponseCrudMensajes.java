/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.response;

import com.udec.wsrestapparticulos.domain.Mensajes;
import java.util.List;
/**
 *
 * @author julia
 */
public class ResponseCrudMensajes {
    private boolean isSuccess;
    private String sMsj;
    private Mensajes CrudMensajes;
    private List<Mensajes> ListMensajes;
    
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

    public Mensajes getCrudMensajes() {
        return CrudMensajes;
    }

    public void setCrudMensajes(Mensajes CrudMensajes) {
        this.CrudMensajes = CrudMensajes;
    }

    public List<Mensajes> getListMensajes() {
        return ListMensajes;
    }

    public void setListMensajes(List<Mensajes> ListMensajes) {
        this.ListMensajes = ListMensajes;
    }

    public void setMensajes(Mensajes updateMensajes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
