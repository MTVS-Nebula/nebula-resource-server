package com.nebula.nebula_resource.helper.api;

import java.util.Map;

public class ResultResponseMessage extends ResponseMessage{
    private Map<String , Object> results;

    public ResultResponseMessage(int httpStatus, String message, Map<String, Object> results){
        super(httpStatus, message);
        this.results = results;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    public void setResults(Map<String, Object> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "ResultResponseMessage{" +
                "httpStatus=" + super.getHttpStatus() +
                "message=" + super.getMessage() +
                "results=" + results +
                '}';
    }
}
