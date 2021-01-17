package com.lendoapp.response;

/*
   @created by sukant 
   @created on on 16/10/20
   @project lendoapp
 */

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({ "status", "body" })
public class ApiResponseSkeleton {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode status = mapper.createObjectNode();
    JsonNode data = mapper.createObjectNode();

    public void setSuccess(Boolean success) {
        ((ObjectNode) status).put("success", success);
    }

    public void setMessage(String message) {
        ((ObjectNode) status).put("message", message);
    }

    public void setHttpCode(HttpStatus httpCode) {
        ((ObjectNode) status).put("httpCode", String.valueOf(httpCode.value()));
    }

    public JsonNode getStatus() {
        return status;
    }

    public JsonNode getData() {
        return data;
    }
}
