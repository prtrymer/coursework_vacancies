package com.example.vacancies.service;

import com.example.vacancies.dto.VacancyDto;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> findAllVacancies();

    void saveVacancy(VacancyDto vacancyDto, String username);

    VacancyDto findVacancyById(Long vacancyId);

    void updateVacancy(VacancyDto vacancy, String username);

    void delete(Long vacancyId);

    List<VacancyDto> searchVacancies(String query);

}
