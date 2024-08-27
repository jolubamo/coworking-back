package com.coworking.security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EncriptacionTokenAux {
    private static final String ALGORITHM = "AES";
    private static final int KEY_SIZE = 128;

    public static byte[] encrypt(String data, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(hexToBytes(secretKey),ALGORITHM) );
        return cipher.doFinal(data.getBytes());
    }

    public static String decrypt(String encryptedData, String secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(hexToBytes(secretKey),ALGORITHM) );
        byte[] decryptedData = cipher.doFinal(hexToBytes(encryptedData));
        return new String(decryptedData);
    }


    public static byte[] hexToBytes(String hexString) {
        int len = hexString.length();
        byte[] bytes = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            bytes[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4)
                    + Character.digit(hexString.charAt(i + 1), 16));
        }
        return bytes;
    }




}
