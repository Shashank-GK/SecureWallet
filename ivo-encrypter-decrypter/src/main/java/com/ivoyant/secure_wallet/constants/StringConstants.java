package com.ivoyant.secure_wallet.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StringConstants {

    @Value("${query.findByUserUuidAndTitle}")
    private String findByUuidAndTitle;

    @Value("${query.findMaxSlNo}")
    private String findMaxSlNo;

    @Value("${query.checkExistingTitle}")
    private String checkUniqueTitle;

    @Value("${query.saveUserCrediential}")
    private String saveUserCredientials;

    @Value("${query.getAllUserCredential}")
    private String getAllUserCredential;

    public String getFindByUuidAndTitle() {
        return findByUuidAndTitle;
    }

    public String getFindMaxSlNo() {
        return findMaxSlNo;
    }

    public String getCheckUniqueTitle() {
        return checkUniqueTitle;
    }

    public String getSaveUserCredientials() {
        return saveUserCredientials;
    }

    public String getGetAllUserCredential() {
        return getAllUserCredential;
    }
}