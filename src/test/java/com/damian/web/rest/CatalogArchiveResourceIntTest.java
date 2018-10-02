package com.damian.web.rest;

import com.damian.BasketExtApp;

import com.damian.domain.CatalogArchive;
import com.damian.repository.CatalogArchiveRepository;
import com.damian.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


import static com.damian.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.damian.domain.enumeration.Theme;
/**
 * Test class for the CatalogArchiveResource REST controller.
 *
 * @see CatalogArchiveResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasketExtApp.class)
public class CatalogArchiveResourceIntTest {

    private static final String DEFAULT_CATALOG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FOR_WHO = "AAAAAAAAAA";
    private static final String UPDATED_FOR_WHO = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ASSISTANT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ASSISTANT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ASSISTANT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ASSISTANT_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_ASSISTANT_TEL = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_ASSISTANT_TEL = "BBBBBBBBBB";

    private static final String DEFAULT_CATALOG_ADDITIONAL_DESC = "AAAAAAAAAA";
    private static final String UPDATED_CATALOG_ADDITIONAL_DESC = "BBBBBBBBBB";

    private static final Theme DEFAULT_CATALOG_THEME = Theme.NIEBIESKI;
    private static final Theme UPDATED_CATALOG_THEME = Theme.CZERWONY;

    private static final byte[] DEFAULT_LOGO = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_LOGO = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_LOGO_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_LOGO_CONTENT_TYPE = "image/png";

    @Autowired
    private CatalogArchiveRepository catalogArchiveRepository;

    @Mock
    private CatalogArchiveRepository catalogArchiveRepositoryMock;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restCatalogArchiveMockMvc;

    private CatalogArchive catalogArchive;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final CatalogArchiveResource catalogArchiveResource = new CatalogArchiveResource(catalogArchiveRepository);
        this.restCatalogArchiveMockMvc = MockMvcBuilders.standaloneSetup(catalogArchiveResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CatalogArchive createEntity(EntityManager em) {
        CatalogArchive catalogArchive = new CatalogArchive()
            .catalogName(DEFAULT_CATALOG_NAME)
            .forWho(DEFAULT_FOR_WHO)
            .customerAssistantName(DEFAULT_CUSTOMER_ASSISTANT_NAME)
            .customerAssistantEmail(DEFAULT_CUSTOMER_ASSISTANT_EMAIL)
            .customerAssistantTel(DEFAULT_CUSTOMER_ASSISTANT_TEL)
            .catalogAdditionalDesc(DEFAULT_CATALOG_ADDITIONAL_DESC)
            .catalogTheme(DEFAULT_CATALOG_THEME)
            .logo(DEFAULT_LOGO)
            .logoContentType(DEFAULT_LOGO_CONTENT_TYPE);
        return catalogArchive;
    }

    @Before
    public void initTest() {
        catalogArchive = createEntity(em);
    }

    @Test
    @Transactional
    public void createCatalogArchive() throws Exception {
        int databaseSizeBeforeCreate = catalogArchiveRepository.findAll().size();

        // Create the CatalogArchive
        restCatalogArchiveMockMvc.perform(post("/api/catalog-archives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catalogArchive)))
            .andExpect(status().isCreated());

        // Validate the CatalogArchive in the database
        List<CatalogArchive> catalogArchiveList = catalogArchiveRepository.findAll();
        assertThat(catalogArchiveList).hasSize(databaseSizeBeforeCreate + 1);
        CatalogArchive testCatalogArchive = catalogArchiveList.get(catalogArchiveList.size() - 1);
        assertThat(testCatalogArchive.getCatalogName()).isEqualTo(DEFAULT_CATALOG_NAME);
        assertThat(testCatalogArchive.getForWho()).isEqualTo(DEFAULT_FOR_WHO);
        assertThat(testCatalogArchive.getCustomerAssistantName()).isEqualTo(DEFAULT_CUSTOMER_ASSISTANT_NAME);
        assertThat(testCatalogArchive.getCustomerAssistantEmail()).isEqualTo(DEFAULT_CUSTOMER_ASSISTANT_EMAIL);
        assertThat(testCatalogArchive.getCustomerAssistantTel()).isEqualTo(DEFAULT_CUSTOMER_ASSISTANT_TEL);
        assertThat(testCatalogArchive.getCatalogAdditionalDesc()).isEqualTo(DEFAULT_CATALOG_ADDITIONAL_DESC);
        assertThat(testCatalogArchive.getCatalogTheme()).isEqualTo(DEFAULT_CATALOG_THEME);
        assertThat(testCatalogArchive.getLogo()).isEqualTo(DEFAULT_LOGO);
        assertThat(testCatalogArchive.getLogoContentType()).isEqualTo(DEFAULT_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void createCatalogArchiveWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = catalogArchiveRepository.findAll().size();

        // Create the CatalogArchive with an existing ID
        catalogArchive.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCatalogArchiveMockMvc.perform(post("/api/catalog-archives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catalogArchive)))
            .andExpect(status().isBadRequest());

        // Validate the CatalogArchive in the database
        List<CatalogArchive> catalogArchiveList = catalogArchiveRepository.findAll();
        assertThat(catalogArchiveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkCatalogNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = catalogArchiveRepository.findAll().size();
        // set the field null
        catalogArchive.setCatalogName(null);

        // Create the CatalogArchive, which fails.

        restCatalogArchiveMockMvc.perform(post("/api/catalog-archives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catalogArchive)))
            .andExpect(status().isBadRequest());

        List<CatalogArchive> catalogArchiveList = catalogArchiveRepository.findAll();
        assertThat(catalogArchiveList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCatalogArchives() throws Exception {
        // Initialize the database
        catalogArchiveRepository.saveAndFlush(catalogArchive);

        // Get all the catalogArchiveList
        restCatalogArchiveMockMvc.perform(get("/api/catalog-archives?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(catalogArchive.getId().intValue())))
            .andExpect(jsonPath("$.[*].catalogName").value(hasItem(DEFAULT_CATALOG_NAME.toString())))
            .andExpect(jsonPath("$.[*].forWho").value(hasItem(DEFAULT_FOR_WHO.toString())))
            .andExpect(jsonPath("$.[*].customerAssistantName").value(hasItem(DEFAULT_CUSTOMER_ASSISTANT_NAME.toString())))
            .andExpect(jsonPath("$.[*].customerAssistantEmail").value(hasItem(DEFAULT_CUSTOMER_ASSISTANT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].customerAssistantTel").value(hasItem(DEFAULT_CUSTOMER_ASSISTANT_TEL.toString())))
            .andExpect(jsonPath("$.[*].catalogAdditionalDesc").value(hasItem(DEFAULT_CATALOG_ADDITIONAL_DESC.toString())))
            .andExpect(jsonPath("$.[*].catalogTheme").value(hasItem(DEFAULT_CATALOG_THEME.toString())))
            .andExpect(jsonPath("$.[*].logoContentType").value(hasItem(DEFAULT_LOGO_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].logo").value(hasItem(Base64Utils.encodeToString(DEFAULT_LOGO))));
    }
    
    public void getAllCatalogArchivesWithEagerRelationshipsIsEnabled() throws Exception {
        CatalogArchiveResource catalogArchiveResource = new CatalogArchiveResource(catalogArchiveRepositoryMock);
        when(catalogArchiveRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restCatalogArchiveMockMvc = MockMvcBuilders.standaloneSetup(catalogArchiveResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCatalogArchiveMockMvc.perform(get("/api/catalog-archives?eagerload=true"))
        .andExpect(status().isOk());

        verify(catalogArchiveRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllCatalogArchivesWithEagerRelationshipsIsNotEnabled() throws Exception {
        CatalogArchiveResource catalogArchiveResource = new CatalogArchiveResource(catalogArchiveRepositoryMock);
            when(catalogArchiveRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restCatalogArchiveMockMvc = MockMvcBuilders.standaloneSetup(catalogArchiveResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restCatalogArchiveMockMvc.perform(get("/api/catalog-archives?eagerload=true"))
        .andExpect(status().isOk());

            verify(catalogArchiveRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getCatalogArchive() throws Exception {
        // Initialize the database
        catalogArchiveRepository.saveAndFlush(catalogArchive);

        // Get the catalogArchive
        restCatalogArchiveMockMvc.perform(get("/api/catalog-archives/{id}", catalogArchive.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(catalogArchive.getId().intValue()))
            .andExpect(jsonPath("$.catalogName").value(DEFAULT_CATALOG_NAME.toString()))
            .andExpect(jsonPath("$.forWho").value(DEFAULT_FOR_WHO.toString()))
            .andExpect(jsonPath("$.customerAssistantName").value(DEFAULT_CUSTOMER_ASSISTANT_NAME.toString()))
            .andExpect(jsonPath("$.customerAssistantEmail").value(DEFAULT_CUSTOMER_ASSISTANT_EMAIL.toString()))
            .andExpect(jsonPath("$.customerAssistantTel").value(DEFAULT_CUSTOMER_ASSISTANT_TEL.toString()))
            .andExpect(jsonPath("$.catalogAdditionalDesc").value(DEFAULT_CATALOG_ADDITIONAL_DESC.toString()))
            .andExpect(jsonPath("$.catalogTheme").value(DEFAULT_CATALOG_THEME.toString()))
            .andExpect(jsonPath("$.logoContentType").value(DEFAULT_LOGO_CONTENT_TYPE))
            .andExpect(jsonPath("$.logo").value(Base64Utils.encodeToString(DEFAULT_LOGO)));
    }

    @Test
    @Transactional
    public void getNonExistingCatalogArchive() throws Exception {
        // Get the catalogArchive
        restCatalogArchiveMockMvc.perform(get("/api/catalog-archives/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCatalogArchive() throws Exception {
        // Initialize the database
        catalogArchiveRepository.saveAndFlush(catalogArchive);

        int databaseSizeBeforeUpdate = catalogArchiveRepository.findAll().size();

        // Update the catalogArchive
        CatalogArchive updatedCatalogArchive = catalogArchiveRepository.findById(catalogArchive.getId()).get();
        // Disconnect from session so that the updates on updatedCatalogArchive are not directly saved in db
        em.detach(updatedCatalogArchive);
        updatedCatalogArchive
            .catalogName(UPDATED_CATALOG_NAME)
            .forWho(UPDATED_FOR_WHO)
            .customerAssistantName(UPDATED_CUSTOMER_ASSISTANT_NAME)
            .customerAssistantEmail(UPDATED_CUSTOMER_ASSISTANT_EMAIL)
            .customerAssistantTel(UPDATED_CUSTOMER_ASSISTANT_TEL)
            .catalogAdditionalDesc(UPDATED_CATALOG_ADDITIONAL_DESC)
            .catalogTheme(UPDATED_CATALOG_THEME)
            .logo(UPDATED_LOGO)
            .logoContentType(UPDATED_LOGO_CONTENT_TYPE);

        restCatalogArchiveMockMvc.perform(put("/api/catalog-archives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedCatalogArchive)))
            .andExpect(status().isOk());

        // Validate the CatalogArchive in the database
        List<CatalogArchive> catalogArchiveList = catalogArchiveRepository.findAll();
        assertThat(catalogArchiveList).hasSize(databaseSizeBeforeUpdate);
        CatalogArchive testCatalogArchive = catalogArchiveList.get(catalogArchiveList.size() - 1);
        assertThat(testCatalogArchive.getCatalogName()).isEqualTo(UPDATED_CATALOG_NAME);
        assertThat(testCatalogArchive.getForWho()).isEqualTo(UPDATED_FOR_WHO);
        assertThat(testCatalogArchive.getCustomerAssistantName()).isEqualTo(UPDATED_CUSTOMER_ASSISTANT_NAME);
        assertThat(testCatalogArchive.getCustomerAssistantEmail()).isEqualTo(UPDATED_CUSTOMER_ASSISTANT_EMAIL);
        assertThat(testCatalogArchive.getCustomerAssistantTel()).isEqualTo(UPDATED_CUSTOMER_ASSISTANT_TEL);
        assertThat(testCatalogArchive.getCatalogAdditionalDesc()).isEqualTo(UPDATED_CATALOG_ADDITIONAL_DESC);
        assertThat(testCatalogArchive.getCatalogTheme()).isEqualTo(UPDATED_CATALOG_THEME);
        assertThat(testCatalogArchive.getLogo()).isEqualTo(UPDATED_LOGO);
        assertThat(testCatalogArchive.getLogoContentType()).isEqualTo(UPDATED_LOGO_CONTENT_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingCatalogArchive() throws Exception {
        int databaseSizeBeforeUpdate = catalogArchiveRepository.findAll().size();

        // Create the CatalogArchive

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCatalogArchiveMockMvc.perform(put("/api/catalog-archives")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(catalogArchive)))
            .andExpect(status().isBadRequest());

        // Validate the CatalogArchive in the database
        List<CatalogArchive> catalogArchiveList = catalogArchiveRepository.findAll();
        assertThat(catalogArchiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCatalogArchive() throws Exception {
        // Initialize the database
        catalogArchiveRepository.saveAndFlush(catalogArchive);

        int databaseSizeBeforeDelete = catalogArchiveRepository.findAll().size();

        // Get the catalogArchive
        restCatalogArchiveMockMvc.perform(delete("/api/catalog-archives/{id}", catalogArchive.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<CatalogArchive> catalogArchiveList = catalogArchiveRepository.findAll();
        assertThat(catalogArchiveList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CatalogArchive.class);
        CatalogArchive catalogArchive1 = new CatalogArchive();
        catalogArchive1.setId(1L);
        CatalogArchive catalogArchive2 = new CatalogArchive();
        catalogArchive2.setId(catalogArchive1.getId());
        assertThat(catalogArchive1).isEqualTo(catalogArchive2);
        catalogArchive2.setId(2L);
        assertThat(catalogArchive1).isNotEqualTo(catalogArchive2);
        catalogArchive1.setId(null);
        assertThat(catalogArchive1).isNotEqualTo(catalogArchive2);
    }
}
