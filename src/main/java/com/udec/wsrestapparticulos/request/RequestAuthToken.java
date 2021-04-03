/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.request;

/**
 *
 * @author julia
 */
public class RequestAuthToken {

    private String sUsuario;
    private String sPass;

    public String getSUsuario() {
        return sUsuario;
    }

    public void setSUsuario(String sUsuario) {
        this.sUsuario = sUsuario;
    }

    public String getSPass() {
        return sPass;
    }

    public void setSPass(String sPass) {
        this.sPass = sPass;
    }

}
