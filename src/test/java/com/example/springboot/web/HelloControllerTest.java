package com.example.springboot.web;

import com.example.springboot.config.auth.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {@ComponentScan.Filter(type= FilterType.ASSIGNABLE_TYPE, classes= SecurityConfig.class)})
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @WithMockUser(roles="USER")
    @Test
    public void hello_is_returned() throws Exception {
        String hello="hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello"));

    }

    @WithMockUser(roles="USER")
    @Test
    void helloDto_is_returned() throws Exception {
        String name="helloDto";
        int amount=1000;

        mvc.perform(get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.valueOf(amount)))    // api에 name과 string으로 바꾼 amount를 담아 요청파라미터로 보냄
                .andExpect(status().isOk())                            // HTTP 요청의 결과가 200인지
                .andExpect(jsonPath("$.name", is(name)))      // JSON 응답값을 "$.필드명"을 통해 검증할 수 있다.
                .andExpect(jsonPath("$.amount", is(amount))); // HelloController는 @RestController 덕에 json을 반환할 수 있다.
    }
}