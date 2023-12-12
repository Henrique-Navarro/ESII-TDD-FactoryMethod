package com.es.fm;

import com.es.fm.controllers.UserController;
import com.es.fm.interfaces.Fish;
import com.es.fm.user.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("usuário criado corretamente")
    void createUser() throws Exception {
        User user = new User(1L, "John Doe",new ArrayList<>());
        user.setId(1L);
        user.setName("John Doe");

        String jsonUser = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("usuário não foi criado corretamente")
    void createUser_Failure() throws Exception {
        User user = new User(1L, "John Doe",new ArrayList<>());

        String jsonUser = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("listar todos os usuários corretamentes")
    void getAllUsers_Success() throws Exception {
        User user1 = new User(1L, "John Doe", new ArrayList<>());
        User user2 = new User(2L, "Jane Doe", new ArrayList<>());
        List<User> userList = Arrays.asList(user1, user2);

        mockMvc.perform(get("/users/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("listar usuário por id")
    void getUserById_Success() throws Exception {
        User user = new User(1L, "John Doe", new ArrayList<>());

        mockMvc.perform(get("/users/{userId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("usuário não encontrado")
    void getUserById_UserNotFound() throws Exception {
        mockMvc.perform(get("/users/{userId}", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("adiciona peixe corretamente ao usuário")
    void addFishToUser_Success() throws Exception {
        Fish fish = new Fish();
        fish.setId(1L);
        //fish.setName("Goldfish");

        mockMvc.perform(post("/users/{userId}/addFish", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Goldfish\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("Fish added to user successfully"));
    }

    @Test
    @DisplayName("usuário não existente na adição de um peixe")
    void addFishToUser_UserNotFound() throws Exception {
        mockMvc.perform(post("/users/{userId}/addFish", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Goldfish\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("User not found"));
    }

    @Test
    @DisplayName("lista todos os peixes de um usuário")
    void getUserFishes_Success() throws Exception {
        List<Fish> fishList = new ArrayList<>();
        fishList.add(new Fish());
        fishList.add(new Fish());

        mockMvc.perform(get("/users/{userId}/fishes", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("usuário não existente na listagem dos peixes")
    void getUserFishes_UserNotFound() throws Exception {
        mockMvc.perform(get("/users/{userId}/fishes", 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("deleta peixe do usuário")
    void removeFishFromUser_Success() throws Exception {
        mockMvc.perform(delete("/users/{userId}/removeFish/{fishId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("usuário não existente para deletar o peixe")
    void removeFishFromUser_FishNotFound() throws Exception {
        mockMvc.perform(delete("/users/{userId}/removeFish/{fishId}", 1L, 999L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("usuário atualizado com sucesso")
    void updateUser_Success() throws Exception {
        String updatedUserJson = "{\"id\":1,\"name\":\"John Doe\",\"fishes\":[]}";

        mockMvc.perform(put("/users/{userId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("User updated successfully"));
    }

    @Test
    @DisplayName("usuário não encontrado para atualizar os dados")
    void updateUser_UserNotFound() throws Exception {
        String updatedUserJson = "{\"id\":999,\"name\":\"John Doe\",\"fishes\":[]}";

        mockMvc.perform(put("/users/{userId}", 999L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedUserJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("User not found"));
    }
}
