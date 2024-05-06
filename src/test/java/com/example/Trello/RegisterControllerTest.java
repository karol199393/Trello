/* Tests from Sample
package com.example.Trello;

import com.example.Trello.controller.RegisterController;
import com.example.Trello.entity.User;
import com.example.Trello.service.RegisterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RegisterController.class)
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RegisterService registerService;

    @Test
    public void testRegisterUserSuccess() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        when(registerService.registerUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"testUser\", \"password\":\"testPassword\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRegisterUserFailInvalidData() throws Exception {
        when(registerService.registerUser(any(User.class))).thenThrow(new IllegalArgumentException());

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"\", \"password\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testRegisterUserFailUserExists() throws Exception {
        when(registerService.registerUser(any(User.class))).thenThrow(new IllegalStateException());

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"existingUser\", \"password\":\"testPassword\"}"))
                .andExpect(status().isConflict());
    }
}

 */