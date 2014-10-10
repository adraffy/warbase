package com.antistupid.warbase.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

public class SHA1 {

    
    static public byte[] digest(byte[] dat) {
        try {            
            return MessageDigest.getInstance("SHA-1").digest(dat);
        } catch(NoSuchAlgorithmException err) {
            throw new IllegalArgumentException(err); // wont happen
        }  
    }
    
    static public String hexString(String x) {
        return DatatypeConverter.printHexBinary(digest(x.getBytes(StandardCharsets.UTF_8)));
    }
    
    
}
