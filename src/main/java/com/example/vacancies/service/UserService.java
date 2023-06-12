package com.example.vacancies.service;

import com.example.vacancies.dto.RegistrationDto;
import com.example.vacancies.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDto registrationDto);
    UserEntity findByEmail(String email);
    UserEntity findByUsername(String username);
}
