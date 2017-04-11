package com.dj.application.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AESEncrypter {
	 private static final String keyValue = "XMzDdG4D03CKm2IxIWQw7g==";
	 
	public static String encrypt(String Data) throws Exception {
		 byte[] raw;
         String encryptedString;
         SecretKeySpec skeySpec;
         byte[] encryptText = Data.getBytes();
         Cipher cipher;
         try {
             raw = Base64.decodeBase64(keyValue);
             skeySpec = new SecretKeySpec(raw, "AES");
             cipher = Cipher.getInstance("AES");
             cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
             encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
         } 
         catch (Exception e) {
             e.printStackTrace();
             return "Error";
         }
         return encryptedString;
	        
	 }
	 
	public static String decrypt(String encryptedData) throws Exception {
		 Cipher cipher;
         String encryptedString;
         byte[] encryptText = null;
         byte[] raw;
         SecretKeySpec skeySpec;
         try {
             raw = Base64.decodeBase64(keyValue);
             skeySpec = new SecretKeySpec(raw, "AES");
             encryptText = Base64.decodeBase64(encryptedData);
             cipher = Cipher.getInstance("AES");
             cipher.init(Cipher.DECRYPT_MODE, skeySpec);
             encryptedString = new String(cipher.doFinal(encryptText));
         } catch (Exception e) {
             e.printStackTrace();
             return "Error";
         }
         return encryptedString;
	    }
	 
}