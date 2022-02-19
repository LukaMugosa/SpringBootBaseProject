package me.amplitudo.stanley.web.errors;

public enum ExceptionErrors {

    UNAUTHORIZED("E100", "User is not authorized to access this resource."),
    USER_DOES_NOT_EXIST("E101", "User with given credentials does not exist."),
    SERVER_ERROR("E102", "Server error."),
    INVALID_CREDENTIALS("E103", "Invalid credentials."),
    ENTITY_NOT_FOUND("E104", "Entity not found"),
    EMAIL_EXISTS("E105", "User with this email already exists"),
    MALFORMED_DATA("E106", "Sent data is malformed");

    private final String code;
    private final String description;

    ExceptionErrors(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
