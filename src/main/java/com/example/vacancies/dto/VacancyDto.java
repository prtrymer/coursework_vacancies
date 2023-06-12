package com.example.vacancies.dto;

import com.example.vacancies.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VacancyDto {
    private Long id;
    @NotEmpty(message = "Description shouldn't be empty")
    private String description;
    @NotEmpty(message = "Keyword shouldn't be empty")
    private String keyword;
    @NotEmpty(message = "Salary shouldn't be empty")
    private String salary;
    private UserEntity createdBy;
    private List<RequirmentsDto> requirments;
}
