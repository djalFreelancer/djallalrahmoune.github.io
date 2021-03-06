package com.example.demo.controller;

import com.example.demo.domain.Product;
import com.example.demo.service.ProductService;


import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);//init mock and controller
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }


    @Test
    public void testList() throws Exception{
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        when(productService.listAll()).thenReturn((List) products);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/list"))
                .andExpect(model().attribute("products", hasSize(2)));

    }

    @Test
    public void testShow() throws Exception{

        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/show/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/show"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));

    }

    @Test
    public void testEdit() throws Exception{

        Integer id = 1;

        when(productService.getById(id)).thenReturn(new Product());

        mockMvc.perform(get("/product/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));

    }

    @Test
    public void testNewProduct() throws Exception{

        Integer id = 1;

        verifyZeroInteractions(productService);

        mockMvc.perform(get("/product/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", instanceOf(Product.class)));

    }

    @Test
    public void testSaveOrUpdate() throws Exception{
        Integer id = 1;
        String descriptyion = "Test Description";
        BigDecimal price =  new BigDecimal("12.00");
        String imageUrl = "example.com";

        Product returnProduct = new Product();
        returnProduct.setId(id);
        returnProduct.setDescription(descriptyion);
        returnProduct.setPrice(price);
        returnProduct.setImageUrl(imageUrl);

        when(productService.saveOrUpdate(Matchers.<Product>any())).thenReturn(returnProduct);

        mockMvc.perform(post("/product")
                .param("id", "1")
                .param("description", descriptyion)
                .param("price", "12.00")
                .param("imageUrl", "example.com"))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(view().name("redirect:product/show/1"))
                    .andExpect(model().attribute("product", instanceOf(Product.class)))
                    .andExpect(model().attribute("product", hasProperty("id", is(id))))
                    .andExpect(model().attribute("product", hasProperty("description", is(descriptyion))))
                    .andExpect(model().attribute("product", hasProperty("price", is(price))))
                    .andExpect(model().attribute("product", hasProperty("imageUrl", is(imageUrl))));

        ArgumentCaptor<Product> boundProduct = ArgumentCaptor.forClass(Product.class);
        verify(productService).saveOrUpdate(boundProduct.capture());

        assertEquals(id, boundProduct.getValue().getId());
        assertEquals(descriptyion, boundProduct.getValue().getDescription());
        assertEquals(price, boundProduct.getValue().getPrice());
        assertEquals(imageUrl, boundProduct.getValue().getImageUrl());

    }

    @Test
    public void testDelete() throws Exception{

        Integer id = 1;

        mockMvc.perform(get("/product/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/product/list"));

        verify(productService, times(1)).delete(id);
    }

}
