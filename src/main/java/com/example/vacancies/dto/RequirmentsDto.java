package com.example.vacancies.dto;

import com.example.vacancies.models.Vacancy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequirmentsDto {
    private Long id;
    private String experience;
    private String education;
    private Vacancy vacancy;
}
