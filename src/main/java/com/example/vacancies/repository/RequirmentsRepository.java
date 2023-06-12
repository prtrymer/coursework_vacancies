package com.example.vacancies.repository;

import com.example.vacancies.models.Requirments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;

public interface RequirmentsRepository extends JpaRepository<Requirments , Long> {

}
