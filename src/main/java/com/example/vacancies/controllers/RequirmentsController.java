package com.example.vacancies.controllers;

import com.example.vacancies.dto.RequirmentsDto;
import com.example.vacancies.models.Requirments;
import com.example.vacancies.service.RequirmentsService;
import com.example.vacancies.service.UserService;
import com.example.vacancies.service.VacancyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class RequirmentsController {
    private RequirmentsService requirmentsService;
    private UserService userService;
    private VacancyService vacancyService;


    @Autowired
    public RequirmentsController(RequirmentsService requirmentsService, UserService userService) {
        this.requirmentsService = requirmentsService;
        this.userService = userService;
    }
    @GetMapping("/requirments")
    public String requirmentList(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("requirments", requirmentsService.findAllRequirments());
        return "requirments-list";
    }

    @GetMapping("/requirments/{Id}")
    public String viewRequirments(@PathVariable("Id")Long Id, Model model, Principal principal) {
        model.addAttribute("vacancy", requirmentsService.findRequirmentsById(Id).getVacancy());
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("requirments", requirmentsService.findRequirmentsById(Id));
        return "requirments-detail";
    }

    @GetMapping("requirments/{vacancyId}/new")
    public String createNewRequirments(@PathVariable("vacancyId") Long vacancyId, Model model){
        Requirments requirments = new Requirments();
        model.addAttribute("vacancyId", vacancyId);
        model.addAttribute("requirments", requirments);
        return "requirments-create";
    }

    @GetMapping("/requirments/{requirmentsId}/edit")
    public String editRequirmentsForm(@PathVariable("requirmentsId") Long requirmentsId, Model model){
        RequirmentsDto requirments  = requirmentsService.findRequirmentsById(requirmentsId);
        model.addAttribute("requirments", requirments);
        return "requirments-edit";
    }

    @PostMapping("/requirments/{vacancyId}")
    public String createRequirments(@PathVariable("vacancyId") Long vacancyId, @ModelAttribute("requirments")RequirmentsDto requirmentsDto, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("requirments", requirmentsDto);
            return "vacancies-create";
        }
        requirmentsService.createRequirments(vacancyId, requirmentsDto);
        return  "redirect:/vacancies/" + vacancyId;
    }

    @PostMapping("/requirments/{requirmentsId}/edit")
    public String updateRequirments(@PathVariable("requirmentsId") Long requirmentsId,
                                @Valid @ModelAttribute("requirments") RequirmentsDto requirments,
                                BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("requirments", requirments);
            return "requirments-edit";
        }
        RequirmentsDto requirmentsDto = requirmentsService.findRequirmentsById(requirmentsId);
        requirments.setId(requirmentsId);
        requirments.setVacancy(requirmentsDto.getVacancy());
        requirmentsService.updateRequirments(requirments);
        return "redirect:/requirments";
    }

    @GetMapping("/requirments/{requirmentsId}/delete")
    public String deleteRequirment(@PathVariable("requirmentsId") long requirmentsId){
        requirmentsService.deleteRequirment(requirmentsId);
        return "redirect:/requirments";
    }
}
