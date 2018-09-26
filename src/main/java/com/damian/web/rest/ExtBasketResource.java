package com.damian.web.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExtBasketResource {

    private RestClient restClient;

    public ExtBasketResource(RestClient restClient) {
        this.restClient = restClient;
    }


    @CrossOrigin
    @GetMapping("/extbaskets")
    ResponseEntity<String> getBaskets(){

        return new ResponseEntity<String>(restClient.get(), HttpStatus.OK);
    }


}
