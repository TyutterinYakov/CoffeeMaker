package ru.tyutterin.coffeemaker.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Getter
@AllArgsConstructor
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Сведения об ошибке")
public class ApiError {
    @Schema(description = "Код статуса HTTP-ответа", example = "BAD_REQUEST")
    private final HttpStatus status;

    @Schema(description = "Общее описание причины ошибки", example = "For the requested operation the conditions are not met.")
    private final String reason;

    @Schema(description = "Сообщение об ошибке", example = "Check out the recipe. Cappuccino is made with milk")
    private final String message;

    @Schema(description = "Список стектрейсов или описания ошибок", example = "[]")
    private final List<String> errors;

    @Schema(description = "Дата и время когда произошла ошибка (в формате \"yyyy-MM-dd HH:mm:ss\")",
            type = "string", example = "2022-09-03 16:27:23")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    public ApiError(HttpStatus status, String reason, String message, String error) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        if (error != null && !error.isBlank()) {
            this.errors = Collections.singletonList(error);
        } else {
            this.errors = null;
        }
    }
}
