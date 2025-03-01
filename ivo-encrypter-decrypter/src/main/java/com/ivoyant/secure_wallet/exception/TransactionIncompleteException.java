package com.ivoyant.secure_wallet.exception;

public class TransactionIncompleteException extends RuntimeException{
    public TransactionIncompleteException(String str){
        super(str);
    }
}
