package com.library.management.system;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.system.controller.PatronController;
import com.library.management.system.dto.PatronDTO;
import com.library.management.system.service.PatronService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PatronController.class)
@AutoConfigureMockMvc(addFilters = false)
class PatronRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PatronService patronService;

    @MockBean
    private ModelMapper mapper;

    @Autowired
    private WebApplicationContext context;
    @Before
    public void setUp(){
        mockMvc= MockMvcBuilders.webAppContextSetup(context).build();
    }
    @Test
    public void savePatronTest() throws Exception {
        PatronDTO patronDTO = new PatronDTO(0, "Ram", "12333");
        MvcResult result=   this.mockMvc
                .perform(post("/patrons").
                        contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(patronDTO))
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                        .andExpect(status().isCreated()).andReturn();
    }
    @Test
    @Order(1)
    public void getPatronTest() throws Exception {
        MvcResult result=   this.mockMvc
                .perform(get("/patrons/1")
                        .header(HttpHeaders.AUTHORIZATION,
                                "Basic " + Base64Utils.encodeToString("admin:admin".getBytes())))
                .andExpect(status().isOk()).andReturn();

    }

}
