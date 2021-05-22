/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import com.udec.wsrestapparticulos.dao.ArticuloDAO;
import com.udec.wsrestapparticulos.dao.UsuarioDAO;
import com.udec.wsrestapparticulos.domain.Articulo;
import com.udec.wsrestapparticulos.domain.Usuario;
import com.udec.wsrestapparticulos.general.Utilidad;
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

    private Utilidad util = null;
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRestArticulo
     */
    public WSRestArticulo() {
        util = new Utilidad();
    }

    @GET
    @Secured
    @Path("/obtenerArticulos")
    public ResponseCrudArticulo consultarArticulos() {
        ResponseCrudArticulo responseCrudArticulo = new ResponseCrudArticulo();
        try {
            List<Articulo> ListArticulo = new ArticuloDAO().consultarArticulos();
            if (ListArticulo != null && !ListArticulo.isEmpty()) {
                responseCrudArticulo.setIsSuccess(Boolean.TRUE);
                responseCrudArticulo.setsMsj("Se han encontrado los registros de articulos.");
                responseCrudArticulo.setListArticulo(ListArticulo);
            } else {
                responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                responseCrudArticulo.setsMsj("No se han encontrado registros de articulos.");
                responseCrudArticulo.setListArticulo(null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseCrudArticulo.setIsSuccess(false);
            responseCrudArticulo.setsMsj("Se ha presentado un error al obtener los articulos");
        }
        return responseCrudArticulo;
    }

    @POST
    @Secured
    @Path("/saveArticulo")
    public ResponseCrudArticulo saveArticulo(Articulo requestArticulo) {
        ResponseCrudArticulo responseCrudArticulo = new ResponseCrudArticulo();
        try {
            if (requestArticulo != null && requestArticulo.getIdUsuario() != null && requestArticulo.getIdUsuario().getId() != null && requestArticulo.getIdUsuario().getId() > 0) {
                String sTituloArticulo = requestArticulo.getTitulo();
                String sDescArticulo = requestArticulo.getArticulo();
                int iPublico = requestArticulo.getPublico();
                //SE LIMPIAN LOS DATOS QUE VIENEN DEL REQUEST
                sTituloArticulo = util.Limpieza_Cadena(sTituloArticulo);
                sDescArticulo = util.Limpieza_Cadena(sDescArticulo);

                Boolean isTxtTituloArticulo = util.isText(sTituloArticulo);
                Boolean isTxtArticulo = util.isText(sDescArticulo);
                Boolean isNumberPublico = util.isNumber(String.valueOf(iPublico));
                if (isTxtTituloArticulo && isTxtArticulo && isNumberPublico) {
                    Usuario findUsuarioId = new UsuarioDAO().finUsuarioById(requestArticulo.getIdUsuario().getId());
                    if (findUsuarioId != null && findUsuarioId.getId() != null && findUsuarioId.getId() > 0) {
                        Articulo newArticulo = new Articulo();
                        newArticulo.setTitulo(sDescArticulo);
                        newArticulo.setArticulo(sDescArticulo);
                        newArticulo.setPublico(iPublico);
                        newArticulo.setFecha(new Date());
                        newArticulo.setIdUsuario(findUsuarioId);
                        Articulo articuloCreado = new ArticuloDAO().saveArticulo(newArticulo);
                        if (articuloCreado != null && articuloCreado.getIdArticulo() != null && articuloCreado.getIdArticulo() > 0) {
                            responseCrudArticulo.setIsSuccess(Boolean.TRUE);
                            responseCrudArticulo.setCrudArticulo(articuloCreado);
                            responseCrudArticulo.setsMsj("ARTICULO REGISTRADO");
                        } else {
                            responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                            responseCrudArticulo.setCrudArticulo(null);
                            responseCrudArticulo.setsMsj("PAILA");
                        }
                    } else {
                        responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                        responseCrudArticulo.setCrudArticulo(null);
                        responseCrudArticulo.setsMsj("No se ha  logrado validar la información del usuario.");
                    }

                } else {
                    responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                    responseCrudArticulo.setCrudArticulo(null);
                    responseCrudArticulo.setsMsj("El titulo, articulo o el valor de si es publico el articulo no son validos.");
                }
            } else {
                responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                responseCrudArticulo.setCrudArticulo(null);
                responseCrudArticulo.setsMsj("Los datos del usuario enviados son ncesarios o no son validos.");
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
    @Path("/updateArticulo")
    public ResponseCrudArticulo updateArticulo(Articulo requestArticulo) {
        ResponseCrudArticulo responseCrudArticulo = new ResponseCrudArticulo();
        try {
            if (requestArticulo != null && requestArticulo.getIdArticulo() != null && requestArticulo.getIdArticulo() > 0 && requestArticulo.getIdUsuario() != null && requestArticulo.getIdUsuario().getId() != null && requestArticulo.getIdUsuario().getId() > 0) {
                Integer idArticulo = requestArticulo.getIdArticulo();
                String sTituloArticulo = requestArticulo.getTitulo();
                String sDescArticulo = requestArticulo.getArticulo();
                int iPublico = requestArticulo.getPublico();
                //SE LIMPIAN LOS DATOS QUE VIENEN DEL REQUEST
                sTituloArticulo = util.Limpieza_Cadena(sTituloArticulo);
                sDescArticulo = util.Limpieza_Cadena(sDescArticulo);

                Boolean isTxtTituloArticulo = util.isText(sTituloArticulo);
                Boolean isTxtArticulo = util.isText(sDescArticulo);
                Boolean isNumberPublico = util.isNumber(String.valueOf(iPublico));
                Boolean isNumberIdArticulo = util.isNumber(String.valueOf(idArticulo));
                if (isTxtTituloArticulo && isTxtArticulo && isNumberPublico && isNumberIdArticulo) {
                    Usuario findUsuarioId = new UsuarioDAO().finUsuarioById(requestArticulo.getIdUsuario().getId());
                    if (findUsuarioId != null && findUsuarioId.getId() != null && findUsuarioId.getId() > 0) {
                        Articulo findArticuloById = new ArticuloDAO().finArticuloById(idArticulo);
                        if (findArticuloById != null && findArticuloById.getIdArticulo() != null && findArticuloById.getIdArticulo() > 0) {
                            findArticuloById.setTitulo(sDescArticulo);
                            findArticuloById.setArticulo(sDescArticulo);
                            findArticuloById.setPublico(iPublico);
                            findArticuloById.setIdUsuario(findUsuarioId);
                            Articulo articuloCreado = new ArticuloDAO().saveArticulo(findArticuloById);
                            if (articuloCreado != null && articuloCreado.getIdArticulo() != null && articuloCreado.getIdArticulo() > 0) {
                                responseCrudArticulo.setIsSuccess(Boolean.TRUE);
                                responseCrudArticulo.setCrudArticulo(articuloCreado);
                                responseCrudArticulo.setsMsj("El articulo fue actualizado correctamente.");
                            } else {
                                responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                                responseCrudArticulo.setCrudArticulo(null);
                                responseCrudArticulo.setsMsj("No se ha logrado actualizar el articulo deseado.");
                            }
                        } else {
                            responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                            responseCrudArticulo.setsMsj("No se ha logrado validar la información del articulo enviado, por favor verifique la información enviada.");
                        }

                    } else {
                        responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                        responseCrudArticulo.setCrudArticulo(null);
                        responseCrudArticulo.setsMsj("No se ha  logrado validar la información del usuario.");
                    }

                } else {
                    responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                    responseCrudArticulo.setCrudArticulo(null);
                    responseCrudArticulo.setsMsj("El código, titulo, descripción del articulo o el valor de si es publico del articulo no son validos.");
                }
            } else {
                responseCrudArticulo.setIsSuccess(Boolean.FALSE);
                responseCrudArticulo.setCrudArticulo(null);
                responseCrudArticulo.setsMsj("Los datos del usuario enviados son ncesarios o no son validos.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseCrudArticulo.setIsSuccess(false);
            responseCrudArticulo.setsMsj("Se ha presentado un error al registrar los articulos");
        }
        return responseCrudArticulo;
    }
}
