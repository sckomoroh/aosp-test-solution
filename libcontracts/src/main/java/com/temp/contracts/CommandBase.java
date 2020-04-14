package com.temp.contracts;

public class CommandBase {
    private String _errorMessage;
    private EStatus _status;
    private String _content;
    private ECommandType _type;

    public final EStatus getStatus() {
        return _status;
    }

    public final void setStatus(EStatus status) {
        _status = status;
    }

    public final String getErrorMessage() {
        return _errorMessage;
    }

    public final void setErrorMessage(String msg) {
        _errorMessage = msg;
    }

    public final String getContent() {
        return _content;
    }

    public final void setContent(String value) {
        _content = value;
    }

    public final ECommandType getCommandType() {
        return _type;
    }

    public final void setCommandType(ECommandType value) {
        _type = value;
    }
}
