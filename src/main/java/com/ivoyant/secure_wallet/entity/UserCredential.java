package com.ivoyant.secure_wallet.entity;

import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Table("user_credentials")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential {
    @PrimaryKey
    @Column("sl_no")
    private int slNo;

    @Column("user_uuid")
    private String userUuid;

    @Column("time_stamp")
    private Instant createdAt;

    @Column("user_name")
    private String username;

    @Column("title")
    private String title;

    @Column("encrypted_value")
    private String value;

    @Transient
    private String decryptedValue; // Exposed to client but not stored in DB
}
