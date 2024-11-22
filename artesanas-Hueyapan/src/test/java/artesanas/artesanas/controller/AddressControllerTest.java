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
public class AddressControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AddressController controller;


    @Test
	void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

    @Test
    public void getAddressByCityTest() throws Exception {
        String city = "Querétaro";
        mvc.perform(get("/addresses/search/" + city)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ciudad", is(city)));                                                                                      // especificada
    }  

    /* @Test
    public void getAllTest() throws Exception {
        mvc.perform(get("/addresses").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))));
    } */

    @Test
    public void getByIdTest() throws Exception {
        mvc.perform(get("/addresses/1").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idAddress", is(1)));
    }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        mvc.perform(get("/addresses/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }

}
