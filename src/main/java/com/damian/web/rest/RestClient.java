package com.damian.web.rest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    private String serverURL = "http://51.68.153.88:8080/basketsextlist";
    private String serverStockURL = "http://51.68.153.88:8080/basket_ext_stock";
    //private String serverURL = "http://localhost:8080/extbaskets";
    private RestTemplate rest;
    private HttpHeaders headers;
    private HttpStatus status;

    public RestClient() {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Authorization", "Basic cGFzczpwYXNz");
    }

    public String get() {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(serverURL, HttpMethod.GET, requestEntity, String.class);

        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public String getStock() {
        HttpEntity<String> requestEntity = new HttpEntity<String>("", headers);
        ResponseEntity<String> responseEntity = rest.exchange(serverStockURL, HttpMethod.GET, requestEntity, String.class);

        this.setStatus(responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
