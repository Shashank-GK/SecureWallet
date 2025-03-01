package com.ivoyant.secure_wallet.repository;

import com.ivoyant.secure_wallet.entity.UserCredential;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.Optional;

public interface SecureWalletRepository {
    /**
     * Find user credential by userUuid and title. This method is used to retrieve the user credential.
     * @param userUuid
     * @param title
     * @return
     */
    Optional<UserCredential> findByUserUuidAndTitle(String userUuid, String title);

    /**
     * Find the maximum serial number. This method is used to find the maximum serial number.
     * @return
     */
    Integer findMaxSlNo();

    /**
     * Check if the title exists. This method is used to check if the title already exists.
     * @param userUuid
     * @param title
     * @return
     */
    Integer checkIfTitleExists(String userUuid, String title);

    /**
     * Save user credential. This method is used to save the user credential.
     * @param slNo, userUuid, createdAt, username, title, value
     */
    void insertUserCredential(int slNo, String userUuid, String createdAt, String username, String title, String value);

    /**
     * Get all user credentials. This method is used to retrieve all user credentials.
     * @return
     */
    List<UserCredential> getAllUserCredential();
}