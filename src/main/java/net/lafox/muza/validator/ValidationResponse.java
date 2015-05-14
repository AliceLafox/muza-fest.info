package net.lafox.muza.validator;


import org.springframework.validation.ObjectError;

import java.util.List;

@SuppressWarnings("unused")
public class ValidationResponse {
    private String status;
    private List<ObjectError> errorMessageList;

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public List<ObjectError> getErrorMessageList() {
        return errorMessageList;
    }

    public void setErrorMessageList(List<ObjectError> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }
}
