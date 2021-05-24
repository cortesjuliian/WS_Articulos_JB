/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.wsrest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author julia
 */
@javax.ws.rs.ApplicationPath("API")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.udec.wsrestapparticulos.seguridad.CrossOriginFilterAPI.class);
        resources.add(com.udec.wsrestapparticulos.seguridad.RestSecurityFilter.class);
        resources.add(com.udec.wsrestapparticulos.wsrest.WSRestArticulo.class);
        resources.add(com.udec.wsrestapparticulos.wsrest.WSRestAuth.class);
        resources.add(com.udec.wsrestapparticulos.wsrest.WSRestMensajes.class);
        resources.add(com.udec.wsrestapparticulos.wsrest.WSRestUser.class);
        resources.add(org.glassfish.jersey.client.filter.HttpDigestAuthFilter.class);
        resources.add(org.glassfish.jersey.server.wadl.internal.WadlResource.class);
    }
    
}
