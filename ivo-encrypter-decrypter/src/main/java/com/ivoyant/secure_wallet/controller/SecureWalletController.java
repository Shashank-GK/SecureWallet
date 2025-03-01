package com.ivoyant.secure_wallet.controller;

import com.ivoyant.secure_wallet.dto.request.Request;
import com.ivoyant.secure_wallet.dto.response.Response;
import com.ivoyant.secure_wallet.entity.UserCredential;
import com.ivoyant.secure_wallet.service.SecureWalletService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user-credentials")
public class SecureWalletController {

    @Autowired
    private SecureWalletService secureWalletService;

    @Operation(summary = "Store encrypted user credentials")
    @PostMapping("/add")
    public ResponseEntity<String> addUserCredential(
            @Valid @RequestBody Request request,
            @RequestHeader(value = "x-user-name") String userName,
            @RequestHeader(value = "x-user-uuid") String userUuid) {
        secureWalletService.addUserCredential(request, userName, userUuid);
        return ResponseEntity.ok().body("User Credentials added successfully with userUuid: " + userUuid + " and title: " + request.getTitle());
    }

    @Operation(summary = "Retrieve decrypted user credentials by userUuid and title")
    @GetMapping("/search/{userUuid}/{title}")
    public ResponseEntity<Response> getUserCredential(@PathVariable String userUuid, @PathVariable String title) {
        Response credential = secureWalletService.getUserCredential(userUuid, title);
        return ResponseEntity.status(HttpStatus.OK).body(credential);
    }

    @Operation(summary = "Retrieve all user credentials")
    @GetMapping("/list")
    public ResponseEntity<List<Response>> getAllUserCredentials() {
        List<Response> userCredentials = secureWalletService.getAllUserCredentialList();
        return ResponseEntity.status(HttpStatus.OK).body(userCredentials);
    }
}
