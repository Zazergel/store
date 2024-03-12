package com.zazergel.category.model;

import com.zazergel.configuration.Config;
import com.zazergel.configuration.Create;
import com.zazergel.configuration.Update;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {
    @NotBlank(groups = {Create.class, Update.class})
    @Size(groups = {Create.class, Update.class}, min = Config.MIN_LENGTH_NAME_CATEGORY,
            max = Config.MAX_LENGTH_NAME_CATEGORY,
            message = "Недопустимый размер названия категории!")
    String name;
}
