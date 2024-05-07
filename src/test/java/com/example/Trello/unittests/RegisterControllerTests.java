package com.example.Trello.unittests;

import com.example.Trello.controller.RegisterController;
import com.example.Trello.entity.User;
import com.example.Trello.service.RegisterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RegisterController.class)
public class RegisterControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RegisterService registerService;

    @Before
    public void setUp() {
        registerService = mock(RegisterService.class);
    }

    @Test
    public void testRegisterUser() throws Exception {
        User newUser = new User("testUser", "testPassword", "test@Email");
        when(registerService.registerUser(newUser)).thenReturn(newUser);

        mockMvc.perform(post("/api/v1/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"testUser\", \"password\": \"testPassword\", \"email\": \"test@Email\" }"))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) content().json("{ \"username\": \"testUser\", \"password\": \"testPassword\", \"email\": \"test@Email\" }"));


    }

    @Test
    public void testRegisterUserWithInvalidData() throws Exception {
        User newUser = new User("testUser", "testPassword", "test@Email");
        when(registerService.registerUser(newUser)).thenReturn(newUser);

        mockMvc.perform(post("/api/v1/register")
                        .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"username\": \"\", \"password\": \"\", \"email\": \"\" }"))
                .andExpect(status().isBadRequest());
    }

}
