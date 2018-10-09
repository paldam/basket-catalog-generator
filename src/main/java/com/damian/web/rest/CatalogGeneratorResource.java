package com.damian.web.rest;

//import com.damian.service.util.CatalogGenerator;
import com.damian.domain.CatalogArchive;
import com.damian.repository.CatalogArchiveRepository;
import com.damian.service.CatalogGeneratorService;
import com.damian.service.util.CatalogGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CatalogGeneratorResource {

    private final Logger log = LoggerFactory.getLogger(CatalogGeneratorResource.class);
    private CatalogArchiveRepository catalogArchiveRepository;
    private CatalogGeneratorService catalogGeneratorService;

    public CatalogGeneratorResource(CatalogArchiveRepository catalogArchiveRepository, CatalogGeneratorService catalogGeneratorService) {
        this.catalogArchiveRepository = catalogArchiveRepository;
        this.catalogGeneratorService = catalogGeneratorService;
    }

    @CrossOrigin
    @RequestMapping(value = "/generatecatalog", method = RequestMethod.POST, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateCatalog(@RequestBody CatalogArchive catalog) throws IOException {


        catalogGeneratorService.generateCatalog(catalog);

        CatalogGenerator catalogGenerator = new CatalogGenerator();
        ByteArrayInputStream bis = catalogGenerator.generateCatalog(catalog);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=catalog222.pdf");
        new InputStreamResource(bis)  ;
        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));


    }

    @CrossOrigin
    @RequestMapping(value = "/regeneratecatalog/{catalogId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> reGenerateCatalog(@PathVariable Long catalogId) throws IOException {

        log.error("WWWWWWWWWWWWWWWWWWWWWWW" + catalogId);
        Optional<CatalogArchive> ca = catalogArchiveRepository.findOneWithEagerRelationships(catalogId);

         ca.get();

        CatalogGenerator catalogGenerator = new CatalogGenerator();
        ByteArrayInputStream bis = catalogGenerator.generateCatalog(ca.get());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=catalog222.pdf");
        new InputStreamResource(bis)  ;
        return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));


    }




}
