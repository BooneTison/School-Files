package main;

import java.io.*;
import java.util.*;
import javax.crypto.*;

/**
 * A simple substitution cipher that can encode and decode strings.
 * Used to scramble the role assignments so that it stays hidden until players put it 
 * into their own program to see their role privately.
 * 
 * Source: http://www.eaaranyak.com/Readmore.aspx?id=212
 */
public class Cipher {
	public static final int DEFAULT_KEY = 13;
    public static final String STR = "abcdefghijklmnopqrstuvwxyz";
    
    
    
    /**
     * Used to encrypt a message.
     * @param plaint is the plaintext message to encrypt
     */
    public static String encrypt(String plaint) {
    	return encrypt(plaint, DEFAULT_KEY);
    }
    
    /**
     * Used to decrypt a message.
     * @param ciphert is the encrypted message to decode.
     */
    public static String decrypt(String ciphert) {
    	return decrypt(ciphert, DEFAULT_KEY);
    }
    
    
    
    
    
    // HELPERS //
    
    private static String encrypt(String plaint,int key) {
        plaint = plaint.toLowerCase();
        String ciphert = "";
        for (int i=0; i<plaint.length(); i++) {
        	if (STR.indexOf(plaint.charAt(i)) != -1) {
        		int charpos = STR.indexOf(plaint.charAt(i));
                int keyval = (charpos+key)%26;
                char replaceval = STR.charAt(keyval);
                ciphert = ciphert + replaceval;
        	}
        	//if char not in str (as in, a space or symbol), keep it as is
        	else {
                ciphert = ciphert + plaint.charAt(i);
        	}
        }
        return ciphert;
    }


    private static String decrypt(String ciphert,int key) {
        ciphert = ciphert.toLowerCase();
        String plaint="";
        for (int i=0; i<ciphert.length(); i++) {
        	if (STR.indexOf(ciphert.charAt(i)) != -1) {
        		int charpos=STR.indexOf(ciphert.charAt(i));
                int keyval=(charpos-key)%26;
                if(keyval<0)
                {
                    keyval=STR.length()+keyval;
                }
                char replaceval=STR.charAt(keyval);
                plaint=plaint+replaceval;
        	}
        	//if char not in sTR (as in, a space or symbol), keep it as is
        	else {
        		plaint = plaint + ciphert.charAt(i);
        	}
            
        }
        return plaint;
    }
    /*
    public static void main(String args[])throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        BufferedReader kr=new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter a plain text");
        String msg=br.readLine();
        System.out.println("Enter a key");
        String key=br.readLine();
        int k=Integer.parseInt(key);    
        System.out.println("Encrypted Text");
        System.out.println(encrypt(msg,k));
        System.out.println("The decrypted text");
        System.out.println(decrypt(encrypt(msg,k),k));
    }
    */
}