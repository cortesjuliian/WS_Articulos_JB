/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import com.udec.wsrestapparticulos.dao.MensajesDAO;
import com.udec.wsrestapparticulos.domain.Mensajes;
import com.udec.wsrestapparticulos.response.ResponseCrudMensajes;
import com.udec.wsrestapparticulos.seguridad.Secured;
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
@Path("APIMensajes")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class WSRestMensajes {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRestMensajes
     */
    public WSRestMensajes() {
    }

    @GET
//    @Secured
    @Path("obtenerMensajes")
    public ResponseCrudMensajes consultarMensajes() {
        ResponseCrudMensajes responseMensajes = new ResponseCrudMensajes();
        List<Mensajes> listMensajes = new MensajesDAO().consultarMensajes();
        responseMensajes.setIsSuccess(Boolean.TRUE);
        responseMensajes.setListMensajes(listMensajes);
        return responseMensajes;
    }
    
    @POST
//    @Secured
    @Path("saveMensajes")
    public ResponseCrudMensajes saveMensajes(Mensajes mensaje) {
        ResponseCrudMensajes responseCrudMensajes = new ResponseCrudMensajes();
        //articulo.setFechanacimiento(new Date());
        try {
            Mensajes newMensajes = new MensajesDAO().saveMensajes(mensaje);
        if (newMensajes != null && newMensajes.getIdMensaje()!= null && newMensajes.getIdMensaje()> 0) {
            responseCrudMensajes.setIsSuccess(Boolean.TRUE);
            responseCrudMensajes.setCrudMensajes(newMensajes);
            responseCrudMensajes.setsMsj("MENSAJE REGISTRADO");
        } else {
            responseCrudMensajes.setIsSuccess(Boolean.FALSE);
            responseCrudMensajes.setCrudMensajes(null);
            responseCrudMensajes.setsMsj("PAILA");
        }
        } catch (Exception e) {
             e.printStackTrace();
          responseCrudMensajes.setIsSuccess(false);
          responseCrudMensajes.setsMsj("Se ha presentado un error al registrar los mensajes");
        }
        return responseCrudMensajes;
    }
    
    
    @POST
    @Secured
    @Path("updateMensajes")
    public ResponseCrudMensajes updateMensajes(Mensajes articulo){
        ResponseCrudMensajes responseCrudMensajes = new ResponseCrudMensajes();
        try {
             Mensajes updateMensajes = new MensajesDAO().updateMensajes(articulo);       
        if (updateMensajes != null && updateMensajes.getIdMensaje() != null && updateMensajes.getIdMensaje() > 0){
            responseCrudMensajes.setIsSuccess(Boolean.TRUE);
            responseCrudMensajes.setMensajes(updateMensajes);
            responseCrudMensajes.setsMsj("ACTUALIZADO");
        } else {
            responseCrudMensajes.setIsSuccess(Boolean.FALSE);
            responseCrudMensajes.setMensajes(null);
            responseCrudMensajes.setsMsj("PAILA AL ACTUALIZAR");
        }            
        } catch (Exception e) {
            e.printStackTrace();
          responseCrudMensajes.setIsSuccess(false);
          responseCrudMensajes.setsMsj("Se ha presentado un error al actualizar los mensajes");
        }            
        return responseCrudMensajes;
    }
}
