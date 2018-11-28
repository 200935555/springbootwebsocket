package com.study.springboot.domain;

public class UserResponse {
    private String responseMessage;
    public UserResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage() {
        return responseMessage;
    }
}
