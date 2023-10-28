package com.example.Ganshin.SecurityForApp.controllers;

import com.example.Ganshin.SecurityForApp.models.Person;
import com.example.Ganshin.SecurityForApp.services.PersonRegisterService;
import com.example.Ganshin.SecurityForApp.utils.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;




@Controller
@RequestMapping("/auth")
public class RegisterController {
    private final PersonRegisterService personRegisterService;
    private final PersonValidator personValidator;

    @Autowired
    public RegisterController(PersonRegisterService personRegisterService, PersonValidator personValidator) {
        this.personRegisterService = personRegisterService;
        this.personValidator = personValidator;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult){
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "auth/registration";

        personRegisterService.register(person);


        return "redirect:/auth.login";
    }
}
