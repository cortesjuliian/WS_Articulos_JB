/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.udec.wsrestapparticulos.general;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author julia
 */
public class Utilidad {

    private static final String RegExp_EMAIL = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,4})+$";
    private static final String RegExp_NUMBER = "^[0-9]*$";
    private static final String RegExp_TEXT = "^[^<>*!¡¿?='&%$#\"|°)(/{}+´,._]+$";
    private static final String RegExp_TEXT_PASS = "^[^<>'\"|°)(/{}+´,._]+$";
    private static final String RegExp_IPv4 = "^(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])$";
    private static final String RegExp_BASE64 = "[^-A-Za-z0-9+/=]|=[^=]|={3,}$";
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public Boolean isNumber(String input) {
        Boolean isNumber = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_NUMBER);
        Matcher matcher = pattern.matcher(input);
        isNumber = matcher.matches();
        return isNumber;
    }

    public Boolean isEmail(String input) {
        Boolean isEmail = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_EMAIL);
        Matcher matcher = pattern.matcher(input);
        isEmail = matcher.matches();
        return isEmail;
    }

    public Boolean isText(String input) {
        Boolean isText = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_TEXT);
        Matcher matcher = pattern.matcher(input);
        isText = matcher.matches();
        return isText;
    }

    public Boolean isClaveAceptada(String input) {
        Boolean isTextClave = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_TEXT_PASS);
        Matcher matcher = pattern.matcher(input);
        isTextClave = matcher.matches();
        return isTextClave;
    }

    public Boolean isIPv4(String input) {
        Boolean isIPv4 = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_IPv4);
        Matcher matcher = pattern.matcher(input);
        isIPv4 = matcher.matches();
        return isIPv4;
    }

    public Boolean isBase64(String input) {
        Boolean isBase64 = Boolean.FALSE;
        Pattern pattern = Pattern.compile(RegExp_BASE64);
        Matcher matcher = pattern.matcher(input);
        isBase64 = matcher.matches();
        return isBase64;
    }

    public String sDecodeUTF_8(String sCadena) {
        String sCadenaEncodingUTF_8 = "";
        sCadenaEncodingUTF_8 = new String(sCadena.getBytes(), UTF_8);
        return sCadenaEncodingUTF_8;
    }

    public String sEncodeUTF_8(String sCadena) {
        String sCadenaEncodingUTF_8 = "";
        sCadenaEncodingUTF_8 = Base64.getEncoder().encodeToString(sCadena.getBytes());
        return sCadenaEncodingUTF_8;
    }

    public String encodeFileToBase64Binary(File file) {
        String encodedfile = null;
        try {
            FileInputStream fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = Base64.getEncoder().encodeToString(bytes);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encodedfile;
    }

    public String Limpieza_Cadena(String Cadena) {
        String Cadena_Final = "";
        Cadena_Final = Cadena.replace("<script>", "");
        Cadena_Final = Cadena_Final.replace("</script>", "");
        Cadena_Final = Cadena_Final.replace("<script></script>", "");
        return Cadena_Final;
    }
}
