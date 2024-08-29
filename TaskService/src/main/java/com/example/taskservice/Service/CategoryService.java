package com.example.taskservice.Service;

import com.example.taskservice.Domain.Category;
import com.example.taskservice.Dto.CategoryDto;
import com.example.taskservice.Repository.CategoryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listAll() {
        return categoryRepository.findAll();
    }

    public Category save(CategoryDto categoryDto) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDto, category);
        category.setTitle(category.getTitle().toLowerCase());
        categoryRepository.save(category);
        return category;
    }

    public Category update(CategoryDto categoryDto,Long id) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category categoryEntity = optionalCategory.get();
            BeanUtils.copyProperties(categoryDto, categoryEntity);
            categoryEntity.setTitle(categoryEntity.getTitle().toLowerCase());
            categoryRepository.save(categoryEntity);
            return categoryEntity;
        }else {
            throw new Exception("Category Not Found");
        }
    }

    public void delete(Long id) throws Exception{
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            categoryRepository.deleteById(id);
        } else {
            throw new Exception("Categor not found");
        }
    }
}
