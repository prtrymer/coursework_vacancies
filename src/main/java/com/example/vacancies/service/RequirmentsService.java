package com.example.vacancies.service;

import com.example.vacancies.dto.RequirmentsDto;

import java.util.List;

public interface RequirmentsService {
    void createRequirments(Long vacancyId, RequirmentsDto requirmentsDto);

    List<RequirmentsDto> findAllRequirments();

    RequirmentsDto findRequirmentsById(Long requirmentsId);

    void updateRequirments(RequirmentsDto requirmentsDto);

    void deleteRequirment(long requirmentsId);
}
