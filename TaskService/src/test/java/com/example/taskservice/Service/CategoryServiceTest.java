package com.example.taskservice.Service;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Dto.CategoryDto;
import com.example.taskservice.Repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.category = new Category();
        this.category.setId(1L);
        this.category.setTitle("mecanico");
    }

    @Test
    void listAll() {
        when(categoryRepository.findAll()).thenReturn(List.of(category,category));
        List<Category> categories = categoryService.listAll();
        assertNotNull(categories);
        assertEquals(2, categories.size());
    }

    @Test
    void save() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto, "id");
        Category saveCategory = categoryService.save(categoryDto);
        assertNotNull(saveCategory);
        assertEquals(saveCategory.getTitle(), category.getTitle());
    }

    @Test
    void update() throws Exception {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        CategoryDto categoryDto = new CategoryDto();
        BeanUtils.copyProperties(category, categoryDto, "id");
        Category saveCategory = categoryService.update(categoryDto,1L);
        assertNotNull(saveCategory);
        assertEquals(saveCategory.getTitle(), category.getTitle());
    }

    @Test
    void delete() throws Exception {
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        doNothing().when(categoryRepository).deleteById(1L);
        categoryService.delete(1L);
        verify(categoryRepository, times(1)).findById(1L);
        verify(categoryRepository, times(1)).deleteById(1L);
    }
}