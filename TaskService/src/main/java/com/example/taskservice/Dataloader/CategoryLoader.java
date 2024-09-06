package com.example.taskservice.Dataloader;
import com.example.taskservice.Domain.Category;
import com.example.taskservice.Dto.CategoryDto;
import com.example.taskservice.Repository.CategoryRepository;
import com.example.taskservice.Service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Order(1)
@Component
public class CategoryLoader implements ApplicationRunner {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Value("${data.source}")
    private String source;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (categoryRepository.count() == 0) {
            String json = source + "category.json";
            ObjectMapper objectMapper = new ObjectMapper();
            List<Category> categories = objectMapper.readValue(new File(json), objectMapper.getTypeFactory().constructCollectionType(List.class, Category.class));

            for (Category category : categories) {
                CategoryDto categoryDto = new CategoryDto();
                BeanUtils.copyProperties(category, categoryDto);
                categoryService.save(categoryDto);
            }
            System.out.println("Categories Saved");
        }
    }
}
