package com.day.webview.eth;

import java.util.List;

public class EthResult {

    private String status;
    private String message;
    private List result;
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setResult(List result) {
        this.result = result;
    }
    public List getResult() {
        return result;
    }
}
