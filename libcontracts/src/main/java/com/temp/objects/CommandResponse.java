package com.temp.objects;

public class CommandResponse {
    public final String Response;
    public final boolean IsSuccess;

    public CommandResponse(String response, boolean isSuccess) {
        Response = response;
        IsSuccess = isSuccess;
    }
}
