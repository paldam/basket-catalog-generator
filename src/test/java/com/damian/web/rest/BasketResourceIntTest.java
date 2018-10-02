package com.damian.web.rest;


import com.damian.BasketExtApp;
import com.damian.domain.Basket;
import com.damian.repository.BasketRepository;
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

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


import static com.damian.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BasketResource REST controller.
 *
 * @see BasketResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BasketExtApp.class)
public class BasketResourceIntTest {

    private static final String DEFAULT_BASKET_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BASKET_NAME = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_BASKET_PRICE = new BigDecimal(1);
    private static final BigDecimal UPDATED_BASKET_PRICE = new BigDecimal(2);

    private static final Integer DEFAULT_ORGIN_BASKET_ID = 1;
    private static final Integer UPDATED_ORGIN_BASKET_ID = 2;

    @Autowired
    private BasketRepository basketRepository;

    @Mock
    private BasketRepository basketRepositoryMock;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBasketMockMvc;

    private Basket basket;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final BasketResource basketResource = new BasketResource(basketRepository);
        this.restBasketMockMvc = MockMvcBuilders.standaloneSetup(basketResource)
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
    public static Basket createEntity(EntityManager em) {
        Basket basket = new Basket()
            .basketName(DEFAULT_BASKET_NAME)
            .basketPrice(DEFAULT_BASKET_PRICE)
            .orginBasketId(DEFAULT_ORGIN_BASKET_ID);
        return basket;
    }

    @Before
    public void initTest() {
        basket = createEntity(em);
    }

