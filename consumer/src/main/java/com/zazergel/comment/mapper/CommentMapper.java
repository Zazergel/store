package com.zazergel.comment.mapper;

import com.zazergel.comment.dto.CommentDto;
import com.zazergel.comment.dto.CommentRequestDto;
import com.zazergel.comment.model.Comment;
import com.zazergel.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", expression = "java(null)")
    @Mapping(target = "created", expression = "java(dateTime)")
    @Mapping(target = "product", expression = "java(product)")
    Comment commentRequestDtoToComment(CommentRequestDto commentRequestDto, LocalDateTime dateTime,
                                       Product product);

    CommentDto commentToCommentDto(Comment comment);
}
