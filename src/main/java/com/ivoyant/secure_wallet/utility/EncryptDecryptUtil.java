package com.ivoyant.secure_wallet.utility;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
@Slf4j
public class EncryptDecryptUtil {

    @Value("${encryption.secret-key}")
    private String secretKey;

    private static final String ALGORITHM = "AES";
    private SecretKeySpec keySpec;

    @PostConstruct
    public void init() {
        keySpec = new SecretKeySpec(secretKey.getBytes(), ALGORITHM);
    }

    /**
     * Encrypts the given value.
     *
     * @param value The value to encrypt.
     * @return The encrypted value.
     */
    public String encrypt(String value) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
        } catch (Exception e) {
            log.error("Error encrypting value", e);
            throw new RuntimeException("Encryption failed");
        }
    }

    /**
     * Decrypts the given encrypted value.
     *
     * @param encryptedValue The value to decrypt.
     * @return The decrypted value.
     */
    public String decrypt(String encryptedValue) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            if (encryptedValue == null) {
                throw new IllegalArgumentException("Input string to decrypt cannot be null");
            }

            return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedValue)));
        } catch (Exception e) {
            log.error("Error decrypting value", e);
            throw new RuntimeException("Decryption failed");
        }
    }
}
