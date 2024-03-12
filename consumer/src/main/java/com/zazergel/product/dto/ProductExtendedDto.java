package com.zazergel.product.dto;

import com.zazergel.comment.dto.CommentDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
public class ProductExtendedDto {
    Long id;
    String name;
    String description;
    Long price;
    String category;
    List<CommentDto> comments;
}