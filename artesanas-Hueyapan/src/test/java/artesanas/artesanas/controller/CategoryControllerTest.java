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
public class CategoryControllerTest {

     @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }


    @Test
    public void getByIdTest() throws Exception {
        mvc.perform(get("/categories/2").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.idCategory", is(2)));
    }

    @Test
    public void getByIdNotFoundTest() throws Exception {
        mvc.perform(get("/categories/0").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string(containsString("The requested item is not registered")));
    }

    @Test
    public void getCategoriesByNameTest() throws Exception {
        String name = "Bolsos";
        mvc.perform(get("/categories/name/" + name)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nombre", is(name)));                                                                                      // especificada
    }
}
