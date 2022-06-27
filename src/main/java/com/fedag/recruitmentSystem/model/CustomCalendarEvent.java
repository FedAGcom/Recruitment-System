package com.fedag.recruitmentSystem.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author koilng
 * @created 15/06/2022 - 17:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomCalendarEvent {

    @Schema(description = "Название события")
    private String summary;
    @Schema(description = "Описание события")
    private String description;
    @Schema(description = "Время начала")
    private Date eventStartTime;
    @Schema(description = "Время окончания")
    private Date eventEndTime;
}
