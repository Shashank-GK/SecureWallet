package com.ivoyant.secure_wallet.repository.impl;

import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.ivoyant.secure_wallet.constants.StringConstants;
import com.ivoyant.secure_wallet.entity.UserCredential;
import com.ivoyant.secure_wallet.repository.SecureWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public class SecureWalletRepositoryImpl implements SecureWalletRepository {

    @Autowired
    private CassandraTemplate cassandraTemplate;

    @Autowired
    private StringConstants stringConstants;

    /**
     * Find user credential by userUuid and title. This method is used to retrieve the user credential.
     * @param userUuid
     * @param title
     * @return
     */
    @Override
    public Optional<UserCredential> findByUserUuidAndTitle(String userUuid, String title) {
        String query = stringConstants.getFindByUuidAndTitle();
        SimpleStatement statement = SimpleStatement.builder(query)
                .addPositionalValue(userUuid)
                .addPositionalValue(title)
                .build();

        UserCredential result = cassandraTemplate.selectOne(statement, UserCredential.class);
        return Optional.ofNullable(result);
    }

    /**
     * Find the maximum serial number. This method is used to find the maximum serial number.
     * @return
     */
    @Override
    public Integer findMaxSlNo() {
        String query = stringConstants.getFindMaxSlNo();
        SimpleStatement statement = SimpleStatement.builder(query).build();

        Integer result = cassandraTemplate.selectOne(statement, Integer.class);
        return result != null ? result : 0;
    }

    /**
     * Check if the title exists. This method is used to check if the title already exists.
     * @param userUuid
     * @param title
     * @return
     */
    @Override
    public Integer checkIfTitleExists(String userUuid, String title) {
        String query = stringConstants.getCheckUniqueTitle();
        SimpleStatement statement = SimpleStatement.builder(query)
                .addPositionalValue(userUuid)
                .addPositionalValue(title)
                .build();

        Long count = cassandraTemplate.selectOne(statement, Long.class);
        return count != null ? count.intValue() : 0;
    }

    /**
     * Insert user credential. This method is used to insert the user credential.
     * @param slNo
     * @param userUuid
     * @param createdAt
     * @param username
     * @param title
     * @param value
     */
    @Override
    public void insertUserCredential(int slNo, String userUuid, String createdAt, String username, String title, String value) {
        String query = stringConstants.getSaveUserCredientials();

        SimpleStatement statement = SimpleStatement.builder(query)
                .addPositionalValue(slNo)
                .addPositionalValue(userUuid)
                .addPositionalValue(Instant.parse(createdAt)) // Ensure `createdAt` is in correct format
                .addPositionalValue(username)
                .addPositionalValue(title)
                .addPositionalValue(value)
                .build();

        cassandraTemplate.execute(statement);
    }

    /**
     * Get all user credentials. This method is used to retrieve all user credentials.
     * @return
     */
    @Override
    public List<UserCredential> getAllUserCredential() {
        String query = stringConstants.getGetAllUserCredential();
        SimpleStatement statement = SimpleStatement.builder(query).build();
        return cassandraTemplate.select(statement, UserCredential.class);
    }
}