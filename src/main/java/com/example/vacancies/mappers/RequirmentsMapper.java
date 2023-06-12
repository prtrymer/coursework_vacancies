package com.example.vacancies.mappers;

import com.example.vacancies.dto.RequirmentsDto;
import com.example.vacancies.models.Requirments;

public class RequirmentsMapper {
    public static Requirments mapToRequirments(RequirmentsDto requirmentsDto){
        return Requirments.builder()
                .id(requirmentsDto.getId())
                .experience(requirmentsDto.getExperience())
                .education(requirmentsDto.getEducation())
                .vacancy(requirmentsDto.getVacancy())
                .build();
    }
    public static RequirmentsDto mapToRequirmentsDto(Requirments requirments){

        return RequirmentsDto.builder()
                .id(requirments.getId())
                .experience(requirments.getExperience())
                .education(requirments.getEducation())
                .vacancy(requirments.getVacancy())
                .build();
    }
}
