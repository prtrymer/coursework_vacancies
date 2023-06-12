package com.example.vacancies.mappers;

import com.example.vacancies.dto.VacancyDto;
import com.example.vacancies.models.Vacancy;

import java.util.stream.Collectors;

public class VacancyMapper {
    public static VacancyDto mapToVacancyDto(Vacancy vacancy) {
        VacancyDto vacancyDto = VacancyDto.builder()
                .id(vacancy.getId())
                .keyword(vacancy.getKeyword())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .createdBy(vacancy.getCreatedBy())
                .requirments(vacancy.getRequirments().stream()
                        .map(RequirmentsMapper::mapToRequirmentsDto)
                        .collect(Collectors.toList()))
                .build();
        return vacancyDto;
    }

    public static Vacancy mapToVacancy(VacancyDto vacancy) {
        Vacancy vacancyDto = Vacancy.builder()
                .id(vacancy.getId())
                .keyword(vacancy.getKeyword())
                .description(vacancy.getDescription())
                .salary(vacancy.getSalary())
                .createdBy(vacancy.getCreatedBy())
                .build();
        return vacancyDto;
    }
}
