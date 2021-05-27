/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.response;

/**
 *
 * @author julia
 */
public class ResponseLogin {
    private boolean isSuccess;
    private String sMsj;    
    private String TokenLogin;

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

    public String getTokenLogin() {
        return TokenLogin;
    }

    public void setTokenLogin(String TokenLogin) {
        this.TokenLogin = TokenLogin;
    }
    
    
}
