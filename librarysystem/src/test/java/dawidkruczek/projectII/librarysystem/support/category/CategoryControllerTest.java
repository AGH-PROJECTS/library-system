package dawidkruczek.projectII.librarysystem.support.category;

import dawidkruczek.projectII.librarysystem.model.Category;
import dawidkruczek.projectII.librarysystem.repository.AuthorRepository;
import dawidkruczek.projectII.librarysystem.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CategoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryRepository repository;

    @Test
    void getAllCategories() throws Exception {
        Category category = new Category("Poezja epicka");
        category.setId("1");
        List<Category> categories =  Collections.singletonList(category);
        when(repository.findAll()).thenReturn(categories);
        mockMvc.perform(get("/categories" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"1\",\"name\": \"Poezja epicka\"}]"));
    }

    @Test
    void getCategory() throws Exception {
        Category category = new Category("Poezja epicka");
        category.setId("1");
        when(repository.findById("1")).thenReturn(Optional.of(category));
        mockMvc.perform(get("/categories/1" ))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"id\":\"1\",\"name\": \"Poezja epicka\"}"));

    }
}