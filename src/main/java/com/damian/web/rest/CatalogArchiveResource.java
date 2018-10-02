package com.damian.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.damian.domain.CatalogArchive;
import com.damian.repository.CatalogArchiveRepository;
import com.damian.web.rest.errors.BadRequestAlertException;
import com.damian.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing CatalogArchive.
 */
@RestController
@RequestMapping("/api")
public class CatalogArchiveResource {

    private final Logger log = LoggerFactory.getLogger(CatalogArchiveResource.class);

    private static final String ENTITY_NAME = "catalogArchive";

    private final CatalogArchiveRepository catalogArchiveRepository;

    public CatalogArchiveResource(CatalogArchiveRepository catalogArchiveRepository) {
        this.catalogArchiveRepository = catalogArchiveRepository;
    }

    /**
     * POST  /catalog-archives : Create a new catalogArchive.
     *
     * @param catalogArchive the catalogArchive to create
     * @return the ResponseEntity with status 201 (Created) and with body the new catalogArchive, or with status 400 (Bad Request) if the catalogArchive has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/catalog-archives")
    @Timed
    public ResponseEntity<CatalogArchive> createCatalogArchive(@Valid @RequestBody CatalogArchive catalogArchive) throws URISyntaxException {
        log.debug("REST request to save CatalogArchive : {}", catalogArchive);
        if (catalogArchive.getId() != null) {
            throw new BadRequestAlertException("A new catalogArchive cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CatalogArchive result = catalogArchiveRepository.save(catalogArchive);
        return ResponseEntity.created(new URI("/api/catalog-archives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /catalog-archives : Updates an existing catalogArchive.
     *
     * @param catalogArchive the catalogArchive to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated catalogArchive,
     * or with status 400 (Bad Request) if the catalogArchive is not valid,
     * or with status 500 (Internal Server Error) if the catalogArchive couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/catalog-archives")
    @Timed
    public ResponseEntity<CatalogArchive> updateCatalogArchive(@Valid @RequestBody CatalogArchive catalogArchive) throws URISyntaxException {
        log.debug("REST request to update CatalogArchive : {}", catalogArchive);
        if (catalogArchive.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CatalogArchive result = catalogArchiveRepository.save(catalogArchive);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, catalogArchive.getId().toString()))
            .body(result);
    }

    /**
     * GET  /catalog-archives : get all the catalogArchives.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many)
     * @return the ResponseEntity with status 200 (OK) and the list of catalogArchives in body
     */
    @GetMapping("/catalog-archives")
    @Timed
    public List<CatalogArchive> getAllCatalogArchives(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all CatalogArchives");
        return catalogArchiveRepository.findAllWithEagerRelationships();
    }

    /**
     * GET  /catalog-archives/:id : get the "id" catalogArchive.
     *
     * @param id the id of the catalogArchive to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the catalogArchive, or with status 404 (Not Found)
     */
    @GetMapping("/catalog-archives/{id}")
    @Timed
    public ResponseEntity<CatalogArchive> getCatalogArchive(@PathVariable Long id) {
        log.debug("REST request to get CatalogArchive : {}", id);
        Optional<CatalogArchive> catalogArchive = catalogArchiveRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(catalogArchive);
    }

    /**
     * DELETE  /catalog-archives/:id : delete the "id" catalogArchive.
     *
     * @param id the id of the catalogArchive to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/catalog-archives/{id}")
    @Timed
    public ResponseEntity<Void> deleteCatalogArchive(@PathVariable Long id) {
        log.debug("REST request to delete CatalogArchive : {}", id);

        catalogArchiveRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
