package com.ecnu.g03.pethospital.exception;

/**
 * @author Shen Lei
 * @date 2021/4/25 23:43
 */
public class AuthenticationException extends RuntimeException {

    public AuthenticationException(String message) {
        super(message);
    }

}
