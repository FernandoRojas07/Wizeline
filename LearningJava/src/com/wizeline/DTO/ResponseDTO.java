package com.wizeline.DTO;

public class ResponseDTO {

    private String status;

    private String code;

    public String getStatus() {
        return status;
    }

    private ErrorDTO errors = new ErrorDTO();

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ErrorDTO getErrors() {
        return errors;
    }

    public void setErrors(ErrorDTO errors) {
        this.errors = errors;
    }
}
