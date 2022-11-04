package com.nebula.nebula_resource.helper.api;

import java.util.Map;

public class ResultResponseMessage extends ResponseMessage{
    private Object results;

    public ResultResponseMessage(int httpStatus, String message, Object results){
        super(httpStatus, message);
        this.results = results;
    }

    public Object getResults() {
        return results;
    }

    public void setResults(Object results) {
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
