package com.spring.Service.Utilities;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

public class EncryptationUtility {

    private IvParameterSpec generateIv(){
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }

    private SecretKeySpec getSecretKey(){
        String keyString = System.getenv("SECRET_KEY");

        // Ensure the key length is valid for AES
        if (keyString == null || (keyString.length() != 16 && keyString.length() != 24 && keyString.length() != 32)) {
            throw new IllegalArgumentException("Invalid key length. Key must be 16, 24, or 32 bytes long.");
        }

        // Convert the key to bytes using a consistent character encoding
        byte[] key = keyString.getBytes(StandardCharsets.UTF_8);

        // Create the SecretKeySpec using the AES algorithm
        return new SecretKeySpec(key, "AES");
    }

    private Cipher getCipher() throws Exception{
        return Cipher.getInstance("AES/CBC/PKCS5Padding");
    }

    private String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data){
        return Base64.getDecoder().decode(data);
    }

    public String encrypt(String data) throws Exception {

        Cipher cipher = this.getCipher();
        IvParameterSpec iv = generateIv();
        cipher.init(Cipher.ENCRYPT_MODE, this.getSecretKey(), iv);

        byte[] encryptedData = cipher.doFinal(data.getBytes());

        byte[] encryptedDataWithIv = new byte[iv.getIV().length + encryptedData.length];

        System.arraycopy(iv.getIV(), 0, encryptedDataWithIv, 0, iv.getIV().length);
        System.arraycopy(encryptedData, 0, encryptedDataWithIv, iv.getIV().length, encryptedData.length);

        return this.encode(encryptedDataWithIv);
    }

    public String decrypt(String encryptedData) throws Exception {

        Cipher cipher = this.getCipher();

        byte[] encryptedDataWithIv = decode(encryptedData);

        byte[] iv = new byte[16];
        byte[] decryptedData = new byte[encryptedDataWithIv.length - iv.length];

        System.arraycopy(encryptedDataWithIv, 0, iv, 0, iv.length);
        System.arraycopy(encryptedDataWithIv, iv.length, decryptedData, 0, decryptedData.length);

        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        cipher.init(Cipher.DECRYPT_MODE, this.getSecretKey(), ivParameterSpec);

        return new String(cipher.doFinal(decryptedData));
    }

}
