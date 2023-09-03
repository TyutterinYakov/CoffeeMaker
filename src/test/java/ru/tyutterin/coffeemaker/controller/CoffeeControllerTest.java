//package ru.tyutterin.coffeemaker.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import ru.tyutterin.coffeemaker.dto.NewCoffeeDto;
//import ru.tyutterin.coffeemaker.model.entity.Coffee;
//import ru.tyutterin.coffeemaker.model.entity.CoffeeType;
//import ru.tyutterin.coffeemaker.service.CoffeeService;
//
//import java.util.Map;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(CoffeeController.class)
//@AutoConfigureMockMvc
//public class CoffeeControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    private ObjectMapper objectMapper;
//    @MockBean
//    private Map<CoffeeType, CoffeeService> coffeeServices;
//    @MockBean
//    private CoffeeService coffeeService;
//
//    @Test
//    public void successfulCreationOfCoffee() throws Exception {
//        NewCoffeeDto successCoffee = NewCoffeeDto.builder()
//                .coffeeMakerId(1L)
//                .portionCoffee(1)
//                .coffeeType(CoffeeType.CAPPUCCINO)
//                .sizePortion(350)
//                .portionMilk(1)
//                .sugar(1)
//                .build();
//
//        Coffee newCoffeeResult = Coffee.builder().coffeeType(CoffeeType.CAPPUCCINO).id(1L).build();
//
//        when(coffeeServices.get(any())).thenReturn(coffeeService);
//        when(coffeeService.build(successCoffee))
//                .thenReturn(newCoffeeResult);
//        when(coffeeService.getType())
//                .thenReturn(CoffeeType.CAPPUCCINO);
//
//        mockMvc.perform(post("/coffee/order")
//                            .content(objectMapper.writeValueAsString(successCoffee))
//                            .contentType(MediaType.APPLICATION_JSON))
//                        .andExpect(status().isCreated())
//                        .andExpect(jsonPath("$.id").value(newCoffeeResult.getId()));
//    }
//}
