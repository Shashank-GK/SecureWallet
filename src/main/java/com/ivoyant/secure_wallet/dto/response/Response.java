package com.ivoyant.secure_wallet.dto.response;

import lombok.Data;

import java.time.Instant;
@Data
public class Response {
    private int slNo;
    private String userUuid;
    private Instant createdAt;
    private String username;
    private String title;
    private String value;
    private String decryptedValue;
}
