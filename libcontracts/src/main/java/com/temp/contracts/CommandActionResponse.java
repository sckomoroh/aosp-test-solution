package com.temp.contracts;

public class CommandActionResponse  {
    private String _response;
    private boolean _isSuccess;

    public String getResponse() {
        return _response;
    }

    public void setResponse(String value) {
        _response = value;
    }

    public boolean isSuccess() {
        return _isSuccess;
    }

    public void setSuccess(boolean value) {
        _isSuccess = value;
    }
}
