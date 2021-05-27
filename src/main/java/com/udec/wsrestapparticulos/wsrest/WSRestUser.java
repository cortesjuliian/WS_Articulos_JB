/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import com.udec.wsrestapparticulos.dao.UsuarioDAO;
import com.udec.wsrestapparticulos.domain.Usuario;
import com.udec.wsrestapparticulos.general.Encriptar;
import com.udec.wsrestapparticulos.general.Utilidad;
import com.udec.wsrestapparticulos.request.RequestCambiarClave;
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
    
    private Utilidad util = null;
    private Encriptar encriptar = null;
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSRestUser
     */
    public WSRestUser() {
        util = new Utilidad();
        encriptar = new Encriptar();
    }
    
    @GET
    @Secured
    @Path("/obtenerUsuarios")
    public ResponseUsuarios consultarUsuarios() {
        ResponseUsuarios responseUsuarios = new ResponseUsuarios();
        try {
            List<Usuario> listUsuarios = new UsuarioDAO().consultarUsuarios();
            if (listUsuarios != null && !listUsuarios.isEmpty()) {
                responseUsuarios.setIsSuccess(Boolean.TRUE);
                responseUsuarios.setListUsuarios(listUsuarios);
                responseUsuarios.setsMsj("Se han obtenido los registro de los usuarios");
            } else {
                responseUsuarios.setIsSuccess(Boolean.FALSE);
                responseUsuarios.setsMsj("No se han encontrado registros de usuarios");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseUsuarios.setIsSuccess(Boolean.FALSE);
            responseUsuarios.setsMsj("Se ha presentando un inconveniente inesperado al procesar la solicitud, por favor intente de nuevo.");
        }
        return responseUsuarios;
    }
    
    @POST
    @Secured
    @Path("/saveUsuario")
    public ResponseCrudUsuario saveUsuario(Usuario requestUsuario) {
        ResponseCrudUsuario responseCrudUsuario = new ResponseCrudUsuario();
        try {
            if (requestUsuario != null) {
                String sNommbres = requestUsuario.getNombre();
                String sApellidos = requestUsuario.getApellido();
                String sEmail = requestUsuario.getEmail();
                String sCantidadHijos = requestUsuario.getCantidadhijos();
                String sEstadoCivil = requestUsuario.getEstadocivil();
                String sUsuario = requestUsuario.getUsuario();
                String sClave = requestUsuario.getPass();
                //SE LIMPIAN LOS DATOS QUE VIENEN DEL REQUEST
                sNommbres = util.Limpieza_Cadena(sNommbres);
                sApellidos = util.Limpieza_Cadena(sApellidos);
                sEmail = util.Limpieza_Cadena(sEmail);
                sCantidadHijos = util.Limpieza_Cadena(sCantidadHijos);
                sEstadoCivil = util.Limpieza_Cadena(sEstadoCivil);
                sUsuario = util.Limpieza_Cadena(sUsuario);
                sClave = util.Limpieza_Cadena(sClave);
                //SE VALIDAN LOS DATOS
                Boolean isTxtNombres = util.isText(sNommbres);
                Boolean isTxtApellidos = util.isText(sApellidos);
                Boolean isTxtEmail = util.isEmail(sEmail);
                Boolean isNumberCantHijos = util.isNumber(sCantidadHijos);
                Boolean isTxtEstadoCivil = util.isText(sEstadoCivil);
                Boolean isTxtUsuario = util.isText(sUsuario);
                Boolean isClaveAcept = util.isClaveAceptada(sClave);
                
                if (isTxtNombres && isTxtApellidos) {
                    if (isTxtEmail && isTxtEstadoCivil) {
                        if (isNumberCantHijos) {
                            if (isTxtUsuario && isClaveAcept) {
                                Usuario newUsuario = new Usuario();
                                newUsuario.setNombre(sNommbres);
                                newUsuario.setApellido(sApellidos);
                                newUsuario.setEmail(sEmail);
                                newUsuario.setEstadocivil(sEstadoCivil);
                                newUsuario.setCantidadhijos(sCantidadHijos);
                                newUsuario.setUsuario(sUsuario);
                                String sPass_To_MD5 = encriptar.encriptar_MD5(sClave);                                
                                newUsuario.setPass(sPass_To_MD5);
                                newUsuario.setTipodocumento("");
                                newUsuario.setNumerodocumento(0);
                                newUsuario.setFechanacimiento(new Date());
                                newUsuario.setFoto("".getBytes());
                                newUsuario.setNombrefoto("");
                                newUsuario.setTelefono("");
                                newUsuario.setTipofoto("");                                
                                Usuario usuarioCreado = new UsuarioDAO().saveUsuario(newUsuario);
                                if (usuarioCreado != null && usuarioCreado.getId() != null && usuarioCreado.getId() > 0) {
                                    responseCrudUsuario.setIsSuccess(Boolean.TRUE);
                                    responseCrudUsuario.setUsuario(newUsuario);
                                    responseCrudUsuario.setsMsj("REGISTRADO");
                                } else {
                                    responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                                    responseCrudUsuario.setUsuario(null);
                                    responseCrudUsuario.setsMsj("PAILA");
                                }
                            } else {
                                responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                                responseCrudUsuario.setUsuario(null);
                                responseCrudUsuario.setsMsj("El usuario y la clave no son validos");
                            }
                        } else {
                            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                            responseCrudUsuario.setUsuario(null);
                            responseCrudUsuario.setsMsj("El dato de cantidad de hijos no es valido");
                        }
                    } else {
                        responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                        responseCrudUsuario.setUsuario(null);
                        responseCrudUsuario.setsMsj("El email o estado civil no son validos");
                    }
                } else {
                    responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                    responseCrudUsuario.setUsuario(null);
                    responseCrudUsuario.setsMsj("Los nombre so apellidos no son validos");
                }
                
            } else {
                responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                responseCrudUsuario.setUsuario(null);
                responseCrudUsuario.setsMsj("Los parametros son requeridos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
            responseCrudUsuario.setsMsj("INCONVENIENTE AL PROCESAR LA SOLICITUD DE REGISTRO");
        }
        return responseCrudUsuario;
    }
    
    @POST
    @Secured
    @Path("/updateUsuario")
    public ResponseCrudUsuario updateUsuario(Usuario requestUsuario) {
        ResponseCrudUsuario responseCrudUsuario = new ResponseCrudUsuario();
        try {
            if (requestUsuario != null && requestUsuario.getId() != null && requestUsuario.getId() > 0) {
                Integer idUsuario = requestUsuario.getId();
                String sNommbres = requestUsuario.getNombre();
                String sApellidos = requestUsuario.getApellido();
                String sEmail = requestUsuario.getEmail();
                String sCantidadHijos = requestUsuario.getCantidadhijos();
                String sEstadoCivil = requestUsuario.getEstadocivil();
                //SE LIMPIAN LOS DATOS QUE VIENEN DEL REQUEST
                sNommbres = util.Limpieza_Cadena(sNommbres);
                sApellidos = util.Limpieza_Cadena(sApellidos);
                sEmail = util.Limpieza_Cadena(sEmail);
                sCantidadHijos = util.Limpieza_Cadena(sCantidadHijos);
                sEstadoCivil = util.Limpieza_Cadena(sEstadoCivil);
                //SE VALIDAN LOS DATOS
                Boolean isNumberIdUser = util.isNumber(String.valueOf(idUsuario));
                Boolean isTxtNombres = util.isText(sNommbres);
                Boolean isTxtApellidos = util.isText(sApellidos);
                Boolean isTxtEmail = util.isEmail(sEmail);
                Boolean isNumberCantHijos = util.isNumber(sCantidadHijos);
                Boolean isTxtEstadoCivil = util.isText(sEstadoCivil);
                if (isNumberIdUser && isTxtNombres && isTxtApellidos) {
                    if (isTxtEmail && isTxtEstadoCivil) {
                        if (isNumberCantHijos) {
                            Usuario findUsuarioById = new UsuarioDAO().finUsuarioById(idUsuario);
                            if (findUsuarioById != null && findUsuarioById.getId() != null && findUsuarioById.getId() > 0) {
                                findUsuarioById.setNombre(sNommbres);
                                findUsuarioById.setApellido(sApellidos);
                                findUsuarioById.setEmail(sEmail);
                                findUsuarioById.setEstadocivil(sEstadoCivil);
                                findUsuarioById.setCantidadhijos(sCantidadHijos);
                                Usuario usuarioActualizado = new UsuarioDAO().updateUsuario(findUsuarioById);
                                if (usuarioActualizado != null && usuarioActualizado.getId() != null && usuarioActualizado.getId() > 0) {
                                    responseCrudUsuario.setIsSuccess(Boolean.TRUE);
                                    responseCrudUsuario.setUsuario(findUsuarioById);
                                    responseCrudUsuario.setsMsj("Se ha actualizado la información del usuario");
                                } else {
                                    responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                                    responseCrudUsuario.setsMsj("No se ha logrado actualizar la información del usuario");
                                }
                            } else {
                                responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                                responseCrudUsuario.setsMsj("No se ha logrado validar la información del usuario.");
                            }
                            
                        } else {
                            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                            responseCrudUsuario.setsMsj("El dato de cantidad de hijos no es valido");
                        }
                    } else {
                        responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                        responseCrudUsuario.setsMsj("El email o estado civil no son validos");
                    }
                } else {
                    responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                    responseCrudUsuario.setsMsj("El código, nombres o apellidos del usuario no son validos");
                }
                
            } else {
                responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                responseCrudUsuario.setsMsj("Los parametros son requeridos");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
            responseCrudUsuario.setsMsj("INCONVENIENTE AL PROCESAR LA SOLICITUD DE ACTUALIZACIÓN");
        }
        return responseCrudUsuario;
    }
    
    @POST
    @Secured
    @Path("/cambiarClaveUsuario")
    public ResponseCrudUsuario cambiarClaveUsuario(RequestCambiarClave requestCambiarClave) {
        ResponseCrudUsuario responseCrudUsuario = new ResponseCrudUsuario();
        try {
            if (requestCambiarClave != null && requestCambiarClave.getCurrentUser() != null && requestCambiarClave.getCurrentUser().getId() != null && util.isNumber(String.valueOf(requestCambiarClave.getCurrentUser().getId()))) {
                String sCurrentPass = requestCambiarClave.getsCurrentPass();
                String sNewPass = requestCambiarClave.getsNewPass();
                String sConfirmNewPass = requestCambiarClave.getsConfirNewPass();
                //LIMPIEZA DE LAS VARIABLES QUE VIENE DEL REQUEST
                sCurrentPass = util.Limpieza_Cadena(sCurrentPass);
                sNewPass = util.Limpieza_Cadena(sNewPass);
                sConfirmNewPass = util.Limpieza_Cadena(sConfirmNewPass);
                //VALIDACION DE LA CLAVE
                Boolean isTxtCurrentPass = util.isClaveAceptada(sCurrentPass);
                Boolean isTxtNewPass = util.isClaveAceptada(sNewPass);
                Boolean isTxtConfirNewPass = util.isClaveAceptada(sConfirmNewPass);
                if (isTxtCurrentPass && isTxtNewPass && isTxtConfirNewPass) {
                    Usuario findUsuarioById = new UsuarioDAO().finUsuarioById(requestCambiarClave.getCurrentUser().getId());
                    if (findUsuarioById != null && findUsuarioById.getId() != null && findUsuarioById.getId() > 0) {
                        String sPassOldMD5 = encriptar.encriptar_MD5(sCurrentPass);
                        if (sPassOldMD5.equals(findUsuarioById.getPass())) {
                            if (sNewPass.equals(sConfirmNewPass)) {
                                String sPassToMD5 = encriptar.encriptar_MD5(sNewPass);
                                findUsuarioById.setPass(sPassToMD5);
                                Usuario updateUsuario = new UsuarioDAO().updateUsuario(findUsuarioById);
                                if (updateUsuario != null && updateUsuario.getId() != null && updateUsuario.getId() > 0) {
                                    responseCrudUsuario.setIsSuccess(Boolean.TRUE);
                                    responseCrudUsuario.setUsuario(updateUsuario);
                                    responseCrudUsuario.setsMsj("Se ha cambiado la clave del usuario de manera correcta.");
                                } else {
                                    responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                                    responseCrudUsuario.setsMsj("Los datos del usuario no son correctos o validos, por favor verifique la información.");
                                }
                            } else {
                                responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                                responseCrudUsuario.setsMsj("La nueva clave debe coincidir con la confirmación, por favor verifique la información.");
                            }
                            
                        } else {
                            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                            responseCrudUsuario.setsMsj("La clave actual no es similar a ingresada, por favor verifique la infomración ingresada.");
                        }
                    } else {
                        responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                        responseCrudUsuario.setsMsj("No se ha logrado validar la información del usuario, por favor verifique la información del usuario.");
                    }
                    
                } else {
                    responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                    responseCrudUsuario.setsMsj("La clave actual, nueva clave o confirmación de la nueva clave no son validas, por favor verifique la información.");
                }
                
            } else {
                responseCrudUsuario.setIsSuccess(Boolean.FALSE);
                responseCrudUsuario.setsMsj("Los datos del usuario no son correctos o validos, por favor verifique la infomración.");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            responseCrudUsuario.setIsSuccess(Boolean.FALSE);
            responseCrudUsuario.setsMsj("Se ha presentado un inconveniente al procesar el cambio de clave para el usuario, por favor intente de nuevo.");
        }
        
        return responseCrudUsuario;
    }
}
