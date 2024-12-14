package vn.techzen.academy_pnv_12.Exception;

public enum ErrorCode {
    EMPLOYEE_NOT_FOUND("Employee not found"),
    INVALID_PHONE("Invalid phone number"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
