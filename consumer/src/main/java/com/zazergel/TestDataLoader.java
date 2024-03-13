package com.zazergel;

import com.zazergel.category.dto.CategoryDto;
import com.zazergel.category.repository.CategoryRepo;
import com.zazergel.category.service.CategoryService;
import com.zazergel.product.dto.ProductDto;
import com.zazergel.product.service.ProductService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestDataLoader implements CommandLineRunner {


    private final CategoryService categoryService;
    private final ProductService productService;

    private final CategoryRepo categoryRepo;

    public TestDataLoader(CategoryService categoryService, ProductService productService, CategoryRepo categoryRepo) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        addTestCategories();
        addTestProducts();
    }

    private void addTestCategories() {
        CategoryDto category1 = CategoryDto.builder().name("Фрукты").build();
        CategoryDto category2 = CategoryDto.builder().name("Овощи").build();
        CategoryDto category3 = CategoryDto.builder().name("Инструменты").build();
        CategoryDto category4 = CategoryDto.builder().name("Напиток").build();

        categoryService.add(category1);
        categoryService.add(category2);
        categoryService.add(category3);
        categoryService.add(category4);
    }

    private void addTestProducts() {
        ProductDto product1 = ProductDto.builder()
                .name("Апельсин")
                .description("Сочный, спелый апельсин")
                .price(10L)
                .categoryId(getCategoryIdFromRepo("Фрукты"))
                .build();
        ProductDto product2 = ProductDto.builder()
                .name("Помидор")
                .description("Красный, спелый помидор")
                .price(8L)
                .categoryId(getCategoryIdFromRepo("Овощи"))
                .build();
        ProductDto product3 = ProductDto.builder()
                .name("Молоток")
                .description("Надежный инструмент")
                .price(100L)
                .categoryId(getCategoryIdFromRepo("Инструменты"))
                .build();
        ProductDto product4 = ProductDto.builder()
                .name("Яблочный сок")
                .description("Осветленный")
                .price(50L)
                .categoryId(getCategoryIdFromRepo("Напиток"))
                .build();
        ProductDto product5 = ProductDto.builder()
                .name("Молочный коктейль")
                .description("Из пастеризованного молока")
                .price(80L)
                .categoryId(getCategoryIdFromRepo("Напиток"))
                .build();
        ProductDto product6 = ProductDto.builder()
                .name("Огурец")
                .description("Молодец")
                .price(15L)
                .categoryId(getCategoryIdFromRepo("Овощи"))
                .build();
        ProductDto product7 = ProductDto.builder()
                .name("Гвозди")
                .description("Не жаренные")
                .price(90L)
                .categoryId(getCategoryIdFromRepo("Инструменты"))
                .build();
        ProductDto product8 = ProductDto.builder()
                .name("Банан")
                .description("Обычный банан")
                .price(18L)
                .categoryId(getCategoryIdFromRepo("Фрукты"))
                .build();
        productService.add(product1);
        productService.add(product2);
        productService.add(product3);
        productService.add(product4);
        productService.add(product5);
        productService.add(product6);
        productService.add(product7);
        productService.add(product8);
    }

    private Long getCategoryIdFromRepo(String name){
       return categoryRepo.findByName(name).orElseThrow().getId();
    }
}
