package com.damian.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.damian.domain.Catalog;
import com.damian.domain.User;
import com.damian.service.MailService;
//import com.damian.service.util.CatalogGenerator;
import com.damian.web.rest.errors.InvalidPasswordException;
import com.damian.web.rest.vm.ManagedUserVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CatalogGeneratorResource {

    private final Logger log = LoggerFactory.getLogger(CatalogGeneratorResource.class);


//
//    @CrossOrigin
//    @RequestMapping(value = "/generatecatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<InputStreamResource> generateCatalog(@RequestBody Catalog catalog) throws IOException {
//
//
//
//
//        CatalogGenerator catalogGenerator = new CatalogGenerator();
//        ByteArrayInputStream bis = catalogGenerator.generateCatalog(catalog);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=catalog222.pdf");
//        new InputStreamResource(bis)  ;
//        return ResponseEntity
//            .ok()
//            .headers(headers)
//            .contentType(MediaType.APPLICATION_PDF)
//            .body(new InputStreamResource(bis));
//    }



}
