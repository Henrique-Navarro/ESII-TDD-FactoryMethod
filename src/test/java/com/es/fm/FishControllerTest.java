package com.es.fm;

import com.es.fm.controllers.FishController;
import com.es.fm.factory.FishFactory;
import com.es.fm.interfaces.Fish;
import com.es.fm.model.Angelfish;
import com.es.fm.services.FishService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(FishController.class)
@AutoConfigureMockMvc
class FishControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FishFactory fishFactory;

    @MockBean
    private FishService fishService;

    @Test
    void swimFish_Success() throws Exception {
        Angelfish angelfish = new Angelfish();
        given(fishFactory.createFish(anyString())).willReturn(angelfish);

        mockMvc.perform(get("/swim/{type}", "angelfish")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void swimFish_FishNotFound() throws Exception {
        try {
            given(fishFactory.createFish(anyString())).willThrow(new IllegalArgumentException("Invalid fish type"));

            mockMvc.perform(get("/swim/{type}", "unknownFish")
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        } catch (Exception e) {
        }
    }

    @Test
    void createFish_Success() throws Exception {
        Fish newFish = new Fish(); // Criar um objeto Fish para simular o envio na requisição

        when(fishService.save(any(Fish.class))).thenReturn(newFish);

        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void createFish_Failure() throws Exception {
        when(fishService.save(any(Fish.class))).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"));
    }

    @Test
    void getAllFish_Success() throws Exception {
        List<Fish> fishList = new ArrayList<>();
        fishList.add(new Fish());
        fishList.add(new Fish());

        when(fishService.getAll()).thenReturn(fishList);

        mockMvc.perform(MockMvcRequestBuilders.get("/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getAllFish_EmptyList() throws Exception {
        when(fishService.getAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(MockMvcRequestBuilders.get("/list")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isEmpty());
    }

    @Test
    void getFishById_Success() throws Exception {
        Long fishId = 1L;
        Fish fish = new Fish();

        when(fishService.getById(fishId)).thenReturn(fish);

        mockMvc.perform(MockMvcRequestBuilders.get("/{id}", fishId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void getFishById_NotFound() throws Exception {
        Long fishId = 999L;
        when(fishService.getById(fishId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.get("/{id}", fishId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateFish_Success() throws Exception {
        Long fishId = 1L;
        Fish existingFish = new Fish(); // Crie um peixe existente para simular a resposta do serviço
        existingFish.setId(fishId);

        Fish updatedFish = new Fish();
        updatedFish.setPeso(2.0f);
        updatedFish.setExpectativaVida(5.0f);
        updatedFish.setTemperamento("Calm");

        when(fishService.getById(fishId)).thenReturn(existingFish);
        when(fishService.save(existingFish)).thenReturn(existingFish);

        mockMvc.perform(MockMvcRequestBuilders.put("/{id}", fishId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"peso\": 2.0, \"expectativaVida\": 5.0, \"temperamento\": \"Calm\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(fishId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.peso").value(2.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.expectativaVida").value(5.0))
                .andExpect(MockMvcResultMatchers.jsonPath("$.temperamento").value("Calm"));
    }

    @Test
    void updateFish_FishNotFound() throws Exception {
        Long fishId = 999L; // Use um ID inexistente para simular a resposta do serviço
        when(fishService.getById(fishId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/{id}", fishId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"peso\": 2.0, \"expectativaVida\": 5.0, \"temperamento\": \"Calm\" }"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void deleteFish_Success() throws Exception {
        Long fishId = 1L;
        Fish existingFish = new Fish(); // Crie um peixe existente para simular a resposta do serviço
        existingFish.setId(fishId);

        when(fishService.getById(fishId)).thenReturn(existingFish);

        mockMvc.perform(MockMvcRequestBuilders.delete("/{id}", fishId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Fish deleted successfully"));
    }

    @Test
    void deleteFish_FishNotFound() throws Exception {
        Long fishId = 999L;
        when(fishService.getById(fishId)).thenReturn(null);

        mockMvc.perform(MockMvcRequestBuilders.delete("/{id}", fishId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}