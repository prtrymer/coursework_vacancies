package com.example.vacancies.controllers;

import com.example.vacancies.dto.VacancyDto;
import com.example.vacancies.models.Vacancy;
import com.example.vacancies.service.UserService;
import com.example.vacancies.service.VacancyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
public class VacancyController {
    private final VacancyService vacancyService;
    private final UserService userService;

    @Autowired
    public VacancyController(VacancyService vacancyService, UserService userService) {
        this.vacancyService = vacancyService;
        this.userService = userService;
    }

    @GetMapping("/vacancies")
    public String listVacancies(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("vacancies", vacancyService.findAllVacancies());
        return "vacancies-list";

    }

    @GetMapping("/vacancies/{vacancyId}")
    public String vacancyDetail(@PathVariable("vacancyId") long vacancyId, Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("vacancy", vacancyService.findVacancyById(vacancyId));
        return "vacancies-detail";
    }

    @GetMapping("/vacancies/new")
    public String createNewVacancy(Model model) {
        Vacancy vacancy = new Vacancy();
        model.addAttribute("vacancy", vacancy);
        return "vacancies-create";
    }

    @GetMapping("/vacancies/{vacancyId}/delete")
    public String deleteVacancy(@PathVariable("vacancyId") Long vacancyId, Model model) {
        vacancyService.delete(vacancyId);
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancies/search")
    public String searchVacancy(@RequestParam(value = "query") String query, Model model, Principal principal) {
        List<VacancyDto> vacancies = vacancyService.searchVacancies(query);
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("vacancies", vacancies);
        return "vacancies-list";
    }

    @PostMapping("/vacancies/new")
    public String saveVacancy(@Valid @ModelAttribute("vacancy") VacancyDto vacancyDto,
                              BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("vacancy", vacancyDto);
            return "vacancies-create";
        }
        vacancyService.saveVacancy(vacancyDto, principal.getName());
        return "redirect:/vacancies";
    }

    @GetMapping("/vacancies/{vacancyId}/edit")
    public String editVacancyForm(@PathVariable("vacancyId") Long vacancyId, Model model) {
        VacancyDto vacancy = vacancyService.findVacancyById(vacancyId);
        model.addAttribute("vacancy", vacancy);
        return "vacancies-edit";
    }

    @PostMapping("/vacancy/{vacancyId}/edit")
    public String updateVacancy(@PathVariable("vacancyId") Long vacancyId,
                                @Valid @ModelAttribute("vacancy") VacancyDto vacancy,
                                BindingResult result, Model model, Principal principal) {
        if (result.hasErrors()) {
            model.addAttribute("vacancy", vacancy);
            return "vacancies-edit";
        }
        vacancy.setId(vacancyId);
        vacancyService.updateVacancy(vacancy, principal.getName());
        return "redirect:/vacancies";
    }
}
