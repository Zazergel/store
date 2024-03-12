package com.zazergel.comment;

import com.zazergel.configuration.Config;
import com.zazergel.configuration.Create;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentRequestDto {
    @NotBlank(groups = Create.class)
    @Size(groups = Create.class, min = Config.MIN_LENGTH_COMMENT,
            max = Config.MAX_LENGTH_COMMENT,
            message = "Недопустимый размер комментария!")
    String text;
}