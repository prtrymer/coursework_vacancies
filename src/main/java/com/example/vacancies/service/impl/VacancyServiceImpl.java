package com.example.vacancies.service.impl;

import com.example.vacancies.dto.VacancyDto;
import com.example.vacancies.mappers.VacancyMapper;
import com.example.vacancies.models.UserEntity;
import com.example.vacancies.models.Vacancy;
import com.example.vacancies.repository.UserRepository;
import com.example.vacancies.repository.VacancyRepository;
import com.example.vacancies.service.VacancyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.vacancies.mappers.VacancyMapper.mapToVacancy;
import static com.example.vacancies.mappers.VacancyMapper.mapToVacancyDto;

@Service
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;

    public VacancyServiceImpl(VacancyRepository vacancyRepository, UserRepository userRepository) {
        this.vacancyRepository = vacancyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<VacancyDto> findAllVacancies() {
        List<Vacancy> vacancies = vacancyRepository.findAll();
        return vacancies.stream().map(VacancyMapper::mapToVacancyDto).collect(Collectors.toList());
    }

    @Override
    public void saveVacancy(VacancyDto vacancyDto, String username) {
        UserEntity user = userRepository.findByUsername(username);
        Vacancy vacancy = mapToVacancy(vacancyDto);
        vacancy.setCreatedBy(user);
        vacancyRepository.save(vacancy);
    }

    @Override
    public VacancyDto findVacancyById(Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId)
                .orElseThrow(() -> new IllegalArgumentException("Vacancy with id %s not found".formatted(vacancyId)));
        return mapToVacancyDto(vacancy);
    }

    @Override
    public void updateVacancy(VacancyDto vacancyDto, String username) {
        UserEntity user = userRepository.findByUsername(username);
        Vacancy vacancy = mapToVacancy(vacancyDto);
        vacancy.setCreatedBy(user);
        vacancyRepository.save(vacancy);
    }

    @Override
    public void delete(Long vacancyId) {
        vacancyRepository.deleteById(vacancyId);
    }

    @Override
    public List<VacancyDto> searchVacancies(String query) {
        List<Vacancy> vacancies = vacancyRepository.findAllByKeywordContainingIgnoreCase(query);
        return vacancies.stream().map(VacancyMapper::mapToVacancyDto).collect(Collectors.toList());
    }

}
