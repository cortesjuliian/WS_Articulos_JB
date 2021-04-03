/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import com.udec.wsrestapparticulos.dao.ArticuloDAO;
import com.udec.wsrestapparticulos.dao.ArticuloDAO;
import com.udec.wsrestapparticulos.domain.Articulo;
import com.udec.wsrestapparticulos.domain.Articulo;
import com.udec.wsrestapparticulos.response.ResponseCrudArticulo;
import com.udec.wsrestapparticulos.response.ResponseCrudArticulo;
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
@Path("APIArticulo")
@Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
public class WSRestArticulo {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRestArticulo
     */
    public WSRestArticulo() {
    }

    @GET
    @Secured
    @Path("obtenerArticulo")
    public ResponseCrudArticulo consultarArticulos() {
        ResponseCrudArticulo responseCrudArticulo = new ResponseCrudArticulo();
        try {
            List<Articulo> ListArticulo = new ArticuloDAO().consultarArticulos();
            responseCrudArticulo.setIsSuccess(Boolean.TRUE);
            responseCrudArticulo.setListArticulo(ListArticulo);
        } catch (Exception e) {
          e.printStackTrace();
          responseCrudArticulo.setIsSuccess(false);
          responseCrudArticulo.setsMsj("Se ha presentado un error al obtener los articulos");
        }
        return responseCrudArticulo;
    }
    
    @POST
    @Secured
    @Path("saveArticulo")
    public ResponseCrudArticulo saveArticulo(Articulo articulo) {
        ResponseCrudArticulo responseCrudArticulo = new ResponseCrudArticulo();
        //articulo.setFechanacimiento(new Date());
        try {
            Articulo newArticulo = new ArticuloDAO().saveArticulo(articulo);
        if (newArticulo != null && newArticulo.getIdArticulo()!= null && newArticulo.getIdArticulo()> 0) {
            responseCrudArticulo.setIsSuccess(Boolean.TRUE);
            responseCrudArticulo.setCrudArticulo(newArticulo);
            responseCrudArticulo.setsMsj("ARTICULO REGISTRADO");
        } else {
            responseCrudArticulo.setIsSuccess(Boolean.FALSE);
            responseCrudArticulo.setCrudArticulo(null);
            responseCrudArticulo.setsMsj("PAILA");
        }
        } catch (Exception e) {
             e.printStackTrace();
          responseCrudArticulo.setIsSuccess(false);
          responseCrudArticulo.setsMsj("Se ha presentado un error al registrar los articulos");
        }
        return responseCrudArticulo;
    }
    
    
    @POST
    @Secured
    @Path("updateArticulo")
    public ResponseCrudArticulo updateArticulo(Articulo articulo){
        ResponseCrudArticulo responseCrudArticulo = new ResponseCrudArticulo();
        try {
             Articulo updateArticulo = new ArticuloDAO().updateArticulo(articulo);       
        if (updateArticulo != null && updateArticulo.getIdArticulo() != null && updateArticulo.getIdArticulo() > 0){
            responseCrudArticulo.setIsSuccess(Boolean.TRUE);
            responseCrudArticulo.setArticulo(updateArticulo);
            responseCrudArticulo.setsMsj("ACTUALIZADO");
        } else {
            responseCrudArticulo.setIsSuccess(Boolean.FALSE);
            responseCrudArticulo.setArticulo(null);
            responseCrudArticulo.setsMsj("PAILA AL ACTUALIZAR");
        }            
        } catch (Exception e) {
            e.printStackTrace();
          responseCrudArticulo.setIsSuccess(false);
          responseCrudArticulo.setsMsj("Se ha presentado un error al actualizar los articulos");
        }            
        return responseCrudArticulo;
    }
}
