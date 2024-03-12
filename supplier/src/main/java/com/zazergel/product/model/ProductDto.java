package com.zazergel.product.model;

import com.zazergel.configuration.Config;
import com.zazergel.configuration.Create;
import com.zazergel.configuration.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDto {

    @NotBlank(groups = {Create.class, Update.class})
    @Size(groups = {Create.class, Update.class}, min = Config.MIN_LENGTH_NAME_PRODUCT,
            max = Config.MAX_LENGTH_NAME_PRODUCT,
            message = "Недопустимый размер названия продукта!")
    String name;

    @NotBlank(groups = {Create.class, Update.class})
    @Size(groups = {Create.class, Update.class}, min = Config.MIN_LENGTH_DESCRIPTION_PRODUCT,
            max = Config.MAX_LENGTH_DESCRIPTION_PRODUCT,
            message = "Недопустимый размер описания продукта!")
    String description;

    @PositiveOrZero(groups = {Create.class, Update.class}, message = "Значение должно быть неотрицательным!")
    Long price;

    @Positive(groups = Create.class)
    Long categoryId;
}
