package net.skideo.controller;

import net.skideo.annotations.ControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FindAllController.class)
@ControllerTest
class FindAllControllerTest {

    @Autowired
    private MockMvc mockMvc;



    @Test
    public void contextLoads() {
        assertNotNull(mockMvc);
       // assertNotNull(userService);
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/api/all"))
                .andExpect(status().is2xxSuccessful());
    }

}