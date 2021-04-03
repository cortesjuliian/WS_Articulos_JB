/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import com.udec.wsrestapparticulos.dao.UsuarioDAO;
import com.udec.wsrestapparticulos.domain.Usuario;
import com.udec.wsrestapparticulos.response.ResponseCrudUsuario;
import com.udec.wsrestapparticulos.response.ResponseUsuarios;
import com.udec.wsrestapparticulos.seguridad.Secured;
import java.util.Date;
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
@Path("APIUser")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class WSRestUser {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRestUser
     */
    public WSRestUser() {
    }

    @GET
    @Secured
    @Path("obtenerUsuarios")
    public ResponseUsuarios consultarUsuarios() {
        ResponseUsuarios responseUsuarios = new ResponseUsuarios();
        List<Usuario> listUsuarios = new UsuarioDAO().consultarUsuarios();
        responseUsuarios.setIsSuccess(Boolean.TRUE);
        responseUsuarios.setListUsuarios(listUsuarios);
        return responseUsuarios;
    }

    @POST
    @Secured
    @Path("saveUsuario")
    public ResponseCrudUsuario saveUsuario(Usuario usuario) {
        ResponseCrudUsuario responseCrudUsuario = new ResponseCrudUsuario();
        //usuario.setFechanacimiento(new Date());
        Usuario newUsuario = new UsuarioDAO().saveUsuario(usuario);
        if (newUsuario != null && newUsuario.getId() != null && newUsuario.getId() > 0) {
            responseCrudUsuario.setIsSuccess(Boolean.TRUE);
            responseCrudUsuario.setUsuario(newUsuario);
            responseCrudUsuario.setsMsj("REGISTRADO");
        } else {
            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
            responseCrudUsuario.setUsuario(null);
            responseCrudUsuario.setsMsj("PAILA");
        }
        return responseCrudUsuario;
    }
    
    @POST
    @Secured
    @Path("updateUsuario")
    public ResponseCrudUsuario updateUsuario(Usuario usuario){
        ResponseCrudUsuario responseCrudUsuario = new ResponseCrudUsuario();
        Usuario updateUsuario = new UsuarioDAO().updateUsuario(usuario);       
        if (updateUsuario != null && updateUsuario.getId() != null && updateUsuario.getId() > 0){
            responseCrudUsuario.setIsSuccess(Boolean.TRUE);
            responseCrudUsuario.setUsuario(updateUsuario);
            responseCrudUsuario.setsMsj("ACTUALIZADO");
        } else {
            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
            responseCrudUsuario.setUsuario(null);
            responseCrudUsuario.setsMsj("PAILA AL ACTUALIZAR");
        }
        return responseCrudUsuario;
    }
}
