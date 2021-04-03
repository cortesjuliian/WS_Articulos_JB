/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import com.udec.wsrestapparticulos.dao.UsuarioDAO;
import com.udec.wsrestapparticulos.domain.Usuario;
import com.udec.wsrestapparticulos.request.RequestAuthToken;
import com.udec.wsrestapparticulos.response.ResponseAuthAPI;
import com.udec.wsrestapparticulos.seguridad.JwtTokenHelper;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;



/**
 * REST Web Service
 *
 * @author julia
 */
@Path("AuthAPI")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class WSRestAuth {

    private final JwtTokenHelper jwtTokenHelper;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRestAuth
     */
    public WSRestAuth() {
        this.jwtTokenHelper = new JwtTokenHelper();
    }

    @POST
    @Path("WSAutAPI")
    public ResponseAuthAPI autenticate_api(RequestAuthToken requestAuthToken) {
        ResponseAuthAPI tokenJWT = new ResponseAuthAPI();
        try {
            String user = "LineaProf2021";
            String pass = "540938c5-89b6-44ce-bbe6-3e8aefb9c3d0";
            if (requestAuthToken != null) {
                if (requestAuthToken.getSUsuario() != null && !requestAuthToken.getSUsuario().equals("") && requestAuthToken.getSPass() != null && !requestAuthToken.getSPass().equals("")) {
                    if (user.equals(requestAuthToken.getSUsuario()) && pass.equals(requestAuthToken.getSPass())) {
                        String token = jwtTokenHelper.issueTokenAuth();
                        tokenJWT.setIsSuccess(Boolean.TRUE);
                        tokenJWT.setsMsj("Token generado");
                        tokenJWT.setsToken("Bearer" + token);
                    } else {
                        tokenJWT.setIsSuccess(Boolean.FALSE);
                        tokenJWT.setsMsj("Verifique los datos enviados");
                    }
                } else {
                    tokenJWT.setIsSuccess(Boolean.FALSE);
                    tokenJWT.setsMsj("Verifique que se encuentren todos datos necesarios.");

                }

            } else {
                tokenJWT.setIsSuccess(Boolean.FALSE);
                tokenJWT.setsMsj("Verifique que se encuentren todos datos necesarios.");

            }

        } catch (Exception e) {
            e.printStackTrace();
            tokenJWT.setIsSuccess(Boolean.FALSE);
            tokenJWT.setsMsj("Inconveniente al generar token de autenticación");
        }
        return tokenJWT;
    }

}
