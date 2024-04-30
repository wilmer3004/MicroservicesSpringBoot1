package com.uncladavecode.orders_service.http.responses;

public record BaseResponse(String[] errorMenssage) {


    public boolean hasErrors() {
        return errorMenssage != null && errorMenssage.length > 0;
    }
}
