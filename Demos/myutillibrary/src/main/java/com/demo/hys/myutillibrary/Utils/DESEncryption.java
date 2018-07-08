package com.demo.hys.myutillibrary.Utils;


import static com.demo.hys.myutillibrary.Utils.Base64.decode;
import static com.demo.hys.myutillibrary.Utils.Base64.encode;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class DESEncryption implements Encryptions{

    String key = "hxgj0571";
    String iv = "05710571";

  public DESEncryption(String key, String iv) {
    this.key = key;
    this.iv = iv;
  }

  @Override
  public String encrpy(String encrpyString) throws Exception{
    IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DES");
    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, skey, zeroIv);
    byte[] encryptedData = cipher.doFinal(encrpyString.getBytes());
    return encode(encryptedData);
  }

  @Override
  public String decrpy(String decrpyString) throws Exception{
    byte[] byteMi = decode(decrpyString);
    IvParameterSpec zeroIv = new IvParameterSpec(iv.getBytes());
    SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "DES");
    Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, skey, zeroIv);
    byte decryptedData[] = cipher.doFinal(byteMi);
    return new String(decryptedData);
  }

//  public static String encryption(String text) {
//
//    if (!TextUtils.isEmpty(text)) {
//
//      try {
//
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//
//        char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
//            'f'};
//
//        byte[] md5Byte = md5.digest(text.getBytes("UTF8"));
//
//        StringBuffer sb = newStringBuffer();
//
//        for (inti = 0; i < md5Byte.length; i++) {
//
//          sb.append(HEX[(md5Byte[i] & 0xff) / 16]);
//
//          sb.append(HEX[(md5Byte[i] & 0xff) % 16]);
//
//        }
//
//        text = sb.toString();
//
//      } catch (NoSuchAlgorithmException e) {
//
//        returntext;
//
//      } catch (Exception e) {
//
//        return text;
//
//      }
//
//    }
//
//    return text;
//
//  }
}