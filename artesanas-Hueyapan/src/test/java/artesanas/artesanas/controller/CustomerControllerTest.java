package artesanas.artesanas.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CustomerController customerController;

    @Test
    void contextLoads() throws Exception{
        assertThat(customerController).isNotNull();
    }

    /*@Test
    public void getAllTest() throws Exception{
        mvc.perform(get("/customers").accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }*/

    @Test
    public void getByIdTest() throws Exception{
        mvc.perform(get("/customers/3").accept(MediaType.APPLICATION_JSON)).andDo(print())
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.idCustomer", is(3)));
    }

    @Test
    public void getByIdNotFoundTest() throws Exception{
        mvc.perform(get("/customers/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
        .andExpect(status().isNotFound())
        .andExpect(content().string(containsString("The requested item is not registered")));
    }

    @Test
    public void getCustomersByrNameTest() throws Exception {
        String name = "María";
        mvc.perform(get("/customers/name/" + name)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre", is(name)));                                                                                      // especificada
    }
}
