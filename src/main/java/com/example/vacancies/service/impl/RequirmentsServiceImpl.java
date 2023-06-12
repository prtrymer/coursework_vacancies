package com.example.vacancies.service.impl;

import com.example.vacancies.dto.RequirmentsDto;
import com.example.vacancies.dto.VacancyDto;
import com.example.vacancies.mappers.RequirmentsMapper;
import com.example.vacancies.models.Requirments;
import com.example.vacancies.repository.RequirmentsRepository;
import com.example.vacancies.repository.VacancyRepository;
import com.example.vacancies.service.RequirmentsService;
import com.example.vacancies.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.vacancies.mappers.RequirmentsMapper.mapToRequirments;
import static com.example.vacancies.mappers.RequirmentsMapper.mapToRequirmentsDto;
import static com.example.vacancies.mappers.VacancyMapper.mapToVacancy;


@Service
@RequiredArgsConstructor
public class RequirmentsServiceImpl implements RequirmentsService {
    private final RequirmentsRepository requirmentsRepository;
    private final VacancyRepository vacancyRepository;
    private final VacancyService vacancyService;


    @Override
    public void createRequirments(Long vacancyId, RequirmentsDto requirmentsDto) {
        VacancyDto vacancy = vacancyService.findVacancyById(vacancyId);
        Requirments requirments = mapToRequirments(requirmentsDto);
        requirments.setVacancy(mapToVacancy(vacancy));
        requirmentsRepository.save(requirments);
    }

    @Override
    public List<RequirmentsDto> findAllRequirments() {
        List<Requirments> requirments = requirmentsRepository.findAll();
        return requirments.stream().map(RequirmentsMapper::mapToRequirmentsDto).collect(Collectors.toList());
    }

    @Override
    public RequirmentsDto findRequirmentsById(Long requirmentsId) {
        Requirments requirments = requirmentsRepository.findById(requirmentsId)
                .orElseThrow(() -> new IllegalArgumentException("Requirment with id %s not found".formatted(requirmentsId)));
        return mapToRequirmentsDto(requirments);
    }

    @Override
    public void updateRequirments(RequirmentsDto requirmentsDto) {
        Requirments requirments = mapToRequirments(requirmentsDto);
        requirmentsRepository.save(requirments);
    }

    @Override
    public void deleteRequirment(long requirmentsId) {
        requirmentsRepository.deleteById(requirmentsId);
    }

}
