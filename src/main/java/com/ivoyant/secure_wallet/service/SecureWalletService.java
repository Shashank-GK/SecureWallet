package com.ivoyant.secure_wallet.service;

import com.ivoyant.secure_wallet.dto.request.Request;
import com.ivoyant.secure_wallet.dto.response.Response;
import com.ivoyant.secure_wallet.entity.UserCredential;
import com.ivoyant.secure_wallet.exception.ResourceNotFoundException;
import com.ivoyant.secure_wallet.exception.TransactionIncompleteException;
import com.ivoyant.secure_wallet.repository.SecureWalletRepository;
import com.ivoyant.secure_wallet.utility.EncryptDecryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing user credentials.
 */
@Service
@Slf4j
public class SecureWalletService {

    @Autowired
    private SecureWalletRepository secureWalletRepository;

    @Autowired
    private EncryptDecryptUtil encryptDecryptUtil;

    /**
     * Adds a new user credential after encrypting the value.
     *
     * @param request  The request containing user details.
     * @param userName The user's name.
     * @param userUuid The unique identifier for the user.
     */
    public void addUserCredential(Request request, String userName, String userUuid) {
        log.info("Storing encrypted user credentials for user: {}", userName);

        try {
            Integer titleCount = secureWalletRepository.checkIfTitleExists(userUuid, request.getTitle());
            if (titleCount != null && titleCount > 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Title already exists. Please choose a unique title.");
            }
            UserCredential userCredential = new UserCredential();
            userCredential.setUserUuid(userUuid);
            userCredential.setCreatedAt(Instant.now());
            userCredential.setUsername(userName);
            userCredential.setTitle(request.getTitle());
            userCredential.setValue(encryptDecryptUtil.encrypt(request.getValue())); // Encrypt once

            Integer maxSlNo = 0;
            try {
                maxSlNo = secureWalletRepository.findMaxSlNo();
                userCredential.setSlNo((maxSlNo == null) ? 1 : maxSlNo + 1);
            } catch (Exception e) {
                log.error("Error fetching max slNo: {}", e.getMessage(), e);
                userCredential.setSlNo(1);
            }
            // Insert into the database
            secureWalletRepository.insertUserCredential(
                    userCredential.getSlNo(),
                    userCredential.getUserUuid(),
                    userCredential.getCreatedAt().toString(),
                    userCredential.getUsername(),
                    userCredential.getTitle(),
                    userCredential.getValue()
            );
            log.info("User credentials stored successfully for userUuid: {}", userUuid);
        } catch (RuntimeException e) {
            log.error("Error saving user credentials: {}", e.getMessage(), e);
            throw new TransactionIncompleteException("Failed to add user credentials: " + e.getMessage());
        }
    }


    /**
     * Retrieves a decrypted user credential based on user UUID and title.
     *
     * @param userUuid The unique identifier for the user.
     * @param title    The title of the credential.
     * @return UserCredential object containing decrypted values.
     */
    public Response getUserCredential(String userUuid, String title) {
        try {
            log.info("Fetching User Credentials with userUuid: {}, title: {}", userUuid, title);
            Optional<UserCredential> existingCredential = secureWalletRepository.findByUserUuidAndTitle(userUuid, title);

            if (existingCredential.isPresent()) {
                UserCredential userCredential = existingCredential.get();

                log.info("UserCredential Retrieved: slNo={}, userUuid={}, title={}, encryptedValue={}",
                        userCredential.getSlNo(), userCredential.getUserUuid(), userCredential.getTitle(), userCredential.getValue());

                if (userCredential.getValue() == null) {
                    log.error("Encrypted value is null for userUuid: {}, title: {}", userUuid, title);
                }

                String decryptedValue = encryptDecryptUtil.decrypt(userCredential.getValue());

                Response response = new Response();
                response.setSlNo(userCredential.getSlNo());
                response.setUsername(userCredential.getUsername());
                response.setUserUuid(userCredential.getUserUuid());
                response.setCreatedAt(userCredential.getCreatedAt());
                response.setValue(userCredential.getValue());
                response.setDecryptedValue(decryptedValue);
                response.setTitle(userCredential.getTitle());

                log.info("Response Object Created: {}", response);
                return response;
            } else {
                throw new ResourceNotFoundException("User credentials not found for userUuid: " + userUuid + " and title: " + title);
            }

        } catch (Exception e) {
            log.error("Error retrieving user credentials: {}", e.getMessage(), e);
            throw new TransactionIncompleteException("Failed to retrieve user credentials: " + e.getMessage());
        }
    }

    /**
     * Retrieves all stored user credentials.
     *
     * @return List of UserCredential objects with decrypted values.
     */
    public List<Response> getAllUserCredentialList() {
        try {
            log.info("Fetching all User Credentials");
            List<UserCredential> credentials = secureWalletRepository.getAllUserCredential();
            if (credentials.isEmpty()) {
                throw new ResourceNotFoundException("No user credentials found");
            }

            List<Response> responseList = new ArrayList<>();
            for (UserCredential userCredential : credentials) {
                //String decryptedValue = encryptDecryptUtil.decrypt(userCredential.getValue());

                Response response = new Response();
                response.setSlNo(userCredential.getSlNo());
                response.setUsername(userCredential.getUsername());
                response.setUserUuid(userCredential.getUserUuid());
                response.setCreatedAt(userCredential.getCreatedAt());
                response.setValue(userCredential.getValue());
                response.setDecryptedValue(null);
                response.setTitle(userCredential.getTitle());

                responseList.add(response);
            }
            return responseList;
        } catch (Exception e) {
            log.error("Error occurred while fetching all user credentials: {}", e.getMessage(), e);
            throw new TransactionIncompleteException("Failed to retrieve user credentials: " + e.getMessage());
        }
    }

}