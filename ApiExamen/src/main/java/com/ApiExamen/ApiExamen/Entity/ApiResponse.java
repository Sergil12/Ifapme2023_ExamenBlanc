package com.ApiExamen.ApiExamen.Entity;

import java.util.StringJoiner;

public class ApiResponse {
    public boolean result;
    public Object data;
    public String error_code;

    public ApiResponse(boolean result, Object data, String error_code) {
        this.result = result;
        this.data = data;
        this.error_code = error_code;
    }

    public boolean isSuccess() {
        return this.result;
    }

    public static class Builder {
        public boolean result;
        public Object data;
        public String error_code;

        public Builder setResult(Boolean result) {
            this.result = result;
            return this;
        }

        public Builder setData(Object data) {
            this.data = data;
            return this;
        }

        public Builder setError_code(String error_code) {
            this.error_code = error_code;
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(result, data, error_code);
        }
    }

    public String toJson() {
        return new StringJoiner(", ", "{", "}")
                .add("\"result\": \"" + result + "\"")
                .add("\"data\": " + data)
                .add("\"error_code\": \"" + error_code + "\"")
                .toString();
    }
}
