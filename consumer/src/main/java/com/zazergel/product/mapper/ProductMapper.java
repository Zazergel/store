package com.zazergel.product.mapper;

import com.zazergel.category.model.Category;
import com.zazergel.comment.dto.CommentDto;
import com.zazergel.comment.mapper.CommentMapper;
import com.zazergel.product.dto.ProductDto;
import com.zazergel.product.dto.ProductExtendedDto;
import com.zazergel.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    @Autowired
    private CommentMapper commentMapper;

    @Mapping(target = "categoryId", expression = "java(product.getCategory().getId())")
    public abstract ProductDto toProductDto(Product product);

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "name", expression = "java(productDto.getName())")
    @Mapping(target = "category", expression = "java(category)")
    public abstract Product toProduct(ProductDto productDto, Category category);

    @Mapping(target = "id", expression = "java(product.getId())")
    @Mapping(target = "category", source = "product", qualifiedByName = "categoryProvider")
    @Mapping(target = "comments", source = "product", qualifiedByName = "commentsProvider")
    public abstract ProductExtendedDto toProductExtendedDto(Product product);

    @Named("commentsProvider")
    public List<CommentDto> commentsMapper(Product product) {
        return product.getComments().stream().map(commentMapper::commentToCommentDto).toList();
    }

    @Named("categoryProvider")
    public String categoriesMapper(Product product) {
        if (product.getCategory() == null) {
            return null;
        } else {
            return product.getCategory().getName();
        }
    }

}
