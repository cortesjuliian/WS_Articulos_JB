/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.general;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author julia
 */
public class utilidad {

    private static final String RegExp_NUMBER = "^[0-9]*$";
    private static final String RegExp_Script = "^[0-9]*$";
    
    public Boolean isNumber(String input) {
        Boolean isNumber = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_NUMBER);
        Matcher matcher = pattern.matcher(input);
        isNumber = matcher.matches();
        return isNumber;
    }
    
     public Boolean ValidateScript(String input) {
        Boolean ValidateScript = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_Script);
        Matcher matcher = pattern.matcher(input);
        ValidateScript = matcher.matches();
        return ValidateScript;
    }
    
    public String Limpieza_Cadena (String Cadena){
        String Cadena_Final="";
        Cadena_Final=Cadena.replace("<script>", "");
        Cadena_Final=Cadena_Final.replace("</script>", "");
        Cadena_Final=Cadena_Final.replace("<script></script>", "");
        return Cadena_Final;
    }
}
