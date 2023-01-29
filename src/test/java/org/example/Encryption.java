package org.example;

import org.junit.jupiter.api.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Encryption {

    public String encryptString(String pwd) {
        String encryptedString = "";
        String secretKey = "SecretWord123", saltBytes = "IntegrationTest";
        try {
            byte[] secByte = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec iv = new IvParameterSpec(secByte);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), saltBytes.getBytes(), 65536, 256);
            SecretKey tempKey = factory.generateSecret(spec);
            SecretKeySpec secretkey = new SecretKeySpec(tempKey.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, iv);
            encryptedString = Base64.getEncoder().encodeToString(cipher.doFinal(pwd.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public String decryptString(String encryptedPwd) {
        String decryptedString = "";
        String secretKey = "SecretWord123", saltBytes = "IntegrationTest";
        try {
            byte[] secByte = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
            IvParameterSpec iv = new IvParameterSpec(secByte);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), saltBytes.getBytes(), 65536, 256);
            SecretKey tempKey = factory.generateSecret(spec);
            SecretKeySpec secretkey = new SecretKeySpec(tempKey.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretkey, iv);
            decryptedString = new String(cipher.doFinal(Base64.getDecoder().decode(encryptedPwd)));
            cipher.init(Cipher.ENCRYPT_MODE, secretkey, iv);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedString;
    }

    @Test
    public void testDecrypt() {
        final String TEST_STRING_FOR_ENCRYPT = "test#123456";
        final String TEST_STRING_FOR_DECRYPT = "cPcAZgsU96OpLHPfI6vwJA==";
        String encryptedString = encryptString(TEST_STRING_FOR_ENCRYPT);
        System.out.println("Encrypted string: " + "" + TEST_STRING_FOR_ENCRYPT + " ==> " + encryptedString);
        String decryptedString = decryptString(TEST_STRING_FOR_DECRYPT);
        System.out.println("Decrypted string: " + "" + TEST_STRING_FOR_DECRYPT + " ==> " + decryptedString);
        if (!TEST_STRING_FOR_ENCRYPT.equals(decryptedString)) {
            System.out.println("Encrypt error");
        }
        // Console output example:
        // Encrypted string: test#123456 ==> cPcAZgsU96OpLHPfI6vwJA==
        // Decrypted string: cPcAZgsU96OpLHPfI6vwJA== ==> test#123456
    }
}
