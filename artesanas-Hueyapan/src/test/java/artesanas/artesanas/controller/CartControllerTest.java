package artesanas.artesanas.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CartController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    /*@Test
    public void getAllTest() throws Exception {
        mvc.perform(get("/carts").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    }*/

    @Test
    public void getByIdTest() throws Exception {
        mvc.perform(get("/carts/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCart", is(1)));
    }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        mvc.perform(get("/carts/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }

    @Test
    public void getCartByDateTest() throws Exception {
        String testDate = "2024-11-19";
        mvc.perform(get("/carts/dateCreated/" + testDate)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].fecha", is(testDate)));                                                                                      // especificada
    }

}
