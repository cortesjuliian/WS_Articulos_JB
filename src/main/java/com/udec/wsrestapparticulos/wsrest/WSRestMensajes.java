/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import com.udec.wsrestapparticulos.dao.MensajesDAO;
import com.udec.wsrestapparticulos.dao.UsuarioDAO;
import com.udec.wsrestapparticulos.domain.Mensajes;
import com.udec.wsrestapparticulos.domain.Usuario;
import com.udec.wsrestapparticulos.general.Utilidad;
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

    private Utilidad util = null;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRestMensajes
     */
    public WSRestMensajes() {
        util = new Utilidad();
    }

    @GET
    @Secured
    @Path("/obtenerMensajes")
    public ResponseCrudMensajes consultarMensajes() {
        ResponseCrudMensajes responseMensajes = new ResponseCrudMensajes();
        List<Mensajes> listMensajes = new MensajesDAO().consultarMensajes();
        responseMensajes.setIsSuccess(Boolean.TRUE);
        responseMensajes.setListMensajes(listMensajes);
        return responseMensajes;
    }

    @POST
    @Secured
    @Path("/saveMensajes")
    public ResponseCrudMensajes saveMensajes(Mensajes mensaje) {
        ResponseCrudMensajes responseCrudMensajes = new ResponseCrudMensajes();
        //articulo.setFechanacimiento(new Date());
        try {
            String sAsunto = mensaje.getAsunto();
            String sMsj = mensaje.getMensaje();
            sAsunto = util.Limpieza_Cadena(sAsunto);
            sMsj = util.Limpieza_Cadena(sMsj);
            Boolean isTxtAsunto = util.isText(sAsunto);
            Boolean isTxtMsj = util.isText(sMsj);
            if (isTxtAsunto && isTxtMsj) {
                Mensajes nuevoMensaje = new Mensajes();
                nuevoMensaje.setAsunto(sAsunto);
                nuevoMensaje.setMensaje(sMsj);

                Usuario usuarioDestinatario = mensaje.getIdDnatario();
                Usuario usuarioRemitente = mensaje.getIdRtente();

                if (usuarioDestinatario != null && usuarioDestinatario.getId() != null && usuarioRemitente != null && usuarioRemitente.getId() != null) {
                    Usuario findUsuarioDest = new UsuarioDAO().finUsuarioById(usuarioDestinatario.getId());
                    Usuario findUsuarioRem = new UsuarioDAO().finUsuarioById(usuarioRemitente.getId());
                    if (findUsuarioDest != null && findUsuarioDest.getId() != null && findUsuarioDest.getId() > 0 && findUsuarioRem != null && findUsuarioRem.getId() != null && findUsuarioRem.getId() > 0) {
                        nuevoMensaje.setIdDnatario(findUsuarioDest);
                        nuevoMensaje.setIdRtente(usuarioRemitente);
                        Mensajes mensajeCreado = new MensajesDAO().saveMensajes(nuevoMensaje);
                        if (mensajeCreado != null && mensajeCreado.getIdMensaje() != null && mensajeCreado.getIdMensaje() > 0) {
                            responseCrudMensajes.setIsSuccess(Boolean.TRUE);
                            responseCrudMensajes.setCrudMensajes(mensajeCreado);
                            responseCrudMensajes.setsMsj("MENSAJE REGISTRADO");
                        } else {
                            responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                            responseCrudMensajes.setCrudMensajes(null);
                            responseCrudMensajes.setsMsj("PAILA");
                        }
                    } else {
                        responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                        responseCrudMensajes.setsMsj("No se ha logrado validar los datos del usuario destinatario y remitente.");
                    }
                } else {
                    responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                    responseCrudMensajes.setsMsj("Los datos del usuario destinatario y remitente son necesarios.");
                }

            } else {
                responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                responseCrudMensajes.setsMsj("El asunto y mensaje no son validos.");
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
    @Path("/updateMensajes")
    public ResponseCrudMensajes updateMensajes(Mensajes mensaje) {
        ResponseCrudMensajes responseCrudMensajes = new ResponseCrudMensajes();
        try {
            String sAsunto = mensaje.getAsunto();
            String sMsj = mensaje.getMensaje();
            sAsunto = util.Limpieza_Cadena(sAsunto);
            sMsj = util.Limpieza_Cadena(sMsj);
            Boolean isTxtAsunto = util.isText(sAsunto);
            Boolean isTxtMsj = util.isText(sMsj);
            if (isTxtAsunto && isTxtMsj) {
                Mensajes actualizarMensaje = new Mensajes();
                actualizarMensaje.setAsunto(sAsunto);
                actualizarMensaje.setMensaje(sMsj);
                Usuario usuarioDestinatario = mensaje.getIdDnatario();
                Usuario usuarioRemitente = mensaje.getIdRtente();
                if (usuarioDestinatario != null && usuarioDestinatario.getId() != null && usuarioRemitente != null && usuarioRemitente.getId() != null) {
                    Usuario findUsuarioDest = new UsuarioDAO().finUsuarioById(usuarioDestinatario.getId());
                    Usuario findUsuarioRem = new UsuarioDAO().finUsuarioById(usuarioRemitente.getId());
                    if (findUsuarioDest != null && findUsuarioDest.getId() != null && findUsuarioDest.getId() > 0 && findUsuarioRem != null && findUsuarioRem.getId() != null && findUsuarioRem.getId() > 0) {
                        actualizarMensaje.setIdDnatario(findUsuarioDest);
                        actualizarMensaje.setIdRtente(usuarioRemitente);
                        Mensajes mensajeActualizado = new MensajesDAO().updateMensajes(actualizarMensaje);
                        if (mensajeActualizado != null && mensajeActualizado.getIdMensaje() != null && mensajeActualizado.getIdMensaje() > 0) {
                            responseCrudMensajes.setIsSuccess(Boolean.TRUE);
                            responseCrudMensajes.setCrudMensajes(mensajeActualizado);
                            responseCrudMensajes.setsMsj("MENSAJE ACTUALIZADO");
                        } else {
                            responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                            responseCrudMensajes.setCrudMensajes(null);
                            responseCrudMensajes.setsMsj("NO SE LOGRO ACTUALIAR EL MENSAJE");
                        }
                    } else {
                        responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                        responseCrudMensajes.setsMsj("No se ha logrado validar los datos del usuario destinatario y remitente.");
                    }
                } else {
                    responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                    responseCrudMensajes.setsMsj("Los datos del usuario destinatario y remitente son necesarios.");
                }

            } else {
                responseCrudMensajes.setIsSuccess(Boolean.FALSE);
                responseCrudMensajes.setsMsj("El asunto y mensaje no son validos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseCrudMensajes.setIsSuccess(false);
            responseCrudMensajes.setsMsj("Se ha presentado un error al actualizar los mensajes");
        }
        return responseCrudMensajes;
    }
}