    @Test
    @Transactional
    public void createBasket() throws Exception {
        int databaseSizeBeforeCreate = basketRepository.findAll().size();

        // Create the Basket
        restBasketMockMvc.perform(post("/api/baskets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basket)))
            .andExpect(status().isCreated());

        // Validate the Basket in the database
        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeCreate + 1);
        Basket testBasket = basketList.get(basketList.size() - 1);
        assertThat(testBasket.getBasketName()).isEqualTo(DEFAULT_BASKET_NAME);
        assertThat(testBasket.getBasketPrice()).isEqualTo(DEFAULT_BASKET_PRICE);
        assertThat(testBasket.getOrginBasketId()).isEqualTo(DEFAULT_ORGIN_BASKET_ID);
    }

    @Test
    @Transactional
    public void createBasketWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = basketRepository.findAll().size();

        // Create the Basket with an existing ID
        basket.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBasketMockMvc.perform(post("/api/baskets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basket)))
            .andExpect(status().isBadRequest());

        // Validate the Basket in the database
        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkBasketNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = basketRepository.findAll().size();
        // set the field null
        basket.setBasketName(null);

        // Create the Basket, which fails.

        restBasketMockMvc.perform(post("/api/baskets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basket)))
            .andExpect(status().isBadRequest());

        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkBasketPriceIsRequired() throws Exception {
        int databaseSizeBeforeTest = basketRepository.findAll().size();
        // set the field null
        basket.setBasketPrice(null);

        // Create the Basket, which fails.

        restBasketMockMvc.perform(post("/api/baskets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basket)))
            .andExpect(status().isBadRequest());

        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOrginBasketIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = basketRepository.findAll().size();
        // set the field null
        basket.setOrginBasketId(null);

        // Create the Basket, which fails.

        restBasketMockMvc.perform(post("/api/baskets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basket)))
            .andExpect(status().isBadRequest());

        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllBaskets() throws Exception {
        // Initialize the database
        basketRepository.saveAndFlush(basket);

        // Get all the basketList
        restBasketMockMvc.perform(get("/api/baskets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(basket.getId().intValue())))
            .andExpect(jsonPath("$.[*].basketName").value(hasItem(DEFAULT_BASKET_NAME.toString())))
            .andExpect(jsonPath("$.[*].basketPrice").value(hasItem(DEFAULT_BASKET_PRICE.intValue())))
            .andExpect(jsonPath("$.[*].orginBasketId").value(hasItem(DEFAULT_ORGIN_BASKET_ID)));
    }

    public void getAllBasketsWithEagerRelationshipsIsEnabled() throws Exception {
        BasketResource basketResource = new BasketResource(basketRepositoryMock);
        when(basketRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        MockMvc restBasketMockMvc = MockMvcBuilders.standaloneSetup(basketResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBasketMockMvc.perform(get("/api/baskets?eagerload=true"))
        .andExpect(status().isOk());

        verify(basketRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    public void getAllBasketsWithEagerRelationshipsIsNotEnabled() throws Exception {
        BasketResource basketResource = new BasketResource(basketRepositoryMock);
            when(basketRepositoryMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));
            MockMvc restBasketMockMvc = MockMvcBuilders.standaloneSetup(basketResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();

        restBasketMockMvc.perform(get("/api/baskets?eagerload=true"))
        .andExpect(status().isOk());

            verify(basketRepositoryMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getBasket() throws Exception {
        // Initialize the database
        basketRepository.saveAndFlush(basket);

        // Get the basket
        restBasketMockMvc.perform(get("/api/baskets/{id}", basket.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(basket.getId().intValue()))
            .andExpect(jsonPath("$.basketName").value(DEFAULT_BASKET_NAME.toString()))
            .andExpect(jsonPath("$.basketPrice").value(DEFAULT_BASKET_PRICE.intValue()))
            .andExpect(jsonPath("$.orginBasketId").value(DEFAULT_ORGIN_BASKET_ID));
    }

    @Test
    @Transactional
    public void getNonExistingBasket() throws Exception {
        // Get the basket
        restBasketMockMvc.perform(get("/api/baskets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBasket() throws Exception {
        // Initialize the database
        basketRepository.saveAndFlush(basket);

        int databaseSizeBeforeUpdate = basketRepository.findAll().size();

        // Update the basket
        Basket updatedBasket = basketRepository.findById(basket.getId()).get();
        // Disconnect from session so that the updates on updatedBasket are not directly saved in db
        em.detach(updatedBasket);
        updatedBasket
            .basketName(UPDATED_BASKET_NAME)
            .basketPrice(UPDATED_BASKET_PRICE)
            .orginBasketId(UPDATED_ORGIN_BASKET_ID);

        restBasketMockMvc.perform(put("/api/baskets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedBasket)))
            .andExpect(status().isOk());

        // Validate the Basket in the database
        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeUpdate);
        Basket testBasket = basketList.get(basketList.size() - 1);
        assertThat(testBasket.getBasketName()).isEqualTo(UPDATED_BASKET_NAME);
        assertThat(testBasket.getBasketPrice()).isEqualTo(UPDATED_BASKET_PRICE);
        assertThat(testBasket.getOrginBasketId()).isEqualTo(UPDATED_ORGIN_BASKET_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingBasket() throws Exception {
        int databaseSizeBeforeUpdate = basketRepository.findAll().size();

        // Create the Basket

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBasketMockMvc.perform(put("/api/baskets")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(basket)))
            .andExpect(status().isBadRequest());

        // Validate the Basket in the database
        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBasket() throws Exception {
        // Initialize the database
        basketRepository.saveAndFlush(basket);

        int databaseSizeBeforeDelete = basketRepository.findAll().size();

        // Get the basket
        restBasketMockMvc.perform(delete("/api/baskets/{id}", basket.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Basket> basketList = basketRepository.findAll();
        assertThat(basketList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Basket.class);
        Basket basket1 = new Basket();
        basket1.setId(1L);
        Basket basket2 = new Basket();
        basket2.setId(basket1.getId());
        assertThat(basket1).isEqualTo(basket2);
        basket2.setId(2L);
        assertThat(basket1).isNotEqualTo(basket2);
        basket1.setId(null);
        assertThat(basket1).isNotEqualTo(basket2);
    }
}
