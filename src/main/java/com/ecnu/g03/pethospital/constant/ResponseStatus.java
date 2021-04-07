package com.ecnu.g03.pethospital.constant;

/**
 * @author Shen Lei
 * @date 2021/3/28 22:43
 */
public enum ResponseStatus {

    /**
     * success
     */
    SUCCESS("SUCCESS"),
    /**
     * no data
     */
    NO_DATA("NO_DATA"),
    /**
     * database error
     */
    DATABASE_ERROR("DATABASE_ERROR"),
    /**
     * unknown error
     */
    UNKNOWN_ERROR("UNKNOWN_ERROR"),
    /**
     * a user is not allowed to use certain interface
     */
    AUTHORIZATION_ERROR("AUTHORIZATION_ERROR"),
    /**
     * request body or param is invalid
     */
    BAD_REQUEST("BAD_REQUEST");

    private String description;

    ResponseStatus(String description) {
        this.description = description;
    }

    public static ResponseStatus fromString(String description) {
        for (ResponseStatus responseStatus : ResponseStatus.values()) {
            if (responseStatus.description.equals(description)) {
                return responseStatus;
            }
        }
        throw new IllegalArgumentException("No description " + description + " of enum type ResponseStatus");
    }

}
