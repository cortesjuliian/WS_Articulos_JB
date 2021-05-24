/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.request;

import com.udec.wsrestapparticulos.domain.Usuario;

/**
 *
 * @author julia
 */
public class RequestCambiarClave {

    private String sCurrentPass;
    private String sNewPass;
    private String sConfirNewPass;
    private Usuario currentUser;

    public String getsCurrentPass() {
        return sCurrentPass;
    }

    public void setsCurrentPass(String sCurrentPass) {
        this.sCurrentPass = sCurrentPass;
    }

    public String getsNewPass() {
        return sNewPass;
    }

    public void setsNewPass(String sNewPass) {
        this.sNewPass = sNewPass;
    }

    public String getsConfirNewPass() {
        return sConfirNewPass;
    }

    public void setsConfirNewPass(String sConfirNewPass) {
        this.sConfirNewPass = sConfirNewPass;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }

}
